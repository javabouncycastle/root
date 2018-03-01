package cn.com.sure.keypair.entry;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.kpgtask.entry.KpgTask;

@Entity
@Table(name = "km_keypair_in_use")
public class KeypairInuse {
	
	/**
	 * 主键KID唯一的
	 */
	@Id
	private String id;
	
	
	/**
	 * 公钥 base64格式
	 */
	private String pubKey;
	
	
	/**
	 * 私钥 base64格式
	 */
	private String priKey;
	
	/**
	 * 密钥算法
	 */
	private KeyPairAlgorithm keyPairAlgorithm;
	
	/**
	 * 所属任务
	 */	
	private KpgTask kpgTask;

	/**
	 * 密钥生成时间
	 */
    private Date genTime;
    
    /**
     * 密钥使用开始时间
     */
    private Date startTime;
    
    /**
     * 密钥使用结束时间时间
     */
    private Date endTime;
    
    /**
     * 证书系列号
     */
    private String certSn;
    
    /**
     * 证书标识
     */
    private String certDn;
    
    private Date inuseTime;
    

	public Date getInuseTime() {
		return inuseTime;
	}

	public void setInuseTime(Date inuseTime) {
		this.inuseTime = inuseTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}

	public KeyPairAlgorithm getKeyPairAlgorithm() {
		return keyPairAlgorithm;
	}

	public void setKeyPairAlgorithm(KeyPairAlgorithm keyPairAlgorithm) {
		this.keyPairAlgorithm = keyPairAlgorithm;
	}

	public KpgTask getKpgTask() {
		return kpgTask;
	}

	public void setKpgTask(KpgTask kpgTask) {
		this.kpgTask = kpgTask;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getCertDn() {
		return certDn;
	}

	public void setCertDn(String certDn) {
		this.certDn = certDn;
	}
    
    
    
}
