package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.syscode.entry.KmSysCode;

public interface KmSysCodeService {



	/**
	 * @param sysCode
	 * @return 
	 */
	List<KmSysCode> searchByCondition(KmSysCode sysCode);

	/**
	 * @param id
	 * @return
	 */
	KmSysCode selectById(Long id);

	/**
	 * @return
	 */
	List<KmSysCode> selectMin();

	/**
	 * @return
	 */
	List<KmSysCode> selectBuffer();

	/**
	 * @return
	 */
	List<KmSysCode> selectGenKeyNum();

	/**
	 * @param sysCode
	 * @return
	 */
	List<KmSysCode> selectBufSize(KmSysCode sysCode);

	/**
	 * @return 
	 * 
	 */
	List<KmSysCode> selectServicePort();

	/**
	 * @param sysCode
	 * @return
	 */
	int insert(KmSysCode sysCode)throws  KmApplicationexception;

	/**
	 * @return
	 */
	List<KmSysCode> selectAll();

	/**
	 * @param sysCode
	 * @return
	 */
	int update(KmSysCode sysCode);

	/**
	 * @param id
	 * @return
	 */
	int remove(Long id);

	/**
	 * @param id
	 */
	void suspend(Long id);

	/**
	 * @param id
	 */
	void activate(Long id);

	/**
	 * @param sysCode
	 * @return
	 */
	List<KmSysCode> selectByType(KmSysCode sysCode);

}
