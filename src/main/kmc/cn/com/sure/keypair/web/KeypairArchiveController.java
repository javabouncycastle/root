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

import cn.com.sure.keypair.entry.KeypairArchive;
import cn.com.sure.keypair.service.KeypairArchiveService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="kpArchive")
public class KeypairArchiveController {
	
	private static final Log LOG = LogFactory.getLog(KeypairArchiveController.class);
	
	@Autowired
	private KeypairArchiveService keypairArchiveService;
	
	/**
	 * 
	 * @param keypairArchive
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeypairArchive keypairArchive,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<KeypairArchive> keypairArchives=keypairArchiveService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/kpArchList").addObject("keypairArchives", keypairArchives);
		
	}

}
