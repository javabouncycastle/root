package cn.com.sure.log.dao;

import java.util.List;

import cn.com.sure.log.entry.AuditOpLog;

public interface AuditOpLogDAO {

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
	
	

}
