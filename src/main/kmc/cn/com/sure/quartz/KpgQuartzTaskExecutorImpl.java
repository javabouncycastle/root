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
import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.KmSysCode;

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
			KmApplicationexception {
		LOG.info("Quartz:executeTask NotStartedTask - start at " + new Date());
		//1.启动新任务
		List<KpgTask> list = kpgTaskService.findAllUnExecutedTask();
		
		for(KpgTask task:list){
			
			//设置密钥对执行开始时间
			task.setExeTaskStartTime(new Date());
			
			//更新任务状态
			KmSysCode sysCode = new KmSysCode();
			sysCode.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXECUTING));
			task.setTaskStatus(sysCode);
			
			kpgTaskService.update(task);
			
			new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
			
			LOG.info("Quartz:executeTask NotStartedTask - end at "+ new Date());
			
		}
		
		
		//2.继续暂停任务
		LOG.info("Quartz:executeTask ResumeTask - start at " + new Date());

		list=kpgTaskService.findByTaskStatus(KmConstants.CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING);
		
		for(KpgTask task:list){
			
			KmSysCode sysCode = new KmSysCode();
			
			sysCode.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXECUTING));
			
			task.setTaskStatus(sysCode);

			kpgTaskService.update(task);
			
			LOG.info("将要继续执行任务TASK"+task.getId()+","+task.getName());
			
			new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
			
		}
		
	}

}
