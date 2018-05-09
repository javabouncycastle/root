/**
 * 
 */
package cn.com.sure.ctml.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.ErrorMessageConstants;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.ctml.dao.CertificateTemplateDAO;
import cn.com.sure.ctml.entry.CertificateTemplate;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.service.SysCodeService;


/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("CertificateTemplateService")
public class CertificateTemplateServiceImpl implements CertificateTemplateService{
	
	
	private static final Log LOG = LogFactory.getLog(CertificateTemplateServiceImpl.class);
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private CertificateTemplateDAO certTemplateDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#selectAll()
	 */
	@Override
	public List<CertificateTemplate> selectAll(PagedQuery pageQuery) {
		LOG.debug("selectAll - start");
		List<CertificateTemplate> certTemplates = certTemplateDAO.selectAll(pageQuery);
		LOG.debug("selectAll - end");
		return certTemplates;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#insert(cn.com.sure.ca.template.entry.CertTemplate)
	 */
	@Override
	public int insert(CertificateTemplate certTemplate) throws  Applicationexception{
		LOG.debug("insert - start");
		int i = 0;
		CertificateTemplate certificateTemplates = certTemplateDAO.findByName(certTemplate.getCtmlName());
		if(certificateTemplates==null) {
			
			//1.for循环遍历数据字典的id，根据id去数据库里边查出来有多少，再封装成一个string
			//1.1重新封装证书拓展
			if(StringUtils.isNotBlank(certTemplate.getCertExtend())) {
				String reqCertExtend=certTemplate.getCertExtend();
				String[] strCertExtend=reqCertExtend.split(",");
				String certExtend = "";
				if(strCertExtend!=null) {
					for(int j=0;j<strCertExtend.length;j++){
						SysCode syscode = sysCodeService.selectById(Long.valueOf(strCertExtend[j]));
						if(StringUtils.isNotBlank(certExtend)) {
							certExtend = certExtend+";"+syscode.getParaCode()+":"+syscode.getParaValue();
						}else {
							certExtend = syscode.getParaCode()+":"+syscode.getParaValue();
						}
						
					}
					//1.1.1封装好的CertExtend重新set到证书模板里边去
					certTemplate.setCertExtend(certExtend);
				}
			}
			//1.2重新封装增强型密钥用法
			if(StringUtils.isNotBlank(certTemplate.getEku())) {
				String reqEku = certTemplate.getEku();
				String[] strEku = reqEku.split(",");
				String eku="";
				if(strEku!=null) {
					for(int j=0;j<strEku.length;j++) {
						SysCode syscode = sysCodeService.selectById(Long.valueOf(strEku[j]));
						if(StringUtils.isNotBlank(eku)) {
							eku = eku+";"+syscode.getParaCode()+":"+syscode.getParaValue();
						}else {
							eku = syscode.getParaCode()+":"+syscode.getParaValue();
						}
						
					}
					certTemplate.setEku(eku);
				}
			}
			//1.3重新封装签名证书密钥用法
			//这里有bug
			if(StringUtils.isNotBlank(certTemplate.getSignCertKeyUsage())) {
				String reqSignCertKeyUsage = certTemplate.getSignCertKeyUsage();
				String[] strSignCertKeyUsage = reqSignCertKeyUsage.split(",");
				String signCertKeyUsage = "";
				if(strSignCertKeyUsage!=null) {
					for(int j=0;j<strSignCertKeyUsage.length;j++) {
						SysCode syscode = sysCodeService.selectById(Long.valueOf(strSignCertKeyUsage[j]));
						if(StringUtils.isNoneBlank(signCertKeyUsage)) {
							signCertKeyUsage = signCertKeyUsage+";"+syscode.getParaCode()+":"+syscode.getParaValue();
						}else {
							signCertKeyUsage = syscode.getParaCode()+":"+syscode.getParaValue();
						}
						
					}
					certTemplate.setSignCertKeyUsage(signCertKeyUsage);
				}
			}
			//1.4 重新封装加密证书密钥用法
			if(StringUtils.isNotBlank(certTemplate.getEncCertKeyUsage())) {
				String reqEncCertKeyUsage = certTemplate.getEncCertKeyUsage();
				String[] strEncCertKeyUsage = reqEncCertKeyUsage.split(",");
				String encCertKeyUsage = "";
				if(strEncCertKeyUsage!=null) {
					for(int j=0;j<strEncCertKeyUsage.length;j++) {
						SysCode syscode = sysCodeService.selectById(Long.valueOf(strEncCertKeyUsage[j]));
						if(StringUtils.isNotBlank(encCertKeyUsage)) {
							encCertKeyUsage = encCertKeyUsage+";"+syscode.getParaCode()+":"+syscode.getParaValue();
						}else {
							encCertKeyUsage = syscode.getParaCode()+":"+syscode.getParaValue();
						}
						
					}
					certTemplate.setEncCertKeyUsage(encCertKeyUsage);
				}
			}
			
			
			i = certTemplateDAO.insert(certTemplate);
		}else {
			Applicationexception.throwException(ErrorMessageConstants.paraValueExist, new String[]{certTemplate.getCtmlName()});
		}
		
		LOG.debug("insert - end");
		
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#update(cn.com.sure.ca.template.entry.CertTemplate)
	 */
	@Override
	public int update(CertificateTemplate certTemplate) throws Applicationexception {
	
		LOG.debug("update - start");
		
		CertificateTemplate certificateTemplate = new CertificateTemplate();
		certificateTemplate.setId(certTemplate.getId());
		certificateTemplate.setCtmlName(certTemplate.getCtmlName());
		
		List<CertificateTemplate> certificateTemplates= certTemplateDAO.searchByCondition(certificateTemplate);
		
		int i = 0;
		if(certificateTemplates.size()==0) {
			i = certTemplateDAO.update(certTemplate);
		}else {
			Applicationexception.throwException(ErrorMessageConstants.paraValueExist, new String[]{certTemplate.getCtmlName()});
		}
		
		LOG.debug("insert - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#remove(java.lang.Long)
	 */
	@Override
	public int remove(String id) {
		LOG.debug("remove - start");
		int i = certTemplateDAO.remove(id);
		LOG.debug("remove - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#activite(java.lang.String)
	 */
	@Override
	public int activite(String id) {
		LOG.debug("activite - start");
		CertificateTemplate certTemplate = certTemplateDAO.findById(id);
		certTemplate.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		int i = certTemplateDAO.updateValid(certTemplate);
		LOG.debug("activite - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CertTemplateService#suspend(java.lang.String)
	 */
	@Override
	public int suspend(String id) {
		LOG.debug("suspend - start");
		CertificateTemplate certTemplate = certTemplateDAO.findById(id);
		certTemplate.setIsValid(Constants.YES_OR_NO_OPTION_NO);
		int i = certTemplateDAO.updateValid(certTemplate);
		LOG.debug("suspend - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.ca.template.service.CaCertTemplateService#selectStandby()
	 */
	@Override
	public List<CertificateTemplate> selectStandby() {
		LOG.debug("selectStandby - start");
		CertificateTemplate caCertTemplate = new CertificateTemplate();
		caCertTemplate.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<CertificateTemplate> caCertTemplates = certTemplateDAO.selectStandby(caCertTemplate);
		LOG.debug("selectStandby - end");
		return caCertTemplates;
	}

	@Override
	public List<CertificateTemplate> searchByCondition(CertificateTemplate ctml) {
		LOG.debug("searchByCondition - start");
		List<CertificateTemplate> caCertTemplates = certTemplateDAO.searchByCondition(ctml);
		LOG.debug("searchByCondition - end");
		return caCertTemplates;
	}

	@Override
	public int count() {
		LOG.debug("getSysCodeCount - start");
		int i = certTemplateDAO.count();
		LOG.debug("getSysCodeCount - end");
		return i;
	}

	@Override
	public CertificateTemplate findById(String id) {
		LOG.debug("findById - start");
		CertificateTemplate certificateTemplate = certTemplateDAO.findById(id);
		LOG.debug("findById - end");
		return certificateTemplate;
	}


}
