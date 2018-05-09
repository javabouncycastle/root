/**
 * 
 */
package cn.com.sure.cert.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.cert.dao.CertificateDAO;
import cn.com.sure.cert.dao.CertificateRequestDAO;
import cn.com.sure.cert.entry.Certificate;
import cn.com.sure.cert.entry.CertificateRequest;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.ErrorMessageConstants;
import cn.com.sure.ctml.dao.CertificateTemplateDAO;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("RaCertificateRequestService")
public class CertificateRequestServiceImpl implements CertificateRequestService{
	
	private static final Log LOG = LogFactory.getLog(CertificateRequestServiceImpl.class);
	
	Date date = new Date();
	
	@Autowired
	private CertificateRequestDAO raCertificateRequestDAO;
	
	@Autowired
	private CertificateTemplateDAO certificateTemplateDAO;
	
	@Autowired
	private CertificateDAO certificateDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.ra.cert.service.RaCertificateRequestService#insert(cn.com.sure.ra.cert.entry.RaCertificateRequest)
	 */
	@Override
	public int insert(CertificateRequest raCertificateRequest) throws Applicationexception {
		LOG.debug("insert - start");
		
		//1.1设置证书请求时间为当前时间
		raCertificateRequest.setRequestTime(date);
		
		//1.2查询证书有效期是否超出证书模板的有效期
		certificateTemplateDAO.findById(raCertificateRequest.getCtmlId().getId());
		
		if(Integer.parseInt(raCertificateRequest.getValidity())>Integer.parseInt(certificateTemplateDAO.findById(raCertificateRequest.getCtmlId().getId()).getValidity())) {
		
			Applicationexception.throwException(ErrorMessageConstants.outValidity, new String[]{raCertificateRequest.getCertCn()});
		}
		
		//1.2设置开始时间为前台传过来的时间或者是审核通过时,如果是3则标识开始时间为前台传过来的时间
		if((Constants.CERT_START_TIME_CHECKIN).equals(raCertificateRequest.getStartTimeType())) {
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(raCertificateRequest.getStartTime());
		    
		    calendar.add(Calendar.DATE, Integer.parseInt(raCertificateRequest.getValidity()));
		    raCertificateRequest.setEndTime(calendar.getTime());
		}
		
		//1.3 封装dn项
		String dn = null;
		if(StringUtils.isNotBlank(raCertificateRequest.getCertCn())) {
			
			dn = "CN="+raCertificateRequest.getCertCn();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getOrganizationUnit())) {
			
			dn = dn +",OU="+raCertificateRequest.getOrganizationUnit();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getOrganization())) {
			
			dn = dn+",O="+raCertificateRequest.getOrganization();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getLocation())) {
			
			dn=dn+",L="+raCertificateRequest.getLocation();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getState())) {
			
			dn=dn+",S="+raCertificateRequest.getState();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getCounty())) {
			
			dn=dn+",C="+raCertificateRequest.getCounty();
			
		}if(StringUtils.isNotBlank(raCertificateRequest.getCtmlId().getBaseDn())) {
			
			dn=dn+","+raCertificateRequest.getCtmlId().getBaseDn();
			
		}
		
		//4.查询证书表中是否有在用的dn相同的证书状态使用中的证书，如果有返回错误，如果没有，则将这个申请插入库中
		Certificate certificate = new Certificate();
		certificate.setDn(dn);
		certificate.setStatus(Constants.CODE_ID_CERT_STATUS_IN_USE);
		certificate.setCtmlId(raCertificateRequest.getCtmlId());
		
		int i =0;
		i = raCertificateRequestDAO.insert(raCertificateRequest);
		//TODO
		//证书模块还没有写完，暂时不去查询
		/*if(certificateDAO.searchByCondition(certificate).size() != 0) {
			Applicationexception.throwException(ErrorMessageConstants.dnIsExist, new String[]{raCertificateRequest.getCertCn()});
		}else {
			raCertificateRequest.setCertDn(dn);
			i = raCertificateRequestDAO.insert(raCertificateRequest);
		}*/
		
		LOG.debug("insert - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ra.cert.service.RaCertificateRequestService#findById(java.lang.String)
	 */
	@Override
	public CertificateRequest findById(String id) {
		LOG.debug("findById - start");
		CertificateRequest raCertificateRequests = raCertificateRequestDAO.findById(id);
		LOG.debug("findById - end");
		return raCertificateRequests;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ra.cert.service.RaCertificateRequestService#searchByCondition(cn.com.sure.ra.cert.entry.RaCertificateRequest)
	 */
	@Override
	public List<CertificateRequest> searchByCondition(CertificateRequest raCertificateRequest) {
		LOG.debug("searchByCondition - start");
		List<CertificateRequest> raCertificateRequests = raCertificateRequestDAO.searchByCondition(raCertificateRequest);
		LOG.debug("searchByCondition - end");
		return raCertificateRequests;
	}

	@Override
	public List<CertificateRequest> selectAll() {
		LOG.debug("selectAll - start");
		List<CertificateRequest> raCertificateRequests = raCertificateRequestDAO.selectAll();
		LOG.debug("selectAll - end");
		return raCertificateRequests;
	}

	@Override
	public void check(CertificateRequest raCertificateRequest) {
		LOG.debug("check - start");
		

		LOG.debug("check - end");
	}

	@Override
	public List<CertificateRequest> findByCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
