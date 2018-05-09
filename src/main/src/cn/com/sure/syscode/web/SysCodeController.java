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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.common.ReCode;
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

	ReCode reCode = new ReCode();
	
	Date date = new Date();
	
	/**
	* UC-SYS01-05新增数据字典内容
	* @return "redirect:/list"
	*/
	@ResponseBody
	@RequestMapping(value = "insert")
	public ReCode insert(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request
			){
		LOG.debug("insert - start!");
		int i =0;
		try{
			//执行insert操作
			 i = sysCodeService.insert(sysCode);
		}catch(Applicationexception e){
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			
			return reCode;
		}
		LOG.debug("insert - end!");
		reCode.setDes("保存【"+sysCode.getParaCode()+"】成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
		
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
	@ResponseBody
	public String selectAll(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		PagedQuery pageVo=new PagedQuery();

	    pageVo.setStart(request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start").toString()));
	    pageVo.setLength(request.getParameter("length")==null?10:Integer.parseInt(request.getParameter("length").toString()));
		int count =this.sysCodeService.getSysCodeCount();
		List<SysCode> sysCodes = this.sysCodeService.selectAll(pageVo);
		pageVo.setRecordsTotal(String.valueOf(count));
		pageVo.setRecordsFiltered(pageVo.getRecordsTotal());
		pageVo.setData(sysCodes);
		LOG.debug("selectAll - end");
		return JSON.toJSONString(pageVo);
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
		return new ModelAndView("syscode/syscodeList");
	}
	
	
	/**
	* UC-SYS01-02修改数据字典
	* @return "redirect:/syscode/selectAll.do"
	 * @throws Applicationexception 
	*/
	@ResponseBody
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public ReCode update(
	SysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start!");
		int i = 0 ;
		try{
			//执行update操作
			i = sysCodeService.update(sysCode);
		}catch(Applicationexception e){
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		
		LOG.debug("update - end!");
		reCode.setDes("修改【"+sysCode.getParaCode()+"】信息成功");
		reCode.setRetrunCode(Integer.toString(i));
		return  reCode;
		
	}
	
	
	/**
	* UC-SYS01-03删除数据字典
	* @return "redirect:/syscode/selectAll.dot"
	*/
	@ResponseBody
	@RequestMapping(value = "remove")
	public ReCode remove(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start!");
		int i = 0;
		try {
			i = sysCodeService.remove(id);
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			
			return reCode;
		}
		
		reCode.setDes("删除主键为【"+id+"】信息成功");
		reCode.setRetrunCode(Integer.toString(i));
		return  reCode;
		
	}
	
	
	
	/**
	*  UC-SYS01-09停用数据字典内容
	* @return "redirect:/syscode/selectAll.do"
	*/
	@ResponseBody
	@RequestMapping(value = "suspend")
	public ReCode suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("suspend - start!");
		int i = sysCodeService.suspend(id);
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
	 *   UC-SYS01-10启用数据字典内容
	 */
	@ResponseBody
	@RequestMapping(value = "activate")
	public ReCode activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("activate - start!");
		int i = sysCodeService.activate(id);
        attr.addFlashAttribute("success", id);
    	LOG.debug("activate - end!");	
    	
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
	 * UC-SYS01-11按条件查询字典内容
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCondition(SysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<SysCode> sysCodes  = sysCodeService.searchByCondition(sysCode);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes);
		
	}
	
	/**
	 * 转向新增
	 * @return
	 */
	@RequestMapping(value="forWardInsert")
	public ModelAndView forWardInsert() {
		LOG.debug("forWardInsert - start");
		List<SysCodeType> sysCodeTypes = sysCodeTypeService.selectAll();
		LOG.debug("forWardInsert - end");
		return new ModelAndView("syscode/syscodeInsert").addObject("sysCodeTypes",sysCodeTypes);
	}
	
	/**
	 * 转向更新
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "forWardUpdate")
	public ModelAndView forWardUpdate(@RequestParam(value = "id", required = false)Long id) {
		LOG.debug("forWardInsert - start");
		SysCode syscode = sysCodeService.selectById(id);
		List<SysCodeType> sysCodeTypes = sysCodeTypeService.selectAll();
		LOG.debug("forWardInsert - end");
		return new ModelAndView("syscode/syscodeEdit").addObject("syscode", syscode).addObject("sysCodeTypes",sysCodeTypes);
	}
	

}
