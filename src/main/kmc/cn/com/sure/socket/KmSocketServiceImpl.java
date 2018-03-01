/**
 * 
 */
package cn.com.sure.socket;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.common.KmErrorMessageConstants;
import cn.com.sure.keypair.entry.KeypairArchive;
import cn.com.sure.keypair.entry.KeypairInuse;
import cn.com.sure.keypair.entry.KeypairStandby;
import cn.com.sure.keypair.service.KeypairArchiveService;
import cn.com.sure.keypair.service.KeypairInuseService;
import cn.com.sure.keypair.service.KeypairStandbyService;
import sun.misc.BASE64Encoder;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("socketService")
public class KmSocketServiceImpl implements KmSocketService{
	
	private static final Log LOG = LogFactory.getLog(KmSocketServiceImpl.class);
	
	@Autowired
	private KeypairStandbyService keypairStandbyService;
	
	@Autowired
	private KeypairInuseService inuseService;
	
	@Autowired
	private KeypairArchiveService archiveService;

	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	

	@Override
	public byte[] handleSocket(byte[] requestInfoByte) throws Exception {
		
		LOG.debug("handleSocket - start");
		
		 //2.3.1生成随机数，作为md5的密钥
    	String desKey=String.valueOf(Math.round(Math.random()*100000000));
        //2.3对keypair进行加密，并返回
        RespKeypairInfo respKeypairInfo = new RespKeypairInfo();
		
		//0、验证签名
		
		//1.对requestInfo信息进行处理
		String requestInfo=new String(requestInfoByte);
		
		if(requestInfo.equals(KmConstants.SYNCHRONOUS_KPG)){
			
			//1.1.1 查询密钥算法，并返回到ca
			List<KeyPairAlgorithm> algorithms = keypairAlgorithmService.selectAll();
			
			LOG.debug("selectAll - end");
			String jsonStr=JSON.toJSONString(algorithms);
			
			return jsonStr.getBytes();
		}
		
		String certDN = null;
		String certSn = null;
		String certStartTime = null;
		String certEndTime = null;
		String algSize = null;
		String certOperType = null;
		
		requestInfo=requestInfo.replaceAll(" ","");
		
		if(requestInfo.contains("\'")){
			requestInfo=requestInfo.replace("\'", "");
		}
		String[] reqInfo = requestInfo.split(";");
		if(reqInfo.length!=6){
			if(reqInfo.length!=2){
				return "信息格式错误".getBytes();
			}
			
		}
		if(reqInfo.length==6){
			//1.2获取证书dn项，证书sn，证书开始时间，证书结束时间，证书
			certDN = reqInfo[0];
			certSn = reqInfo[1];
			certStartTime = reqInfo[2];
			certEndTime = reqInfo[3];
			algSize=reqInfo[4];
			certOperType=reqInfo[5];
		}if(reqInfo.length==2){
			certSn = reqInfo[0];
			certOperType = reqInfo[1];
		}
		
		Integer operType= Integer.parseInt(certOperType);
		
		KeyPairAlgorithm kpAlg = new KeyPairAlgorithm();
		KeypairInuse kpInuse = new KeypairInuse();
		KeypairArchive kpArchive = new KeypairArchive();
		
		switch (operType) {
		
		//2.新办或者更新
		case KmConstants.TYPE_ID_CERT_NEW_OR_UPDATE_STATUS:
			//2获取密钥对
			kpAlg.setAlgorithmName(algSize);
			KeypairStandby kpStandby = keypairStandbyService.obtKpStandby(kpAlg);
	        
			//2.1将获取到的密钥插入到密钥在用库中
	        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
	        KeypairInuse inuse = new KeypairInuse();
	        inuse.setCertDn(certDN);
	        inuse.setCertSn(certSn);
	        inuse.setEndTime(dfm.parse(certEndTime));
	        inuse.setStartTime(dfm.parse(certStartTime));
	        inuse.setInuseTime(new Date());
	        inuse.setKeyPairAlgorithm(kpAlg);
	        inuse.setKpgTask(kpStandby.getKpgTask());
	        inuse.setPriKey(kpStandby.getPriKey());
	        inuse.setPubKey(kpStandby.getPubKey());
	        inuse.setGenTime(kpStandby.getGenTime());
	        
	        inuseService.insert(inuse);
	        
	        //2.2删除备用库中的那条记录
	        keypairStandbyService.delete(kpStandby.getId());
	        
				//2.3.3用md5对keypair进行加密
	        	String encryptedPriKey;
				encryptedPriKey = encrypt(kpStandby.getPriKey(),desKey);
				String encryptedPubKey;
				encryptedPubKey = encrypt(kpStandby.getPubKey(),desKey);
				//2.3.1将需要的信息set到respKeypairInfo里边去
		        respKeypairInfo.setPrivateKey(encryptedPriKey);
		        respKeypairInfo.setPublicKey(encryptedPubKey);
		        respKeypairInfo.setMd5(desKey);
		      //2.4
		      String jsonStr=JSON.toJSONString(respKeypairInfo);
		      
	        //4返回密钥对信息
		     return jsonStr.getBytes();

		//注销
		case KmConstants.TYPE_ID_CERT_REVOKE_STATUS:
			
			//5注销，将密钥归档
			//5.1根据sn查询密钥
			kpInuse.setCertSn(certSn);
			kpInuse = inuseService.findBySn(kpInuse);
			
			//5.2把查询出来的在用密钥移动到历史密钥库中
			kpArchive.setPubKey(kpInuse.getPubKey());
			kpArchive.setPriKey(kpInuse.getPriKey());
			kpArchive.setKeyPairAlgorithm(kpInuse.getKeyPairAlgorithm());
			kpArchive.setKpgTask(kpInuse.getKpgTask());
			kpArchive.setGenTime(kpInuse.getGenTime());
			kpArchive.setStartTime(kpInuse.getStartTime());
			kpArchive.setEndTime(kpInuse.getEndTime());
			kpArchive.setCertSn(kpInuse.getCertSn());
			kpArchive.setCertDn(kpInuse.getCertDn());
			kpArchive.setArchTime(new Date());
			
			archiveService.insert(kpArchive);
			
			//5.3删除在用库中的信息
			inuseService.delete(kpInuse.getId());
			
			//KmApplicationexception.throwException(KmErrorMessageConstants.revokeKpSuce, new String[]{kpArchive.getCertSn()});
			return "注销密钥成功".getBytes();
			
		//密钥恢复
		case KmConstants.TYPE_ID_CERT_RECOVER_STATUS:
			
			//6根据sn查询密钥
			//TODO需要修改成对象传输
			kpArchive = archiveService.findBySn(certSn);
			if(kpArchive==null||"".equals(kpArchive)){
				//6.1找不到这条记录
				KmApplicationexception.throwException(KmErrorMessageConstants.snNotFound, new String[]{kpArchive.getCertSn()});
				return "找不到这条记录".getBytes();
			}else{
				//6.2找到这个keypair,封装并返回
				//6.2.1md5加密密钥对
				respKeypairInfo.setPrivateKey(encrypt(kpArchive.getPriKey(),desKey));
				respKeypairInfo.setPublicKey(encrypt(kpArchive.getPubKey(),desKey));
				respKeypairInfo.setMd5(desKey);
				
				jsonStr=JSON.toJSONString(respKeypairInfo);
			}
			
			return jsonStr.getBytes();

		default:
			break;
		}
		
	
		LOG.debug("handleSocket - end");
		return null;
	}
	
	boolean verifySignature(String requestInfo){
		LOG.debug("verifySignature - start");
		
		LOG.debug("verifySignature - end");
		return false;
	}

    public static String encrypt(String data, String key) throws Exception {  
        byte[] bt = encrypt(data.getBytes(), key.getBytes());  
        String strs = new BASE64Encoder().encode(bt);  
        return strs;  
    }  
 
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
    	final String DES = "DES";
        // 生成一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
        // 从原始密钥数据创建DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);  
        SecretKey securekey = keyFactory.generateSecret(dks);  
        // Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance(DES);  
        // 用密钥初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        return cipher.doFinal(data);
    } 
    
}
