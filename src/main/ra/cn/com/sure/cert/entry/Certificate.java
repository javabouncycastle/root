package cn.com.sure.cert.entry;

import java.util.Date;

import cn.com.sure.ctml.entry.CertificateTemplate;

public class Certificate {
	
	private String id;
	
	private String dn;//证书dn
	
	private String cn;//证书cn
	
	private String sn;//证书sn
	
	private String ref;//证书参考码
	
	private String cert;//base64格式的证书
	
	private Date startTime;//证书开始时间
	
	private Date endTime;//证书结束时间
	
	private String validity;//证书有效期
	
	private CertificateTemplate kpAlg;//密钥类型
	
	private String status;//证书状态
	
	private CertificateTemplate ctmlId;//证书模板
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
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

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public CertificateTemplate getKpAlg() {
		return kpAlg;
	}

	public void setKpAlg(CertificateTemplate kpAlg) {
		this.kpAlg = kpAlg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CertificateTemplate getCtmlId() {
		return ctmlId;
	}

	public void setCtmlId(CertificateTemplate ctmlId) {
		this.ctmlId = ctmlId;
	}
	
}
