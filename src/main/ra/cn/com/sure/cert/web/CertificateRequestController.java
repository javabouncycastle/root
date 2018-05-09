/**
 * 
 */
package cn.com.sure.cert.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.cert.entry.CertificateRequest;
import cn.com.sure.cert.service.CertificateRequestService;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.BaseController;
import cn.com.sure.common.Constants;
import cn.com.sure.ctml.entry.CertificateTemplate;
import cn.com.sure.ctml.service.CertificateTemplateService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="certificateRequest")
public class CertificateRequestController extends BaseController{
	
	private static final Log LOG = LogFactory.getLog(CertificateRequestController.class);
	
	@Autowired
	private CertificateTemplateService raCertTemplateService;
	
	@Autowired
	private CertificateRequestService raCertificateRequestService;
	
	/**
	 * 
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(CertificateRequest raCertificateRequest,
			Model model, RedirectAttributes attr,HttpServletRequest request) {
		LOG.debug("selectAll - start");
		List<CertificateRequest> raCertificateRequests = new ArrayList<CertificateRequest>();
		
		raCertificateRequests = raCertificateRequestService.findByCondition();
				
		LOG.debug("selectAll - end");
		return new ModelAndView("certificateRequest/certRequestList").addObject("raCertificateRequests", raCertificateRequests);
		
	}
	
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value="forwardInsert")
	public ModelAndView forwardInsert(){
		LOG.debug("forwardInsert - start");
		
		//1.查询证书模板
		//TODO 这里应该是查询option为1的证书模板
		List<CertificateTemplate> raCertTemplates = raCertTemplateService.selectStandby();
		
		LOG.debug("forwardInsert - end");
		return new ModelAndView("cert/certRequestAdd").addObject("raCertTemplates",raCertTemplates);
	}
	
	@RequestMapping(value = "insert")
	public ModelAndView insert(CertificateRequest raCertificateRequest,
			Model model, RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("insert - start");
		
		//1.set进去管理员
		raCertificateRequest.setAdmin((String)request.getSession().getAttribute(Constants.SESSION_ADMIN_NAME));
		raCertificateRequestService.insert(raCertificateRequest);
		
		//2.查询刚才插入的那条信息
		CertificateRequest raCertificateRequests = raCertificateRequestService.findById(raCertificateRequest.getId());
		LOG.debug("insert - end");
		
		return new ModelAndView("certificateRequest/certRequestCheck").addObject("raCertificateRequests", raCertificateRequests);
		
	}
	
	@RequestMapping(value="forwardToSelect")
	public ModelAndView forwardToSelect(){
		LOG.debug("forwardToSelect - start");
		//1.查询证书模板
		//TODO 这里应该是查询option为1的证书模板
		List<CertificateTemplate> raCertTemplates = raCertTemplateService.selectStandby();
		
		LOG.debug("forwardToSelect - end");
		return new ModelAndView("certificateRequest/findCertRequest").addObject("raCertTemplates",raCertTemplates);
		
	}
	
	@RequestMapping(value="searchByCondition")
	public ModelAndView searchByCondition(CertificateRequest raCertificateRequest,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<CertificateRequest> raCertificateRequests = raCertificateRequestService.searchByCondition(raCertificateRequest);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("certificateRequest/findCertRequest").addObject("raCertificateRequests",raCertificateRequests);
	}
	
	@RequestMapping(value="check")
	public String check(CertificateRequest raCertificateRequest,
			Model model, RedirectAttributes attr,HttpServletRequest request) {
		LOG.debug("check - start");
		/*//TODO,需要根据各项是否为空判断重新拼接
		//审核
		//dn,sn, 开始时间，结束时间，密钥类型，请求类型（更新还是新办）,签发证书用的几级根,拓展项
		String reqInfoType = raCertificateRequest.getCertDn()+";"+raCertificateRequest.getStartTime()+";"+raCertificateRequest.getEndTime()
		+";"+raCertificateRequest.getReqType()+";"+raCertTemplateService.findById(raCertificateRequest.getCtmlId()).getResKpgAlgorithm()+";"
				+raCertificateRequest.getReqType()+";"+raCertificateRequest.getRootCertType()+";"+raCertificateRequest.getExtend();
		
		//new Thread(new RaSocketThread(reqInfoType,raCertService)).start();
		
		LOG.debug("check - end");*/
		return "redirect:/raCertRequest/selectAll.do";
		
	}

}
