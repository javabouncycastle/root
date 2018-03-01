package cn.com.sure.common;

public interface KmErrorMessageConstants {
	
	public static final int unknownError = -1;  // 位置错误
	
	public static final int nameExist = 101; //别名已存在
	
	public static final int paraValueExist = 103;//数据字典已存在
	
	public static final int paraTypeValueExist = 104;//数据字典类别已存在
	
	public static final int kpgTaskNameExist = 105;//任务已存在
	
	public static final int taskExecuting = 115;//任务已启动，无法再次启动
	
	public static final int taskFinished = 116;//任务已结束，无法再次启动
	
	public static final int snNotFound = 10001;//找不到这条记录
	
	public static final int revokeKpSuce = 10002;//注销密钥成功
	
	public static final int exportError = 117;//excel导出失败

}
