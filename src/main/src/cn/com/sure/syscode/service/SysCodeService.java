package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.syscode.entry.SysCode;

public interface SysCodeService {



	/**
	 * @param sysCode
	 * @return 
	 */
	List<SysCode> searchByCondition(SysCode sysCode);

	/**
	 * @param id
	 * @return
	 */
	SysCode selectById(Long id);

	/**
	 * @return 
	 * 
	 */
	List<SysCode> selectServicePort();

	/**
	 * @param sysCode
	 * @return
	 */
	int insert(SysCode sysCode)throws  Applicationexception;

	/**
	 * @return
	 */
	List<SysCode> selectAll();

	/**
	 * @param sysCode
	 * @return
	 */
	int update(SysCode sysCode);

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
	List<SysCode> selectByType(SysCode sysCode);

	List<SysCode> findByType(SysCode sysCode);
	

}
