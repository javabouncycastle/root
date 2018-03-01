/**
 * 
 */
package cn.com.sure.keypair.web;

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

import cn.com.sure.keypair.entry.KeypairInuse;
import cn.com.sure.keypair.service.KeypairInuseService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="keypairInuse")
public class KeypairInuseController {
	
	private static final Log LOG = LogFactory.getLog(KeypairInuseController.class);
	
	@Autowired
	private KeypairInuseService keypairInuseService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(){
		LOG.debug("selectAll - start");
		List<KeypairInuse> keypairInuses = keypairInuseService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/keypairInuseList").addObject("keypairInuses", keypairInuses);
		
	}
	
	@RequestMapping(value="searchByCondition")
	public ModelAndView searchByCondition(KeypairInuse KeypairInuse, Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<KeypairInuse> keypairInuses = keypairInuseService.searchByCondition(KeypairInuse);
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/keypairInuseList").addObject("keypairInuses", keypairInuses);
		
	}
}
