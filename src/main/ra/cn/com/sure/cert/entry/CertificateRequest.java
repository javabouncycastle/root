/**
 * 
 */
package cn.com.sure.cert.entry;

import java.util.Date;

import cn.com.sure.ctml.entry.CertificateTemplate;

/**
 * @author Limin
 *
 */
public class CertificateRequest {
	
	
	
	public CertificateRequest() {
		super();
	}

	public CertificateRequest(String id) {
		super();
		this.id = id;
	}

	private String id;
	
	/**
	 * 证书cn
	 */
	private String certCn;
	
	/**
	 * 证书dn
	 */
	private String certDn;
	
	/**
	 * 证书申请时间
	 */
	private Date requestTime;
	
	/**
	 * 证书开始时间
	 */
	private Date startTime;
	
	/**
	 * 证书结束时间
	 */
	private Date endTime;
	
	/**
	 * 证书模板号
	 */
	private CertificateTemplate ctmlId;
	
	/**
	 * 管理员
	 */
	private String admin;
	
	/**
	 * 有效天数
	 * @return
	 */
	private String validity;
	
	/**
	 * 申请类型-新办，更新，注销
	 * @return
	 */
	private String reqType;
	
	/**
	 * 扩展项,列表形式（id1:1,id2:1）
	 * @return
	 */
	private String extend;
	
	/**
	 * 签发用的几级根
	 * @return
	 */   
	/*private String rootCertType;*/
	
	/**
	 * 开始时间类型，是审核时开始还是前台选择开始时间
	 * @return
	 */
	private Integer StartTimeType;
	
	/**
	 * 证书O项，如果有多个O项，中间用,隔开
	 */
	private String organization ;
	
	/**
	 * 证书OU项，如果有多个OU项，中间用,隔开
	 */
	private String organizationUnit;
	
	/**
	 * Location,不存库
	 * @return
	 */
	private String location;
	
	/**
	 * 地区,不存库
	 * @return
	 */
	private String state;
	
	/**
	 *国家 ,不存库
	 * @return
	 */
	private String county;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertCn() {
		return certCn;
	}

	public void setCertCn(String certCn) {
		this.certCn = certCn;
	}

	public String getCertDn() {
		return certDn;
	}

	public void setCertDn(String certDn) {
		this.certDn = certDn;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
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

	public CertificateTemplate getCtmlId() {
		return ctmlId;
	}

	public void setCtmlId(CertificateTemplate ctmlId) {
		this.ctmlId = ctmlId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public Integer getStartTimeType() {
		return StartTimeType;
	}

	public void setStartTimeType(Integer startTimeType) {
		StartTimeType = startTimeType;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(String organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
