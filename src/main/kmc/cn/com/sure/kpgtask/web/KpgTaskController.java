/**
 * 
 */
package cn.com.sure.kpgtask.web;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.BaseController;
import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.log.service.KmAuditOpLogService;
import cn.com.sure.syscode.entry.KmSysCode;
import cn.com.sure.syscode.service.KmSysCodeService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="kpgTask")
public class KpgTaskController extends BaseController{
	
	private static final Log LOG = LogFactory.getLog(KpgTaskController.class);
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KeypairAlgorithmService keyPairAlgorithmService;
	
	@Autowired
	private KmSysCodeService sysCodeService;
	
	@Autowired
	private KmAuditOpLogService auditOpLogService;
	
	@Autowired KpgTaskExecuteService kpgTaskExecuteService;
	
	
	/**
	 * 查询所有
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		KeyPairAlgorithm keyPairAlgorithm = new KeyPairAlgorithm();
		KmSysCode sysCode = new KmSysCode();
		List<KpgTask> kpgTasks = this.kpgTaskService.selectAll();
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.selectOpYes(keyPairAlgorithm);
		List<KmSysCode> codeBuf = this.sysCodeService.selectBufSize(sysCode);
		List<KmSysCode> sysCodes = this.sysCodeService.selectByType(sysCode);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairTaskList").addObject("kpgTasks", kpgTasks).addObject("keyPairAlgorithms",keyPairAlgorithms).addObject("sysCodes",sysCodes).addObject("codeBuf",codeBuf);
		
	}
	
	/**
	 * 增加密钥任务
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="insert")
	public String insert(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try {
			int i = kpgTaskService.insert(kpgTask);
			//添加是否成功
			int result;
			if(i==-1){
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
			}else{
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
			}
			// 添加审计日志
			auditOpLogService.insert(KmConstants.OPERATION_TYPE_INSERT, "增加", "数据字典类别", null,
					kpgTask.getName(), null, null, new Date(), getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
					result);
		} catch (KmApplicationexception e) {
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("kpgTask",kpgTask);
			return "redirect:/kpgTask/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+kpgTask.getName()+"】成功");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 更新密钥任务
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		//比较更新前后字段的变化
		String str = compare(kpgTask);
		int i = kpgTaskService.update(kpgTask);
		int result;
		if(i==-1){
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		//添加审计日志
		auditOpLogService.insert(KmConstants.OPERATION_TYPE_UPDATE, "更新", "数据字典类别", kpgTask.getId().toString(), null, null, 
				str, new Date(), getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
				result);
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+kpgTask.getId()+"】的信息成功！");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 删除密钥任务
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
		this.kpgTaskService.delete(id);
		LOG.debug("remove - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】成功！");
		return "redirect:/kpgTask/selectAll.do";
		
	}
	
	/**
	 * 启动密钥生成任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KmApplicationexception
	 * @throws NoSuchProviderException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value="start")
	public String genKeypair(Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws NoSuchAlgorithmException, KmApplicationexception, NoSuchProviderException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		LOG.debug("genKeypair - start");
		kpgTaskService.start(id);
		LOG.debug("genKeypair - end");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 按条件查询
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCondition(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<KpgTask> kpgTasks = this.kpgTaskService.searchByCondition(kpgTask);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("algorithm/keyPairTaskList").addObject("kpgTasks", kpgTasks);
		
	}
	
	/**
	 * 暂停
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	public String suspend(Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("suspend - start");
		kpgTaskService.suspend(id);
		LOG.debug("suspend - end");
		return "redirect:/kpgTask/selectAll.do";
		
	}
	
	/**
	 * 停止任务
	 */
	public String stop(Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("stop - start");
		kpgTaskService.stop(id);
		LOG.debug("stop - end");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 继续执行暂停的任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	public String continuation(Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("continuation - start");
		kpgTaskService.continuation(id);
		LOG.debug("continuation - end");
				return "redirect:/kpgTask/selectAll.do";
		
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
    public String compare(KpgTask kpgTaskNew){
    	String resultString="";
		
    	if(kpgTaskNew!=null&&!"".equals(kpgTaskNew)&&kpgTaskNew.getId()!=null){
    		//查询数据库中未更新前的数据
    		KpgTask kpgTaskDB = kpgTaskService.selectById(kpgTaskNew.getId());
    		if(StringUtils.isNotBlank(kpgTaskNew.getName())&&StringUtils.isNotBlank(kpgTaskDB.getName())){
    			if(!kpgTaskNew.getName().equals(kpgTaskDB.getName())){
    				resultString+="别名由"+kpgTaskDB.getName()+"变更为"+kpgTaskNew.getName();
    			}
    		}if(kpgTaskDB.getKeyPairAlgorithm()!=null&&kpgTaskNew.getKeyPairAlgorithm()!=null){
    			if(!kpgTaskDB.getKeyPairAlgorithm().equals(kpgTaskNew.getKeyPairAlgorithm())){
    				resultString+="密钥算法由"+kpgTaskDB.getKeyPairAlgorithm()+"变更为"+kpgTaskNew.getKeyPairAlgorithm();
    			}
    		}
    	}
    	
		return resultString;
    	
    }

}
