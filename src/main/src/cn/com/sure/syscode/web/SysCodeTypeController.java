/**
 * 
 */
package cn.com.sure.syscode.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import cn.com.sure.log.test.service.AuditOpLogService;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeTypeService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="syscodetype")
public class SysCodeTypeController {
	
	private static final Log LOG = LogFactory.getLog(SysCodeTypeController.class);
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;
	
	@Autowired
	private AuditOpLogService auditOpLogService;
	
	Date date = new Date();
	
	AuditOpLog auditOpLog = new AuditOpLog();
	
	/**
	 * UC-SYS01-01 新增数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws Applicationexception 
	 */
	@RequestMapping(value = "insert")
	public String insert(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request) {
		LOG.debug("insert - start");
		try {
			int i = sysCodeTypeService.insert(sysCodeType);
			// 添加审计日志
		} catch (Applicationexception e) {
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("sysCodeType",sysCodeType);
			return "redirect:/syscodetype/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+sysCodeType.getParaType()+"】成功");
		return "redirect:/syscodetype/selectAll.do";
		
	}
	
	/**
	 * UC-SYS01-02修改数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws Applicationexception 
	 */
	@RequestMapping(value = "update")
	public String update(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start");
		
		try {
			int i = sysCodeTypeService.update(sysCodeType);
			// 添加审计日志
		} catch (Applicationexception e) {
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("sysCodeType",sysCodeType);
			return "redirect:/syscodetype/selectAll.do";
		}
		
		//添加审计日志
		LOG.debug("update - start");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","修改数据字典类别=【"+sysCodeType.getParaType()+"】信息成功");
				return "redirect:/syscodetype/selectAll.do";
	}
	
	/**
	 * UC-SYS01-03删除数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		int i = sysCodeTypeService.delete(id);
		int result ;
		if(i==-1){
			result = Constants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result = Constants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		/*auditOpLogService.insert(Constants.OPERATION_TYPE_DELETE, "删除", "数据字典类别", id.toString(), null, null, null, 
				date,getIp(request),  (String)request.getSession().getAttribute(Constants.SESSION_ADMIN_NAME), result);*/
		LOG.debug("remove - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】信息成功");
				return "redirect:/syscodetype/selectAll.do";
		
	}
	
	/**
	 * UC-SYS01-04查询全部数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectAll")	
	public ModelAndView selectAll(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<SysCodeType> sysCodeTypes=this.sysCodeTypeService.selectAll(sysCodeType);
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeTypeList").addObject("sysCodeTypes", sysCodeTypes);
		
	}
	
	
	/**
	 * UC-SYS01-12按条件查询字典类别
	 * @param sysCodeType
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")	
	public ModelAndView searchByCondition(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<SysCodeType> sysCodeTypes=this.sysCodeTypeService.searchByCondition(sysCodeType);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("syscode/syscodeTypeList").addObject("sysCodeTypes", sysCodeTypes);
		
	}


}
