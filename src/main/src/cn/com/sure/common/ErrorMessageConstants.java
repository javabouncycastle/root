package cn.com.sure.common;

public interface ErrorMessageConstants {
	//系统级别
	public static final int unknownError = -1;  // 位置错误
	
	public static final int nameExist = 101; //别名已存在
	
	public static final int paraValueExist = 103;//数据字典已存在
	
	public static final int paraTypeValueExist = 104;//数据字典类别已存在
	
	public static final int exportError = 117;//excel导出失败
	
	public static final int sysCodeTypeInuse = 118;//数据字典类型正在使用，请勿删除！
	
	public static final int sysCodeInuseInCtml = 121;//数据字典正在证书模板中使用，请勿删除！
	
	public static final int sysCodeInuseIntaskBuff = 122;//数据字典正在密钥任务缓存数量中使用，请勿删除！
	
	public static final int sysCodeInuseIntaskStatus = 123;//123=数据字典正在密钥任务任务状态使用，请勿删除！
	
	//KM模块
	public static final int kpgTaskNameExist = 205;//任务已存在
	
	public static final int taskExecuting = 215;//任务已启动，无法再次启动
	
	public static final int taskFinished = 216;//任务已结束，无法再次启动
	
	public static final int snNotFound = 10001;//找不到这条记录
	
	public static final int revokeKpSuce = 10002;//注销密钥成功
	
	
	//CA模块
	public static final int outValidity = 300;//超出证书模板的最大有效期
	
	public static final int ctmlNameExist = 303;//证书模板已存在
	
	//RA模块
	public static final int dnIsExist = 401;//DN已存在

}
