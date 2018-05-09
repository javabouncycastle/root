/**
 * 
 */
package cn.com.sure.kpgtask.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.ErrorMessageConstants;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.keypair.dao.KpgTaskDAO;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.service.SysCodeService;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("kpgTaskService")
public class KpgTaskServiceImpl implements KpgTaskService{
	
	private static final Log LOG = LogFactory.getLog(KpgTaskServiceImpl.class);
	
	@Autowired
	private KpgTaskDAO kpgTaskDAO;
	
	@Autowired
	private SysCodeService sysCodeService;
	
	
	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#selectAll()
	 */
	@Override
	public List<KpgTask> selectAll(PagedQuery pagedQuery) {
		LOG.debug("selectAll - start");
		List<KpgTask> kpgTasks=this.kpgTaskDAO.selectAll(pagedQuery);
		LOG.debug("selectAll - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#insert(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public int insert(KpgTask kpgTask) throws Applicationexception {
		LOG.debug("insert - start");
		KpgTask dbKpgTask = this.kpgTaskDAO.findByName(kpgTask.getName());
		int i=0;
		if(dbKpgTask==null){
			SysCode syscode = new SysCode();
			syscode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_NOT_STARTED));
			List<SysCode> code = sysCodeService.searchByCondition(syscode);
			SysCode codes = new SysCode();
			
			codes.setId(code.get(0).getId());
			kpgTask.setTaskStatus(codes);
			
			kpgTask.setGeneratedKeyAmount(0);
			kpgTask.setTaskStartTime(new Date());
			i = kpgTaskDAO.insert(kpgTask);
		}else {
			Applicationexception.throwException(ErrorMessageConstants.kpgTaskNameExist, new String[]{kpgTask.getName()});
		}
		LOG.debug("insert - end");
		return i;
		
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#update(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public int update(KpgTask kpgTask) {
		LOG.debug("update -start");
		/*KpgTask dbKpgTask = kpgTaskDAO.findById(kpgTask.getId());
		if(dbKpgTask.getTaskStatus().getId()!=KmConstants.CODE_ID_TASK_STATUS_NOT_STARTED){
			throw new KmApplicationexception("任务["+kpgTask.getName()+"]已经启动，无法修改!");
		}*/
		int i = kpgTaskDAO.update(kpgTask);
		LOG.debug("update - end");
		return i;
	
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) {
		LOG.debug("delete - start");
		int i = kpgTaskDAO.delete(id);
		LOG.debug("delete - end");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#selectById()
	 */
	@Override
	public KpgTask selectById(Long id) {
		LOG.debug("selectById - start");
		KpgTask kpgTask=this.kpgTaskDAO.findById(id);
		LOG.debug("selectById - end");
		return kpgTask;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#findAllUnExecutedTask()
	 */
	@Override
	public List<KpgTask> findAllUnExecutedTask() {
		LOG.debug("KpgTask - start");
		SysCode sysCode = new SysCode();
		KpgTask kpgTask =new KpgTask();
		sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING));
		kpgTask.setTaskStatus(sysCode);
		Date date=new Date();
		kpgTask.setTaskStartTime(date);
		List<KpgTask> kpgTasks=this.kpgTaskDAO.findAllUnExecutedTask(kpgTask);
		LOG.debug("KpgTask - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#findByTaskStatus(java.lang.Integer)
	 */
	@Override
	public List<KpgTask> findByTaskStatus(Long codeId) {
		LOG.debug("findByTaskStatus - start");
		List<KpgTask> kpgTasks = kpgTaskDAO.findByTaskStatus(codeId);
		LOG.debug("findByTaskStatus - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#updateGeneratedKeyAmount(java.lang.Long, int)
	 */
	@Override
	public void updateGeneratedKeyAmount(Long taskId, int sliceSize) {
		LOG.debug("updateGeneratedKeyAmount - start");
		
		KpgTask kpgTask = kpgTaskDAO.findById(taskId);	
		
		kpgTask.setGeneratedKeyAmount(kpgTask.getGeneratedKeyAmount()+sliceSize);
		
		kpgTaskDAO.updateGeneratedKeyAmount(kpgTask);
		
		LOG.debug("updateGeneratedKeyAmount - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#searchByCondition(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public List<KpgTask> searchByCondition(KpgTask kpgTask) {
		LOG.debug("searchByCondition - start");
		List<KpgTask> kpgTasks = this.kpgTaskDAO.searchByCondition(kpgTask);
		LOG.debug("searchByCondition - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#start(java.lang.Long)
	 */
	@Override
	public Map<String,Object> startTask(Long id) {
		LOG.debug("start - start");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		int i = 0;
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		if(kpgTask!=null) {
			SysCode sysCode = new  SysCode();
			if(String.valueOf(Constants.CODE_ID_TASK_STATUS_NOT_STARTED).equals(kpgTask.getTaskStatus().getParaValue())) {
				
				sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING));
				
				List<SysCode>sysCodes=sysCodeService.searchByCondition(sysCode);
				kpgTask.setTaskStatus(sysCodes.get(0));
				
				i = kpgTaskDAO.update(kpgTask);
				
			}
			
			LOG.debug("start - end");
		}
		resultMap.put("i", i);
		resultMap.put("kpgTask", kpgTask);
		return resultMap;
	} 
		
	

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#stop(java.lang.Long)
	 */
	@Override
	public int stop(Long id) {
		LOG.debug("stop - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		int i = 0;
		if((String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_PAUSED));
			List<SysCode>sysCodes=sysCodeService.searchByCondition(sysCode);
			kpgTask.setTaskStatus(sysCodes.get(0));
			i=kpgTaskDAO.updateStatus(kpgTask);
		}if((String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_RESUMED)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_PAUSED));
			List<SysCode>sysCodes=sysCodeService.searchByCondition(sysCode);
			kpgTask.setTaskStatus(sysCodes.get(0));
			i=kpgTaskDAO.updateStatus(kpgTask);
		}  
		
		LOG.debug("stop - end");
		return i;
		
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#continute(java.lang.Long)
	 */
	@Override
	public Map<String,Object> continuation(Long id) {
		LOG.debug("continute - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		int i = 0;
		
		if(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_PAUSED).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING));
			
			List<SysCode>sysCodes=sysCodeService.searchByCondition(sysCode);
			kpgTask.setTaskStatus(sysCodes.get(0));
			
			i = kpgTaskDAO.update(kpgTask);
		}
		
		LOG.debug("continute - end");
		
		resultMap.put("i", i);
		resultMap.put("kpgTask", kpgTask);
		return resultMap;
		
	}

	@Override
	public int getSysCodeCount() {
		LOG.debug("getSysCodeCount - start");
		int count = kpgTaskDAO.getSysCodeCount();
		LOG.debug("getSysCodeCount - end");
		return count;
	}

}
