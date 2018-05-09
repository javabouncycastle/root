/**
 * 
 */
package cn.com.sure.common;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.service.SysCodeService;

/**
 * @author Limin
 *
 */
public class KeyPairGenThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(KeyPairGenThread.class);
	
	private KpgTask kpgTask;
	private KpgTaskExecuteService kpgTaskExecuteService;
	private KpgTaskService kpgTaskService;
	private SysCodeService sysCodeService;
	
	//线程传值只能用构造方法传值，没法注入，注入进来是空！！
	/**
	 * @param kpgTaskService
	 * @param task
	 * @param kpgTaskExecuteService
	 */
	public KeyPairGenThread(KpgTaskService kpgTaskService, KpgTask kpgTask,
			KpgTaskExecuteService kpgTaskExecuteService,SysCodeService sysCodeService) {
		this.kpgTask=kpgTask;
		this.kpgTaskExecuteService=kpgTaskExecuteService;
		this.kpgTaskService=kpgTaskService;
		this.sysCodeService=sysCodeService;
	}

	public void run(){
		
		do{
			LOG.info("执行TASK"+kpgTask.getId()+","+kpgTask.getName());
			LOG.info("run - start at " + new Date());
			
			
			try {
				kpgTaskExecuteService.executeTaskSlice(kpgTask.getId());
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("执行出现异常"+kpgTask.getId());
				
				kpgTask = kpgTaskService.selectById(kpgTask.getId());
				kpgTask.setExeTaskEndTime(new Date());
				SysCode sysCode = new SysCode();
				sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXCEPTION));
				List<SysCode> codes = sysCodeService.searchByCondition(sysCode);
				SysCode code = new SysCode();
				code=codes.get(0);
				kpgTask.setTaskStatus(code);
				kpgTask.setTaskExeResult(e.getMessage());
				kpgTaskService.update(kpgTask);
				
				LOG.error("executeTask - exception - end at " + new Date());
				return;
				
			}
			
			kpgTask = kpgTaskService.selectById(kpgTask.getId());
			
			if(kpgTask.getGeneratedKeyAmount().intValue() == kpgTask.getKpgKeyAmount().intValue()){
				SysCode sysCode = new SysCode();
				sysCode.setParaValue(String.valueOf(Constants.CODE_ID_TASK_STATUS_FINISHED));
				List<SysCode> codes = sysCodeService.searchByCondition(sysCode);
				SysCode code = new SysCode();
				code=codes.get(0);
				kpgTask.setTaskStatus(code);
				kpgTask.setExeTaskEndTime(new Date());
				kpgTask.setTaskExeResult("成功");
				kpgTaskService.update(kpgTask);
				return;
			}
			
		}while(kpgTask.getTaskStatus().getParaValue().equals(String.valueOf(Constants.CODE_ID_TASK_STATUS_EXECUTING)) );
		
		
		LOG.info("run - end at "+ new Date());
	}

	public KpgTask getKpgTask() {
		return kpgTask;
	}

	public void setKpgTask(KpgTask kpgTask) {
		this.kpgTask = kpgTask;
	}
	
	
}