package cn.com.sure.log.dao;

import java.util.List;

import cn.com.sure.log.entry.KmAuditOpLog;

public interface KmAuditOpLogDAO {

	void insert(KmAuditOpLog auditOpLog);

	/**
	 * @return
	 */
	List<KmAuditOpLog> selectAll();

	/**
	 * @param auditOpLog
	 * @return
	 */
	List<KmAuditOpLog> searchByCondition(KmAuditOpLog auditOpLog);
	
	

}
