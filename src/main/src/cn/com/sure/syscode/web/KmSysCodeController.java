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

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.log.service.KmAuditOpLogService;
import cn.com.sure.syscode.entry.KmSysCode;
import cn.com.sure.syscode.entry.KmSysCodeType;
import cn.com.sure.syscode.service.KmSysCodeService;
import cn.com.sure.syscode.service.KmSysCodeTypeService;

@Controller
@RequestMapping(value="syscode")
public class KmSysCodeController {
	
	private static final Log LOG = LogFactory.getLog(KmSysCodeController.class);
	
	@Autowired
	private KmSysCodeService sysCodeService;
	
	@Autowired
	private KmSysCodeTypeService sysCodeTypeService;
	
	@Autowired
	private KmAuditOpLogService auditOpLogService;
	
	Date date = new Date();
	
	/**
	* UC-SYS01-05新增数据字典内容
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "insert")
	public String insert(KmSysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request
			){
		LOG.debug("insert - start!");
		try{
			//执行insert操作
			int i = sysCodeService.insert(sysCode);
			//添加审计日志
			int result;
			if(i==-1){
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
			}else{
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
			}
			auditOpLogService.insert(KmConstants.OPERATION_TYPE_INSERT, "增加", "数据字典", null,
					sysCode.getParaCode(), null, null, date, getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
					result);
		}catch(KmApplicationexception e){
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
	public ModelAndView selectAll(KmSysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<KmSysCode> sysCodes = this.sysCodeService.selectAll();
		List<KmSysCodeType> sysCodeTypes = this.sysCodeTypeService.selectAll(null);
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes).addObject("sysCodeTypes",sysCodeTypes);
	}
	
	
	/**
	* UC-SYS01-02修改数据字典
	* @return "redirect:/syscode/selectAll.do"
	*/
	@RequestMapping(value = "update")
	public String update(
	KmSysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start!");
		
		//添加审计日志
		String str = compare(sysCode);
		int i = sysCodeService.update(sysCode);
		
		//添加审计日志
		int result;
		if(i==-1){
			result=KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result=KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		auditOpLogService.insert(KmConstants.OPERATION_TYPE_UPDATE, "更新", "数据字典", sysCode.getId().toString(), null, null, 
				str, date, getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
				result);
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
		//添加审计日志
		int result;
		if(i==-1){
			 result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			 result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		auditOpLogService.insert(KmConstants.OPERATION_TYPE_DELETE, "删除", "数据字典", id.toString(), null, null, null, 
				date,getIp(request),  (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), result);
		LOG.debug("remove - end!");
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
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.sysCodeService.suspend(id);
    	LOG.debug("suspend - end!");
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
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.sysCodeService.activate(id);
        attr.addFlashAttribute("success", id);
    	LOG.debug("activate - end!");	
    	attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
        return "redirect:/syscode/selectAll.do";
		
	}
	
	/**
	 * UC-SYS01-11按条件查询字典内容
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCondition(KmSysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<KmSysCode> sysCodes  = sysCodeService.searchByCondition(sysCode);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes);
		
	}
	
	
	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
    public  String getIp(HttpServletRequest request) {
           String ip = request.getHeader("X-Forwarded-For");
           if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
               //多次反向代理后会有多个ip值，第一个ip才是真实ip
               int index = ip.indexOf(",");
               if(index != -1){
                   return ip.substring(0,index);
               }else{
                   return ip;
                }
            }
            ip = request.getHeader("X-Real-IP");
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                return ip;
            }
            return request.getRemoteAddr();
       }
    
    
    //比较更新了那些字段
    public String compare(KmSysCode sysCodeNew){
    	String resultString="";
    	if(sysCodeNew!=null&&!"".equals(sysCodeNew)){
    		KmSysCode sysCodeDB = sysCodeService.selectById(sysCodeNew.getId());
    		if(StringUtils.isNotBlank(sysCodeDB.getParaCode())&&StringUtils.isNotBlank(sysCodeNew.getParaCode())){
    			if(!sysCodeDB.getParaCode().equals(sysCodeNew.getParaCode())){
    				resultString+="记录参数名称由"+sysCodeDB.getParaCode()+"变更为"+sysCodeNew.getParaCode();
    			}
    		}if(StringUtils.isNotBlank(sysCodeDB.getParaValue())&&StringUtils.isNotBlank(sysCodeNew.getParaValue())){
    			if(!sysCodeDB.getParaValue().equals(sysCodeNew.getParaValue())){
    				resultString+="参数值由"+sysCodeDB.getParaValue()+"变更为"+sysCodeNew.getParaValue();
    			}
    		}if(StringUtils.isNotBlank((CharSequence) sysCodeDB.getParaType())&&StringUtils.isNotBlank((CharSequence) sysCodeNew.getParaType())){
    			if(!sysCodeDB.getParaType().equals(sysCodeNew.getParaType())){
    				resultString+="参数类别由"+sysCodeDB.getParaValue()+"变更为"+sysCodeNew.getParaValue();
    			}
    		}
    	}
		return resultString;
    	
    }

}
