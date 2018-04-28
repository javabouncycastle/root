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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.ReCode;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keyPairAlgorithmService;
	
	ReCode reCode = new ReCode();
	
	/**
	 * UC-ALG02-01 新增密钥算法
	 */
	@ResponseBody
	@RequestMapping(value="insert")
	public ReCode insert(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		int i=0;
		try{
			//执行insert操作
			 i = keyPairAlgorithmService.insert(keyPairAlgorithm);
		}catch(Applicationexception e){
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			
			return reCode;
		}
		LOG.debug("insert - end");
		
		reCode.setRetrunCode(Integer.toString(i));
		reCode.setDes("保存【"+keyPairAlgorithm.getName()+"】成功");
		
		return reCode;
		
	}
	
	/**
	 * UC- ALG02-06查询所有密钥算法
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List <KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", JSON.toJSON(keyPairAlgorithms));
		
	}
	
	/**
	 * UC- ALG02-02修改密钥算法
	 * @throws Applicationexception 
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public ReCode update(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws Applicationexception{
		LOG.debug("update - start");
		int i = 0 ;
		try{
			//执行update操作
			i = keyPairAlgorithmService.update(keyPairAlgorithm);
		}catch(Applicationexception e){
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		LOG.debug("update - end");
		reCode.setRetrunCode(Integer.toString(i));
		reCode.setDes("修改主键为【"+keyPairAlgorithm.getId()+"】的信息成功！");
		return reCode;
	}
	
	
	/**
	 *  UC- ALG02-03删除密钥算法
	 */
	@ResponseBody
	@RequestMapping(value="remove")
	public ReCode delete(
			@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		int i = keyPairAlgorithmService.delete(id);
		LOG.debug("delete - end");
		if(i==1) {
			reCode.setDes("删除主键为【"+id+"】的密钥算法成功！");
			reCode.setRetrunCode(Integer.toString(i));
		}else {
			reCode.setDes("删除主键为【"+id+"】的密钥算法失败！");
			reCode.setRetrunCode(Integer.toString(i));
		}
		
		return reCode;
		
	}
	
	
	/**
	* UC- ALG02-04停用密钥算法
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "suspend")
	public ReCode suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr ,HttpServletRequest re){
		LOG.debug("suspend - start!");
		int i = keyPairAlgorithmService.suspend(id);
		if(i==1) {
			reCode.setDes("停用主键为【"+id+"】的密钥算法成功！");
			reCode.setRetrunCode(Integer.toString(i));
		}else {
			reCode.setDes("停用主键为【"+id+"】的密钥算法失败！");
			reCode.setRetrunCode(Integer.toString(i));
		}
		LOG.debug("suspend - end!");
		return reCode;
    	
	}
	
	/**
	 *  UC- ALG02-05启用密钥算法
	 * @return "redirect:/list"
	 */
	@RequestMapping(value = "activate")
	@ResponseBody
	public ReCode activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		int i = keyPairAlgorithmService.activate(id);
		if(i==1) {
			reCode.setDes("启用主键为【"+id+"】的密钥算法成功！");
			reCode.setRetrunCode(Integer.toString(i));
		}else {
			reCode.setDes("启用主键为【"+id+"】的密钥算法失败！");
			reCode.setRetrunCode(Integer.toString(i));
		}
    	LOG.debug("activate - end!");
    	return reCode;
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
	
	@RequestMapping(value = "forWardInsert")
	public String forWardInsert() {
		LOG.debug("forWardInsert - start");
		LOG.debug("forWardInsert - end");
		return "algorithm/kpAlgInsert";
	}
	
	@RequestMapping(value = "forWardUpdate")
	public ModelAndView forWardUpdate(@RequestParam(value = "id", required = false)Long id) {
		LOG.debug("forWardInsert - start");
		KeyPairAlgorithm keyPairAlgorithm = keyPairAlgorithmService.selectById(id);
		LOG.debug("forWardInsert - end");
		return new ModelAndView("algorithm/kpAlgEdit").addObject("keyPairAlgorithm", keyPairAlgorithm);
	}
}
