/**
 * 
 */
package cn.com.sure.auto.kpg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.KeyPairGenThread;
import cn.com.sure.keypair.service.KeypairStandbyService;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeService;

/**
 * @author Limin
 *
 */
@Component
public class AutoKpg implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private KeypairStandbyService keypairStandbyService;
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KpgTaskExecuteService kpgTaskExecuteService;
	
	
	private static final Log LOG = LogFactory.getLog(AutoKpg.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
	   if (event.getSource() instanceof XmlWebApplicationContext) {
           if (((XmlWebApplicationContext) event.getSource()).getDisplayName().equals("Root WebApplicationContext")) {
        	   //自动生成密钥
        	   autoKpg();
           }
       }
	}
	 public void autoKpg(){
	 	//1查询
    	//1.1查询密钥算法
		 LOG.debug("autoKpg - start");
    		List <KeyPairAlgorithm> list = keypairAlgorithmService.selectAll();
    		
    		//1.2查询每种算法的密钥数量
    		for(KeyPairAlgorithm kpa:list){
    			
    			//1.3判断数据库密钥数量是否小于密钥最小数量
    			//1.3.1先查询一下数据字典中是否有这条记录，如果记录被删除，则报错到控制台，提示缺少这条记录
    			SysCode sysCode = new SysCode();
    			SysCodeType sysCodeType = new SysCodeType();
    			sysCodeType.setParaType(Constants.KEY_NUM_MIN);
    			sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
    			sysCode.setParaType(sysCodeType);
    			
    			if(sysCodeService.searchByCondition(sysCode).size()==0) {
    				LOG.error("数据字典中缺少密钥最少数量的记录，请添加！");
    				return;
    			}
    			//1.3.2如果数据字典中存在这条记录，则查询出来对比一下现有的密钥对是否少于这条记录对应的最小数量，如果小于，则生成密钥对，如果不小于，则跳过
    			if(keypairStandbyService.countNum(kpa.getAlgorithmName())<Integer.parseInt(sysCodeService.searchByCondition(sysCode).get(0).getParaValue())){//密钥数量<密钥数量最小值
    				
    				//1.4新建一个任务,生成密钥
    				LOG.info("new TASk start");
    				KpgTask task = new KpgTask();
    				
    				//1.4.1缓存数量，默认获取第一条
    				SysCode code = new SysCode();
        			SysCodeType codeType = new SysCodeType();
        			codeType.setParaType(Constants.DB_COMMIT_BUFFER);
    				code.setIsValid(Constants.YES_OR_NO_OPTION_YES);
    				code.setParaType(codeType);
    				//1.4.1.1先查询一下数据字典中是否有这条记录，如果记录被删除，则报错到控制台，提示缺少这条记录
        			if(sysCodeService.searchByCondition(code).size()==0) {
        				LOG.error("数据字典中缺少缓存数量记录，请添加！");
        				return;
        			}
        			code.setParaValue(sysCodeService.searchByCondition(code).get(0).getParaValue());
    				task.setDbCommitBufsize(code);
    				
    				//1.4.2生成最小数量，默认取第一条.
    				//如果备用表中密钥的数量小于数据字典中密钥的最少数量，则启动生成，但是不能少于最少数量多少就生成多少，应该生成大于那个数量的密钥，因此要设立一个如果小于密钥最小数量了，那么一次应该生成多少条密钥
    				SysCode codes = new SysCode();
        			SysCodeType codeTypes = new SysCodeType();
        			codeTypes.setParaType(Constants.GEN_KEY_NUM);
    				codes.setIsValid(Constants.YES_OR_NO_OPTION_YES);
    				codes.setParaType(codeTypes);
    				
    				//1.4.2.1先查询一下数据字典中是否有这条记录，如果记录被删除，则报错到控制台，提示缺少这条记录
        			if(sysCodeService.searchByCondition(codes).size()==0) {
        				LOG.error("数据字典中缺少最小生成数量的记录，请添加！");
        				return;
        			}
        			codes.setParaValue(sysCodeService.searchByCondition(codes).get(0).getParaValue());
    				task.setKpgKeyAmount(Integer.parseInt(codes.getParaValue()));
    				
    				//1.4.3密钥算法
    				KeyPairAlgorithm algorithm = new KeyPairAlgorithm();
    				algorithm.setId(kpa.getId());
    				task.setKeyPairAlgorithm(algorithm);
    				
    				//1.4.4创建一个任务并启动
    				SysCode codeAlg = new SysCode();
    				codeAlg.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING));
    				task.setTaskStatus(codeAlg);
    				//用当前时间来命名任务名称
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    				task.setName("系统自动生成密钥"+sdf.format(new Date()));
    				task.setStartTime(new Date());
    				try {
    					//插入一条任务
    					kpgTaskService.insert(task);
    				} catch (Applicationexception e) {
    					e.printStackTrace();
    				}
    				
    				//启动线程，自动生成密钥
    				new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
    			}
    		}
     }
	 
		 
			
}
