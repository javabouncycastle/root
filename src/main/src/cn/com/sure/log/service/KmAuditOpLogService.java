package cn.com.sure.log.service;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.com.sure.log.entry.KmAuditOpLog;

public interface KmAuditOpLogService {
	
	
	/**
	 * 
	 * @param auditOpLog
	 */
	void insert(long type,String action,String actionExt1,String actionExt2,String actionExt3,String actionExt4,
			String  message,Date timestamp,String ip,String  operator,Integer isOpSucc);
	

	/**
	 * @return
	 */
	List<KmAuditOpLog> selectAll();


	/**
	 * @param auditOpLog
	 * @return
	 */
	List<KmAuditOpLog> searchByCondition(KmAuditOpLog auditOpLog);


	/**
	 * @param out 
	 * @param titles 
	 * 
	 */
	void exportExcel(String[] titles, ServletOutputStream out,KmAuditOpLog auditOpLog)throws Exception;
	
}
