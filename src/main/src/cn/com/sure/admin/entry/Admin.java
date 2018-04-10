/**
 * 
 */
package cn.com.sure.admin.entry;

import java.util.Date;

/**
 * @author Limin
 *
 */
public class Admin {
	
	private String id;
	
	private String cert;
	private String pfxCert;
	private Date startTime;
	private Date endTime;
	private String certDn;
	private String certSn;
	private String adminId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public String getPfxCert() {
		return pfxCert;
	}
	public void setPfxCert(String pfxCert) {
		this.pfxCert = pfxCert;
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
	public String getCertDn() {
		return certDn;
	}
	public void setCertDn(String certDn) {
		this.certDn = certDn;
	}
	public String getCertSn() {
		return certSn;
	}
	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	

}
