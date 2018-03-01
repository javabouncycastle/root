package cn.com.sure.log.entry;

import java.util.Date;

import javax.persistence.Id;

public class KmAuditOpLog {
	
	public KmAuditOpLog() {
	}
	
	public KmAuditOpLog(String id) {
		this.id = id;
	}
	/**
	 * ID
	 */
	@Id 
	private String id;
	
	private long type;   //表示操作的类型。
	private String action; //表示执行的操作是什么
	
	private String actionExt1; //表示执行的操作扩展
	private String actionExt2; //表示执行的操作扩展
	private String actionExt3; //表示执行的操作扩展
	private String actionExt4; //表示执行的操作扩展

	private String  message;   //本地化消息
	private Date    timestamp; //执行上述操作的日期和时间。以 GMT 时间存储此值
	private String  ip;        //对其执行操作的IP
	private String  operator;  //对其执行操作的帐户的证书序列号
	private Integer isOpSucc;  //表示已执行操作的结果
	
	private Date startTime;//查询时使用的时间段开始时间
	private Date endTime;//查询时使用的时间段的结束时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionExt1() {
		return actionExt1;
	}
	public void setActionExt1(String actionExt1) {
		this.actionExt1 = actionExt1;
	}
	public String getActionExt2() {
		return actionExt2;
	}
	public void setActionExt2(String actionExt2) {
		this.actionExt2 = actionExt2;
	}
	public String getActionExt3() {
		return actionExt3;
	}
	public void setActionExt3(String actionExt3) {
		this.actionExt3 = actionExt3;
	}
	public String getActionExt4() {
		return actionExt4;
	}
	public void setActionExt4(String actionExt4) {
		this.actionExt4 = actionExt4;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getIsOpSucc() {
		return isOpSucc;
	}
	public void setIsOpSucc(Integer isOpSucc) {
		this.isOpSucc = isOpSucc;
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
