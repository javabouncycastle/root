package cn.com.sure.kpgtask.entry;

import java.util.Date;

import javax.persistence.Id;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.syscode.entry.KmSysCode;

public class KpgTask {

	/**
	 * 主键
	 */
	@Id
	private Long id;
	
	/**
	 * 别名
	 */
	private String name;
	
	/**
	 * 产生密钥的密钥算法+长度
	 */
	private KeyPairAlgorithm keyPairAlgorithm;
	
	
	/**
	 * 产生密钥的密钥数量
	 */
	private Integer kpgKeyAmount;

    /**
     * 任务状态  1 standby准备状态，executing正在执行，finished任务完成，exception异常结束 ，interrupted人工中断
     */
	private KmSysCode taskStatus; 
    
		
	/**
	 * 设定任务新建时间
	 */
    private Date taskStartTime;
    

	/**
	 * 任务说明
	 */
	private String taskNotes;


    
	/**
	 * 本次执行开始时间
	 */
    private Date exeTaskStartTime;
    
    
	/**
	 * 本次执行结束时间
	 */
    private Date exeTaskEndTime;
    
	/**
	 * 密钥已经存储缓冲记录数量
	 */
	private KmSysCode dbCommitBufsize; 
	
	
	
	/**
	 * 密钥已经存储数量
	 */
	private Integer generatedKeyAmount; 
	
	/**
	 * 任务执行结果说明
	 */
	private String taskExeResult;
	
	
	private Date startTime;
	private Date endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KeyPairAlgorithm getKeyPairAlgorithm() {
		return keyPairAlgorithm;
	}

	public void setKeyPairAlgorithm(KeyPairAlgorithm keyPairAlgorithm) {
		this.keyPairAlgorithm = keyPairAlgorithm;
	}

	public Integer getKpgKeyAmount() {
		return kpgKeyAmount;
	}

	public void setKpgKeyAmount(Integer kpgKeyAmount) {
		this.kpgKeyAmount = kpgKeyAmount;
	}

	public KmSysCode getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(KmSysCode taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskNotes(String taskNotes) {
		this.taskNotes = taskNotes;
	}

	public Date getExeTaskStartTime() {
		return exeTaskStartTime;
	}

	public void setExeTaskStartTime(Date exeTaskStartTime) {
		this.exeTaskStartTime = exeTaskStartTime;
	}

	public Date getExeTaskEndTime() {
		return exeTaskEndTime;
	}

	public void setExeTaskEndTime(Date exeTaskEndTime) {
		this.exeTaskEndTime = exeTaskEndTime;
	}

	public KmSysCode getDbCommitBufsize() {
		return dbCommitBufsize;
	}

	public void setDbCommitBufsize(KmSysCode dbCommitBufsize) {
		this.dbCommitBufsize = dbCommitBufsize;
	}

	public Integer getGeneratedKeyAmount() {
		return generatedKeyAmount;
	}

	public void setGeneratedKeyAmount(Integer generatedKeyAmount) {
		this.generatedKeyAmount = generatedKeyAmount;
	}

	public String getTaskExeResult() {
		return taskExeResult;
	}

	public void setTaskExeResult(String taskExeResult) {
		this.taskExeResult = taskExeResult;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


}
