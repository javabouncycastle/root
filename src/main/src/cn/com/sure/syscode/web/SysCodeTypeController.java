/**
 * 
 */
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.ReCode;
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
	
	Date date = new Date();
	
	ReCode reCode = new ReCode();
	
	/**
	 * UC-SYS01-01 新增数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws Applicationexception 
	 */
	@ResponseBody
	@RequestMapping(value = "insert")
	public ReCode insert(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request) {
		LOG.debug("insert - start");
		int i = 0 ;
		try {
			i = sysCodeTypeService.insert(sysCodeType);
			// 添加审计日志
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		LOG.debug("insert - end");
		reCode.setDes("保存【"+sysCodeType.getParaType()+"】成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
		
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
	@ResponseBody
	@RequestMapping(value = "update")
	public ReCode update(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start");
		int i = 0;
		try {
			i = sysCodeTypeService.update(sysCodeType);
			
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		
		LOG.debug("update - end");
		reCode.setDes("修改数据字典类别=【"+sysCodeType.getParaType()+"】信息成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
	}
	
	/**
	 * UC-SYS01-03删除数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "remove")
	public ReCode remove(@RequestParam(value = "id", required = false)Long id,
			Model model, RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("remove - start");
		int i = 0;
		try {
			i = sysCodeTypeService.delete(id);
			// 添加审计日志
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		
		LOG.debug("remove - end");
		reCode.setDes("删除主键为【"+id+"】信息成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
		
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
		List<SysCodeType> sysCodeTypes=this.sysCodeTypeService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeTypeList").addObject("sysCodeTypes", JSON.toJSON(sysCodeTypes));
		
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
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "forWardInsert")
	public String forWardInsert() {
		LOG.debug("forWardInsert - start");
		LOG.debug("forWardInsert - end");
		return "syscode/syscodeTypeInsert";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "forWardUpdate")
	public ModelAndView forWardUpdate(@RequestParam(value = "id", required = false)Long id) {
		LOG.debug("forWardInsert - start");
		SysCodeType syscodeType = sysCodeTypeService.selectById(id);
		LOG.debug("forWardInsert - end");
		return new ModelAndView("syscode/syscodeTypeEdit").addObject("syscodeType", syscodeType);
	}


}
