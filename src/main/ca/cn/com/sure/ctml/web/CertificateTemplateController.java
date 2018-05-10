/**
 * 
 */
package cn.com.sure.ctml.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.common.ReCode;
import cn.com.sure.ctml.entry.CertificateTemplate;
import cn.com.sure.ctml.service.CertificateTemplateService;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeService;
import cn.com.sure.syscode.service.SysCodeTypeService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="certificateTemplate")
public class CertificateTemplateController {
	
	@Autowired
	private CertificateTemplateService certTemplateService;
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;
	
	@Autowired
	private KeypairAlgorithmService keyPairAlgorithmService;
	
	ReCode reCode = new ReCode();
	
	private static final Log LOG = LogFactory.getLog(CertificateTemplateController.class);
	
	/**
	 * UC-SYS04-01查询全部证书模板
	 */
	@RequestMapping(value = "selectAll")
	@ResponseBody
	public String selectAll(CertificateTemplate certificateTemplate,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		
		PagedQuery pageQuery=new PagedQuery();

		pageQuery.setStart(request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start").toString()));
		pageQuery.setLength(request.getParameter("length")==null?10:Integer.parseInt(request.getParameter("length").toString()));
	    int count = certTemplateService.count();
	    List<CertificateTemplate> certificateTemplates=certTemplateService.selectAll(pageQuery);
	    pageQuery.setRecordsTotal(String.valueOf(count));
	    pageQuery.setRecordsFiltered(pageQuery.getRecordsTotal());
	    pageQuery.setData(certificateTemplates);
		
	    LOG.debug("selectAll - end");
	    
	    return JSON.toJSONString(pageQuery);
		
	}
	/**
	 * 跳转到前台页面之后再去执行selectAll的查询 目的是为了实现分页查询
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toSelectAll")
	public ModelAndView toSelectAll(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("toSelectAll - start");
		LOG.debug("toSelectAll - end");
		return new ModelAndView("ctml/ctmlList");
	}
	
	/**
	 * UC-SYS04-02新增证书模板
	 */
	@ResponseBody
	@RequestMapping(value = "insert")
	public ReCode insert(CertificateTemplate certTemplate,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		int i = 0;
		try {
			i = certTemplateService.insert(certTemplate);
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		LOG.debug("insert - end");
		reCode.setDes("保存【"+certTemplate.getCtmlName()+"】成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
		
	}
	
	/**
	 * 转向更新
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "forWardUpdate")
	public ModelAndView forWardUpdate(@RequestParam(value = "id", required = false)String id) {
		LOG.debug("forWardInsert - start");
		CertificateTemplate certificateTemplate = certTemplateService.findById(id);
		
		SysCode sysCode = new SysCode();
		SysCodeType codeType = new SysCodeType();
		SysCodeType sysCodeTypes = new SysCodeType();
		//2.密钥类型
		KeyPairAlgorithm keyPairAlgorithm = new KeyPairAlgorithm();
		keyPairAlgorithm.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<KeyPairAlgorithm> keypairAlgorithms = keyPairAlgorithmService.selectOpYes(keyPairAlgorithm);
		
		//3.签名证书用法
		codeType.setParaType(Constants.SIGN_CERT_KEY_USAGE);
		List<SysCodeType>typekeyuse = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typekeyuse.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeSignUse = sysCodeService.searchByCondition(sysCode);
		
		//4.加密证书用法
		codeType.setParaType(Constants.ENC_CERT_KEY_USAGE);
		List<SysCodeType>typeEncuse = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeEncuse.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeEncUse = sysCodeService.searchByCondition(sysCode);
		
		//5.增强型密钥用法
		codeType.setParaType(Constants.EXTEND_KEY_USAGE);
		List<SysCodeType>typeEKU = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeEKU.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeExKeyUse = sysCodeService.searchByCondition(sysCode);
		
		//6.证书拓展项
		codeType.setParaType(Constants.CERT_EXTEND);
		List<SysCodeType>typeExtend = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeExtend.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeExtend = sysCodeService.searchByCondition(sysCode);
		LOG.debug("forWardInsert - end");
		return new ModelAndView("ctml/ctmlEdit").addObject("certificateTemplate", certificateTemplate).addObject("keypairAlgorithms",keypairAlgorithms).
				addObject("sysCodeSignUse",sysCodeSignUse).addObject("sysCodeEncUse",sysCodeEncUse).addObject("sysCodeExKeyUse",sysCodeExKeyUse).addObject("sysCodeExtend",sysCodeExtend);
	}
	
	/**
	 * UC-SYS04-03修改证书模板
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public ReCode update(CertificateTemplate certTemplate,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		int i = 0;
		try {
			i = certTemplateService.update(certTemplate);
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		
		LOG.debug("update - end");
		return reCode;
		
	}
	
	/**
	 * UC-SYS04-04删除证书模板
	 */
	@ResponseBody
	@RequestMapping(value = "remove")
	public ReCode remove(
	@RequestParam(value = "id", required = false)String id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start!");
		int i = certTemplateService.remove(id);
		LOG.debug("remove - end");
		reCode.setDes("删除主键为【"+id+"】信息成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
	}
	
	/**
	 * UC-SYS04-05启用证书模板
	 */
	@RequestMapping(value="activate")
	public ReCode activate(@RequestParam(value = "id", required = false)String id,
			Model model,RedirectAttributes attr){
		LOG.debug("activate - start");
		int i = certTemplateService.activite(id);
		LOG.debug("activate - end");
		if(i==1) {
			reCode.setDes("启用主键为【"+id+"】成功");
			reCode.setRetrunCode(Integer.toString(i));
		}else {
			reCode.setDes("启用主键为【"+id+"】失败");
			reCode.setRetrunCode(Integer.toString(i));
		}
		return reCode;
		
	}
	
	/**
	 * UC-SYS04-06停用证书模板
	 */
	@RequestMapping(value="suspend")
	public ReCode suspend(@RequestParam(value = "id", required = false)String id,
			Model model,RedirectAttributes attr){
		LOG.debug("suspend - start");
		int i = certTemplateService.suspend(id);
		LOG.debug("suspend - end");
		if(i==1) {
			reCode.setDes("停用主键为【"+id+"】成功");
			reCode.setRetrunCode(Integer.toString(i));
		}else {
			reCode.setDes("停用主键为【"+id+"】失败");
			reCode.setRetrunCode(Integer.toString(i));
		}
		return reCode;
		
	}
	
	/**
	 * UC-SYS04-07转向新增
	 */
	@RequestMapping(value="forWardInsert")
	public ModelAndView forWardInsert(){
		LOG.debug("forWardInsert - start");
		SysCode sysCode = new SysCode();
		SysCodeType codeType = new SysCodeType();
		SysCodeType sysCodeTypes = new SysCodeType();
		//2.密钥类型
		KeyPairAlgorithm keyPairAlgorithm = new KeyPairAlgorithm();
		keyPairAlgorithm.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<KeyPairAlgorithm> keypairAlgorithms = keyPairAlgorithmService.selectOpYes(keyPairAlgorithm);
		
		//3.签名证书用法
		codeType.setParaType(Constants.SIGN_CERT_KEY_USAGE);
		List<SysCodeType>typekeyuse = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typekeyuse.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeSignUse = sysCodeService.searchByCondition(sysCode);
		
		//4.加密证书用法
		codeType.setParaType(Constants.ENC_CERT_KEY_USAGE);
		List<SysCodeType>typeEncuse = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeEncuse.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeEncUse = sysCodeService.searchByCondition(sysCode);
		
		//5.增强型密钥用法
		codeType.setParaType(Constants.EXTEND_KEY_USAGE);
		List<SysCodeType>typeEKU = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeEKU.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeExKeyUse = sysCodeService.searchByCondition(sysCode);
		
		//6.证书拓展项
		codeType.setParaType(Constants.CERT_EXTEND);
		List<SysCodeType>typeExtend = sysCodeTypeService.searchByCondition(codeType);
		sysCodeTypes.setId(typeExtend.get(0).getId());
		sysCode.setParaType(sysCodeTypes);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodeExtend = sysCodeService.searchByCondition(sysCode);
		
		LOG.debug("forWardInsert - end");
		return new ModelAndView("ctml/ctmlInsert").addObject("keypairAlgorithms",keypairAlgorithms).addObject("sysCodeSignUse",
				sysCodeSignUse).addObject("sysCodeEncUse", sysCodeEncUse).addObject("sysCodeExKeyUse", sysCodeExKeyUse).addObject("sysCodeExtend",sysCodeExtend);
		
	}
}
