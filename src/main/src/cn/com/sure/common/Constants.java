package cn.com.sure.common;


public class Constants {
	
	public static final int YES_OR_NO_OPTION_YES = 1;//停用启用-启用
	public static final int YES_OR_NO_OPTION_NO = 0;//停用启用-停用
	
	public static final String CERT_START_TIME_CHECKIN = "3";//证书开始时间是审核通过时
	
	public static final long OPERATION_TYPE_INSERT=11;//增加
	public static final String OPERATION_TYPE_INS="增加";
	
	public static final long OPERATION_TYPE_DELETE=12;//删除
	public static final String OPERATION_TYPE_DEL="删除";//删除
	
	public static final long OPERATION_TYPE_UPDATE=13;//更新
	public static final String OPERATION_TYPE_UPD="更新";//更新
	
	public static final long OPERATION_TYPE_SELECT=14;//查询
	public static final String OPERATION_TYPE_SEL="查询";//查询
	
	public static final long OPERATION_TYPE_SUSPEND=15;//停用
	public static final String OPERATION_TYPE_SUS="停用";//停用

	public static final long OPERATION_TYPE_ACTIVE=16;//启用
	public static final String OPERATION_TYPE_ACT="启用";//启用
	
	
	public static final String OPERATION_TYPE_NAME="数据字典";//查询
	
	public static final int SUCCESS_OR_FAILD_OPTION_SUCCESS=1;//操作成功
	public static final int SUCCESS_OR_FAILD_OPTION_FAILD=0;//操作失败	
	
	//Session存放的管理员信息
	public static final String SESSION_ADMIN_ID       = "OLTAX_ADMIN_ID";
	public static final String SESSION_ADMIN_NAME     = "OLTAX_ADMIN_NAME";
	public static final String SESSION_ADMIN_REF      = "OLTAX_ADMIN_REF";
	public static final String SESSION_ADMIN_TERM_ID  = "OLTAX_ADMIN_TERM_ID";
	public static final String SESSION_ADMIN_DN       = "OLTAX_ADMIN_DN";
	
	public static final int ADMIN_AUTH_NUM = 10000;
	
	// 任务状态
	public static final String TYPE_ID_TASK_STATUS = "status";    // 任务状态TYPE
	
	//密钥最小数量
	public static final String KEY_NUM_MIN="keyNumMin";
	
	//密钥生成数量
	public static final String GEN_KEY_NUM="genKeyNum";
	
	//缓存数量
	public static final String DB_COMMIT_BUFFER = "dbCommitBufsize";
	
	//
	public static final String SYNCHRONOUS_KPG="synchronousKpg";
	
	public static final long CODE_ID_TASK_STATUS_NOT_STARTED = 20;        //执行状态 - 尚未开始
	public static final long CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING = 21;   //执行状态 - 任务已启动
	
	public static final long CODE_ID_TASK_STATUS_EXECUTING   = 22;       //执行状态 - 正在执行
	public static final long CODE_ID_TASK_STATUS_MANUAL_INTERRUPTED = 23;//执行状态 - 人工中断Manual interrupt
	public static final long CODE_ID_TASK_STATUS_FINISHED = 24;          //执行状态 - 正常结束
	public static final long CODE_ID_TASK_STATUS_EXCEPTION = 25;         //执行状态 - 异常结束

	public static final long CODE_ID_TASK_STATUS_MANUAL_PAUSED = 26;      //执行状态 - 人工暂停
	public static final long CODE_ID_TASK_STATUS_MANUAL_RESUMED = 27;     //执行状态 - 人工继续
	
	
	// 证书状态
	public static final long TYPE_ID_CERT_STATUS = 38L;

	public static final long CODE_ID_CERT_STATUS_GET_AUTHCODE         = 3801L; //证书状态  - 获取两码成功（23）证书未下载
	public static final long CODE_ID_CERT_STATUS_IN_USE               = 3802L; //证书状态  - 证书使用中
	
	
	// 证书新申请状态
	public static final int TYPE_ID_CERT_NEW_OR_UPDATE_STATUS = 39;    // 
	
	public static final long CODE_ID_CERT_STATUS_NEW_REQ              = 3901L; //证书状态 - 新申请1
	public static final long CODE_ID_CERT_STATUS_NEW_REQ_APPROVED     = 3902L; //证书状态  - 新申请审核通过
	public static final long CODE_ID_CERT_STATUS_NEW_REQ_DENIED       = 3903L; //证书状态  - 新申请审核拒绝
	public static final long CODE_ID_CERT_STATUS_REVOKED              = 3906L; //证书状态  - 证书已注销
	
	// 证书新更新状态
	//public static final int TYPE_ID_CERT_UPDATE_STATUS = 40;    // 
	public static final long CODE_ID_CERT_STATUS_UPDATE_REQ              = 4001L; //证书状态 - 提交更新申请
	public static final long CODE_ID_CERT_STATUS_UPDATE_REQ_APPROVED     = 4002L; //证书状态  - 更新审核通过
	public static final long CODE_ID_CERT_STATUS_UPDATE_REQ_DENIED       = 4003L; //证书状态  - 更新审核拒绝
	
	// 证书新注销状态
	public static final int TYPE_ID_CERT_REVOKE_STATUS = 41;    // 
	public static final long CODE_ID_CERT_STATUS_REVOKE_REQ              = 4101L; //证书状态 - 提交注销申请
	public static final long CODE_ID_CERT_STATUS_REVOKE_REQ_APPROVED     = 4102L; //证书状态  - 更新注销通过
	public static final long CODE_ID_CERT_STATUS_REVOKE_REQ_DENIED       = 4103L; //证书状态  - 更新注销拒绝
	
	//密钥恢复
	public static final int TYPE_ID_CERT_RECOVER_STATUS = 42; 
	
	//证书有效天数
	public static final String VALIDITY="validity";
	//密钥类型
	public static final String KPG_ALGORITHM="kpgAlgorithm";
	//签名证书用法
	public static final String SIGN_CERT_KEY_USAGE="signCertKeyUsage";
	//加密证书用法
	public static final String ENC_CERT_KEY_USAGE="encCertKeyUsage";
	//加密证书用法拓展
	public static final String EXTEND_KEY_USAGE="extendedKeyUsage";
	
	
	public static final String CERT_TEMPLATE_VALIDITY = "validity";
	
	//socket传递同步密钥算法参数
	public static final String SYNCHRONOUS_CTML="synchronousCtml";
	
	//syscode表中密钥算法
	public static final String KEY_PAIR_ALGORITHM="kpgAlgorithm";
	
	public static final String PARA_CODE="密钥算法";
	
	
}
