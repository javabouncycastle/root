/**
 * 
 */
package cn.com.sure.keypair.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.common.BaseController;
import cn.com.sure.keypair.entry.KeypairStandby;
import cn.com.sure.keypair.standby.service.KeypairStandbyService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="keypairStandby")
public class KeypairStandbyControler extends BaseController{
	
	private static final Log LOG = LogFactory.getLog(KeypairStandbyControler.class);
	
	@Autowired
	private KeypairStandbyService keyPairStandbyService;
	
	/**
	 * UC-SYS05-04密钥查询
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(){
		LOG.debug("selectAll - start");
		List<KeypairStandby> keyPairStandbys = keyPairStandbyService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/keyPairStandbyList").addObject("keyPairStandbys", keyPairStandbys);
		
	}
	
	/**
	 * UC-SYS05-02删除密钥
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		keyPairStandbyService.delete(String.valueOf(id));
		LOG.debug("remove - end");
		return "redirect:/keypairStandby/selectAll.do";
		
	}
	
	/**
	 * UC-SYS05-03密钥备份
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="backups")
	public String backups(KeypairStandby keypairStandby, HttpServletResponse response){
		LOG.debug("backups - start");
		 response.setContentType("application/binary;charset=UTF-8");
		LOG.debug("backups - end");
		return "redirect:/keypairStandby/selectAll.do";
	}
	
	@RequestMapping(value="searchByCondition")
	public ModelAndView searchByCondition(KeypairStandby keypairStandby, Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<KeypairStandby> keyPairStandbys=keyPairStandbyService.searchByCondition(keypairStandby);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("keyPair/keyPairStandbyList").addObject("keyPairStandbys", keyPairStandbys);
		
	}

}
	
