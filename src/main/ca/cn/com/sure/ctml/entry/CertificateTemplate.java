/**
 * 
 */
package cn.com.sure.ctml.entry;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;

/**
 * @author Limin
 *
 */
public class CertificateTemplate {
	
	private String id;
	
	/**
	 * 密钥长度
	 */
	private Integer keySize;
	
	/**
	 * 模板名称
	 */
	private String ctmlName;
	
	/**
	 * 密钥对类型，引用数据字典表
	 */
	private KeyPairAlgorithm kpgAlgorithm;
	
	/**
	 * 有效期天数
	 */
	private String validity;
	
	/**
	 * hash
	 */
	private String hash;
	
	/**
	 * 签名证书密钥用法   列表形式（id1:1,id2:1）
	 */
	private String signCertKeyUsage;

	
	/**
	 * 加密证书密钥用法   列表形式
	 */
	private String encCertKeyUsage;       

	/**
	 * 是否有效
	 */
	private Integer isValid;
	
	/**
	 * baseDN
	 */
	private String baseDn;
	
	/**
	 * 审核类型，自动审核or手动审核
	 */
	private Integer reviewedType;
	
	/**
	 * 增强型密钥用法
	 */
	private String eku;
	
	/**
	 * 备注
	 */
	private String notes;
	
	/**
	 * 证书拓展
	 */
	private String certExtend;

	public String getId() {
		return id;
	}

	public Integer getKeySize() {
		return keySize;
	}

	public void setKeySize(Integer keySize) {
		this.keySize = keySize;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCtmlName() {
		return ctmlName;
	}

	public void setCtmlName(String ctmlName) {
		this.ctmlName = ctmlName;
	}

	public KeyPairAlgorithm getKpgAlgorithm() {
		return kpgAlgorithm;
	}

	public void setKpgAlgorithm(KeyPairAlgorithm kpgAlgorithm) {
		this.kpgAlgorithm = kpgAlgorithm;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSignCertKeyUsage() {
		return signCertKeyUsage;
	}

	public void setSignCertKeyUsage(String signCertKeyUsage) {
		this.signCertKeyUsage = signCertKeyUsage;
	}

	public String getEncCertKeyUsage() {
		return encCertKeyUsage;
	}

	public void setEncCertKeyUsage(String encCertKeyUsage) {
		this.encCertKeyUsage = encCertKeyUsage;
	}


	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getEku() {
		return eku;
	}

	public void setEku(String eku) {
		this.eku = eku;
	}

	public String getNotes() {
		return notes;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public Integer getReviewedType() {
		return reviewedType;
	}

	public void setReviewedType(Integer reviewedType) {
		this.reviewedType = reviewedType;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCertExtend() {
		return certExtend;
	}

	public void setCertExtend(String certExtend) {
		this.certExtend = certExtend;
	}

}
