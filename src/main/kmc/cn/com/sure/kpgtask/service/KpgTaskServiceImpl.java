/**
 * 
 */
package cn.com.sure.kpgtask.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.keypair.dao.KpgTaskDAO;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.syscode.entry.SysCode;

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
	
	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#selectAll()
	 */
	@Override
	public List<KpgTask> selectAll() {
		LOG.debug("selectAll - start");
		List<KpgTask> kpgTasks=this.kpgTaskDAO.selectAll();
		LOG.debug("selectAll - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#insert(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public int insert(KpgTask kpgTask) throws Applicationexception {
		LOG.debug("insert - start");
		//KpgTask dbKpgTask = this.kpgTaskDAO.findByName(kpgTask.getName());
		int i=0;
		/*if(dbKpgTask==null){*/
			SysCode taskStatus=new SysCode();
			if("".equals(kpgTask.getTaskStatus())||kpgTask.getTaskStatus()==null){
				taskStatus.setParaValue((String.valueOf(Constants.CODE_ID_TASK_STATUS_NOT_STARTED)));
			}else{
				taskStatus.setParaValue(kpgTask.getTaskStatus().getParaValue());
			}
			
			kpgTask.setGeneratedKeyAmount(0);
			kpgTask.setTaskStatus(taskStatus);
			kpgTask.setTaskStartTime(new Date());
			i = kpgTaskDAO.insert(kpgTask);
		/*}if(dbKpgTask!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.kpgTaskNameExist, new String[]{kpgTask.getName()});
		}*/
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
	public void delete(Long id) {
		LOG.debug("delete - start");
		this.kpgTaskDAO.delete(id);
		LOG.debug("delete - end");
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
		
		kpgTaskDAO.update(kpgTask);
		
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
	public void start(Long id) {
		LOG.debug("start - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		if(kpgTask!=null&&(String.valueOf(Constants.CODE_ID_TASK_STATUS_NOT_STARTED)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING));
			kpgTask.setTaskStatus(sysCode);
			kpgTaskDAO.start(kpgTask);
		} 
		
		LOG.debug("start - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#suspend(java.lang.Long)
	 */
	@Override
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		if(kpgTask!=null&&(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_PAUSED));
			kpgTask.setTaskStatus(sysCode);
			kpgTaskDAO.suspend(id);
		} 
		
		LOG.debug("suspend - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#stop(java.lang.Long)
	 */
	@Override
	public void stop(Long id) {
		LOG.debug("stop - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		if(kpgTask!=null&&(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_INTERRUPTED));
			kpgTask.setTaskStatus(sysCode);
			kpgTaskDAO.stop(id);
		} 
		
		LOG.debug("stop - end");
		
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#continute(java.lang.Long)
	 */
	@Override
	public void continuation(Long id) {
		LOG.debug("continute - start");
		KpgTask kpgTask = this.kpgTaskDAO.findById(id);
		if(kpgTask!=null&&(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_PAUSED)).equals(kpgTask.getTaskStatus().getParaValue())) {
			SysCode sysCode = new  SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_MANUAL_RESUMED));
			kpgTask.setTaskStatus(sysCode);
			kpgTaskDAO.stop(id);
		} 
		LOG.debug("continute - end");
		
	}



}
