package cn.com.sure.log.test.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.com.sure.log.entry.AuditOpLog;

public interface AuditOpLogService {
	
	
	/**
	 * 
	 * @param auditOpLog
	 */
	void insert(AuditOpLog auditOpLog);
	

	/**
	 * @return
	 */
	List<AuditOpLog> selectAll();


	/**
	 * @param auditOpLog
	 * @return
	 */
	List<AuditOpLog> searchByCondition(AuditOpLog auditOpLog);


	/**
	 * @param out 
	 * @param titles 
	 * 
	 */
	void exportExcel(String[] titles, ServletOutputStream out,AuditOpLog auditOpLog)throws Exception;
	
}
