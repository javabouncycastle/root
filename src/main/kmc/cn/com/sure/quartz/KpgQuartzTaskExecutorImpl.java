/**
 * 
 */
package cn.com.sure.quartz;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KeyPairGenThread;
import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("kpgQuartzTaskExecutor")
public class KpgQuartzTaskExecutorImpl implements KpgQuartzTaskExecutor{

	private static final Log LOG = LogFactory.getLog(KpgQuartzTaskExecutorImpl.class);
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KpgTaskExecuteService kpgTaskExecuteService;
	
	
	@Override
	public void executeTask() throws NoSuchAlgorithmException,
			Applicationexception {
		LOG.info("Quartz:executeTask NotStartedTask - start at " + new Date());
		//1.启动新任务
		List<KpgTask> list = kpgTaskService.findAllUnExecutedTask();
		
		for(KpgTask task:list){
			
			//设置密钥对执行开始时间
			task.setExeTaskStartTime(new Date());
			
			//更新任务状态
			SysCode sysCode = new SysCode();
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING));
			task.setTaskStatus(sysCode);
			
			kpgTaskService.update(task);
			
			new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
			
			LOG.info("Quartz:executeTask NotStartedTask - end at "+ new Date());
			
		}
		
		
		//2.继续暂停任务
		LOG.info("Quartz:executeTask ResumeTask - start at " + new Date());

		list=kpgTaskService.findByTaskStatus(Constants.CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING);
		
		for(KpgTask task:list){
			
			SysCode sysCode = new SysCode();
			
			sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING));
			
			task.setTaskStatus(sysCode);

			kpgTaskService.update(task);
			
			LOG.info("将要继续执行任务TASK"+task.getId()+","+task.getName());
			
			new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
			
		}
		
	}

}
