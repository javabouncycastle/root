/**
 * 
 */
package cn.com.sure.common;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.KmSysCode;

/**
 * @author Limin
 *
 */
public class KeyPairGenThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(KeyPairGenThread.class);
	
	private KpgTask kpgTask;
	private KpgTaskExecuteService kpgTaskExecuteService;
	private KpgTaskService kpgTaskService;
	
	//线程传值只能用构造方法传值，没法注入，注入进来是空！！
	/**
	 * @param kpgTaskService
	 * @param task
	 * @param kpgTaskExecuteService
	 */
	public KeyPairGenThread(KpgTaskService kpgTaskService, KpgTask kpgTask,
			KpgTaskExecuteService kpgTaskExecuteService) {
		this.kpgTask=kpgTask;
		this.kpgTaskExecuteService=kpgTaskExecuteService;
		this.kpgTaskService=kpgTaskService;
	}

	public void run(){
		
		do{
			LOG.info("执行TASK"+kpgTask.getId()+","+kpgTask.getName());
			LOG.info("run - start at " + new Date());
			
			
			try {
				kpgTaskExecuteService.executeTaskSlice(kpgTask.getId());
			} catch (Exception e) {
				LOG.error(e);
				LOG.error("执行出现异常"+kpgTask.getId());
				
				kpgTask = kpgTaskService.selectById(kpgTask.getId());
				kpgTask.setExeTaskEndTime(new Date());
				KmSysCode sysCode = new KmSysCode();
				sysCode.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXCEPTION));
				kpgTask.setTaskStatus(sysCode);
				kpgTask.setTaskExeResult(e.getMessage());
				kpgTaskService.update(kpgTask);
				
				LOG.error("executeTask - exception - end at " + new Date());
				return;
				
			}
			
			kpgTask = kpgTaskService.selectById(kpgTask.getId());
			
			if(kpgTask.getGeneratedKeyAmount().intValue() == kpgTask.getKpgKeyAmount().intValue()){
				KmSysCode sysCode = new KmSysCode();
				sysCode.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_FINISHED));
				kpgTask.setTaskStatus(sysCode);
				kpgTask.setExeTaskEndTime(new Date());
				kpgTask.setTaskExeResult("成功");
				kpgTaskService.update(kpgTask);
				return;
			}
			
		}while(kpgTask.getTaskStatus().getParaValue().equals(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXECUTING)) );
		
		
		LOG.info("run - end at "+ new Date());
	}

	public KpgTask getKpgTask() {
		return kpgTask;
	}

	public void setKpgTask(KpgTask kpgTask) {
		this.kpgTask = kpgTask;
	}
	
	
}
