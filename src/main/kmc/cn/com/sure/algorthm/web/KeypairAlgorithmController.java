package cn.com.sure.algorthm.web;

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

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keyPairAlgorithmService;
	
	
	/**
	 * UC-ALG02-01 新增密钥算法
	 */
	@RequestMapping(value="insert")
	public String insert(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//执行insert操作
			int i = keyPairAlgorithmService.insert(keyPairAlgorithm);
		}catch(Applicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keyPairAlgorithm",keyPairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+keyPairAlgorithm.getName()+"】成功");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 * UC- ALG02-06查询所有密钥算法
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		LOG.debug("selectAll - start");
		List <KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
	
	/**
	 * UC- ALG02-02修改密钥算法
	 * @throws Applicationexception 
	 */
	@RequestMapping(value="update")
	public String update(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start");
		try{
			//执行update操作
			int i = keyPairAlgorithmService.update(keyPairAlgorithm);
		}catch(Applicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keyPairAlgorithm",keyPairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+keyPairAlgorithm.getId()+"】的信息成功！");
		return "redirect:/algorithm/selectAll.do";
	}
	
	
	/**
	 *  UC- ALG02-03删除密钥算法
	 */
	@RequestMapping(value="remove")
	public String delete(
			@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		int i = keyPairAlgorithmService.delete(id);
		int result;
		if(i==-1){
			result = Constants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result = Constants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		/*auditOpLogService.insert(Constants.OPERATION_TYPE_DELETE, "删除", "数据字典类别", id.toString(), null, null, null, 
				new Date(),getIp(request),  (String)request.getSession().getAttribute(Constants.SESSION_ADMIN_NAME), result);*/
		LOG.debug("delete - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】成功！");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	
	/**
	* UC- ALG02-04停用密钥算法
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.keyPairAlgorithmService.suspend(id);
        attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","停用主键为【"+id+"】成功");
    	LOG.debug("suspend - end!");
        return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 *  UC- ALG02-05启用密钥算法
	 * @return "redirect:/list"
	 */
	
	
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.keyPairAlgorithmService.activate(id);
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
    	LOG.debug("activate - end!");		
        return "redirect:/algorithm/selectAll.do";
		
	}
	

	/**
	 * UC- ALG02-07按条件查询密钥算法
	 * @param keypairAlgorithm
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCOndition(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCOndition - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.searchByCondition(keyPairAlgorithm);
		LOG.debug("searchByCOndition - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
    
}
