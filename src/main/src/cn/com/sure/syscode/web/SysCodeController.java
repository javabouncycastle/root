package cn.com.sure.syscode.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.log.entry.AuditOpLog;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeService;
import cn.com.sure.syscode.service.SysCodeTypeService;

@Controller
@RequestMapping(value="syscode")
public class SysCodeController {
	
	private static final Log LOG = LogFactory.getLog(SysCodeController.class);
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;

	
	Date date = new Date();
	
	AuditOpLog auditOpLog = new AuditOpLog();
	
	/**
	* UC-SYS01-05新增数据字典内容
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "insert")
	public String insert(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request
			){
		LOG.debug("insert - start!");
		try{
			//执行insert操作
			 sysCodeService.insert(sysCode);
		}catch(Applicationexception e){
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("frSysCode",sysCode);
			return "redirect:/syscode/selectAll.do";
		}
		LOG.debug("insert - end!");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+sysCode.getParaCode()+"】成功");
		return "redirect:/syscode/selectAll.do";
		
	}
	
	/**
	 * UC-SYS01-08查询数据字典内容
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<SysCode> sysCodes = this.sysCodeService.selectAll();
		List<SysCodeType> sysCodeTypes = this.sysCodeTypeService.selectAll(null);
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes).addObject("sysCodeTypes",sysCodeTypes);
	}
	
	
	/**
	* UC-SYS01-02修改数据字典
	* @return "redirect:/syscode/selectAll.do"
	 * @throws Applicationexception 
	*/
	@RequestMapping(value = "update")
	public String update(
	SysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start!");
		try{
			//执行update操作
			int i = sysCodeService.update(sysCode);
			//添加审计日志
		}catch(Applicationexception e){
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("frSysCode",sysCode);
			return "redirect:/syscode/selectAll.do";
		}
		
		LOG.debug("update - end!");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","修改【"+sysCode.getParaCode()+"】信息成功");
		return  "redirect:/syscode/selectAll.do";
		
	}
	
	
	/**
	* UC-SYS01-03删除数据字典
	* @return "redirect:/syscode/selectAll.dot"
	*/
	@RequestMapping(value = "remove")
	public String remove(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start!");
		int i =  sysCodeService.remove(id);
		
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】信息成功");	
		return  "redirect:/syscode/selectAll.do";
		
	}
	
	
	
	/**
	*  UC-SYS01-09停用数据字典内容
	* @return "redirect:/syscode/selectAll.do"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("suspend - start!");
		int i =  sysCodeService.suspend(id);
    	
    	attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","停用主键为【"+id+"】成功");
        return "redirect:/syscode/selectAll.do";
		
	}
	
	
	
	/**
	 *   UC-SYS01-10启用数据字典内容
	 */
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("activate - start!");
		int i =  sysCodeService.activate(id);
		int result;
		if(i==-1){
			 result = Constants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			 result = Constants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
        attr.addFlashAttribute("success", id);
    	LOG.debug("activate - end!");	
    	/*// 添加审计日志
		auditOpLog.setType(Constants.OPERATION_TYPE_ACTIVE);
		auditOpLog.setAction(Constants.OPERATION_TYPE_ACT);
		auditOpLog.setActionExt1(Constants.OPERATION_TYPE_NAME);
		auditOpLog.setActionExt2(id.toString());
		auditOpLog.setActionExt3("");
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		auditOpLog.setTimestamp(date);
		auditOpLog.setIp(getIp(request));
		auditOpLog.setOperator((String)request.getSession().getAttribute(Constants.SESSION_ADMIN_NAME));
		auditOpLog.setIsOpSucc(result);
		
		auditOpLogService.insert(auditOpLog);*/
    	attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
        return "redirect:/syscode/selectAll.do";
		
	}
	
	/**
	 * UC-SYS01-11按条件查询字典内容
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCondition(SysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<SysCode> sysCodes  = sysCodeService.searchByCondition(sysCode);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes);
		
	}
	

}
