/**
 * 
 */
package cn.com.sure.kpgtask.web;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;

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
import cn.com.sure.common.BaseController;
import cn.com.sure.common.Constants;
import cn.com.sure.common.KeyPairGenThread;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.common.ReCode;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeService;
import cn.com.sure.syscode.service.SysCodeTypeService;

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
	private SysCodeService sysCodeService;
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;
	
	@Autowired
	private KpgTaskExecuteService kpgTaskExecuteService;
	
	ReCode reCode = new ReCode();
	
	
	/**
	 * 查询所有
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectAll")
	public String selectAll(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		
		PagedQuery pagedQuery=new PagedQuery();

		pagedQuery.setStart(request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start").toString()));
		pagedQuery.setLength(request.getParameter("length")==null?10:Integer.parseInt(request.getParameter("length").toString()));
	    int count = this.kpgTaskService.getSysCodeCount();
		List<KpgTask> kpgTasks = this.kpgTaskService.selectAll(pagedQuery);
		pagedQuery.setRecordsTotal(String.valueOf(count));
		pagedQuery.setRecordsFiltered(pagedQuery.getRecordsTotal());
		pagedQuery.setData(kpgTasks);

		LOG.debug("selectAll - end");
		return JSON.toJSONString(pagedQuery);
		
	}
	
	@RequestMapping(value="toSelectAll")
	public ModelAndView toSelectAll(){
		LOG.debug("toSelectAll - start");
		LOG.debug("toSelectAll - end");
		return new ModelAndView("algorithm/keyPairTaskList");
		
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
	public ReCode insert(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		int i = 0;
		try {
			i = kpgTaskService.insert(kpgTask);
			
		} catch (Applicationexception e) {
			reCode.setDes(e.getMessage());
			reCode.setRetrunCode(Integer.toString(i));
			return reCode;
		}
		LOG.debug("insert - end");
		reCode.setDes("保存【"+kpgTask.getName()+"】成功");
		reCode.setRetrunCode(Integer.toString(i));
		return reCode;
	}
	
	/**
	 * 转向新增
	 * @return
	 */
	@RequestMapping(value="forWardInsert")
	public ModelAndView forWardInsert() {
		LOG.debug("forWardInsert - start");
		KeyPairAlgorithm keyPairAlgorithm = new KeyPairAlgorithm();

		SysCode code = new SysCode();
		SysCodeType sysCodeTypebuf = new SysCodeType();
		sysCodeTypebuf.setParaType(Constants.DB_COMMIT_BUFFER);
		
		List<SysCodeType>codetypesbuf = sysCodeTypeService.searchByCondition(sysCodeTypebuf);
		SysCodeType sysCodeTypesbuf = new SysCodeType();
		sysCodeTypesbuf.setId(codetypesbuf.get(0).getId());
		
		code.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		code.setParaType(sysCodeTypesbuf);
		
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.selectOpYes(keyPairAlgorithm);
		List<SysCode> codeBuf = this.sysCodeService.searchByCondition(code);
		LOG.debug("forWardInsert - end");
		return new ModelAndView("algorithm/keypairTaskInsert").addObject("keyPairAlgorithms",keyPairAlgorithms).addObject("codeBuf",codeBuf);
		
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
	public ReCode update(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		
		int i = kpgTaskService.update(kpgTask);

		LOG.debug("update - end");
		reCode.setDes("修改主键为【"+kpgTask.getId()+"】的信息成功！");
		reCode.setRetrunCode(Integer.toString(i));
		attr.addFlashAttribute("updateSuccess","true");
		return reCode;
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
	public ReCode remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		int i = kpgTaskService.delete(id);
		LOG.debug("remove - end");
		reCode.setDes("删除主键为【"+id+"】成功！");
		reCode.setRetrunCode(Integer.toString(i));
		
		return reCode;
	}
	
	/**
	 * 启动密钥生成任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Applicationexception
	 * @throws NoSuchProviderException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value="startTask")
	public ReCode start(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws NoSuchAlgorithmException, Applicationexception, NoSuchProviderException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		LOG.debug("genKeypair - start");
		Map<String,Object> result = kpgTaskService.startTask(id);
		int i=(int)result.get("i");
		KpgTask kpgTask=(KpgTask)result.get("kpgTask");
		LOG.debug("genKeypair - end");
		
		if(i==1){
			//更新完状态之后去执行生成密钥
			//启动线程，自动生成密钥
			reCode.setDes("启动密钥任务"+id+"成功");
			reCode.setRetrunCode(Integer.toString(i));
			new Thread(new KeyPairGenThread(kpgTaskService,kpgTask,kpgTaskExecuteService,sysCodeService)).start();
		}else {
			reCode.setDes("启动密钥任务"+id+"失败");
			reCode.setRetrunCode(Integer.toString(i));
		}
		return reCode;
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
	 * 停止任务
	 */
	@RequestMapping(value="stop")
	public ReCode stop(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("stop - start");
		int i = kpgTaskService.stop(id);
		LOG.debug("stop - end");
		if(i==1) {
			reCode.setRetrunCode(Integer.toString(i));
			reCode.setDes("暂停密钥任务"+id+"成功");
		}else {
			reCode.setRetrunCode(Integer.toString(i));
			reCode.setDes("暂停密钥任务"+id+"失败");
		}
		
		return reCode;
	}
	
	/**
	 * 继续执行暂停的任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="continuation")
	public ReCode continuation(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("continuation - start");
		Map<String,Object> result = kpgTaskService.continuation(id);
		int i=(int)result.get("i");
		KpgTask kpgTask=(KpgTask)result.get("kpgTask");
		
		LOG.debug("continuation - end");
		
		if(i==1){
			//启动线程，自动生成密钥
			new Thread(new KeyPairGenThread(kpgTaskService,kpgTask,kpgTaskExecuteService,sysCodeService)).start();
			 reCode.setDes("继续执行任务"+id+"成功");
			 reCode.setRetrunCode(Integer.toString(i));
		}
		return reCode;
		
	}
	
	

}
