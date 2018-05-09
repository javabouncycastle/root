package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.PagedQuery;
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

/*	*//**
	 * @return 
	 * 
	 *//*
	List<SysCode> selectServicePort();*/

	/**
	 * @param sysCode
	 * @return
	 */
	int insert(SysCode sysCode)throws  Applicationexception;

	/**
	 * @return
	 */
	List<SysCode> selectAll(PagedQuery pagevo);

	/**
	 * @param sysCode
	 * @return
	 */
	int update(SysCode sysCode)throws  Applicationexception;

	/**
	 * @param id
	 * @return
	 */
	int remove(Long id) throws Applicationexception;

	/**
	 * @param id
	 * @return 
	 */
	int suspend(Long id);

	/**
	 * @param id
	 * @return 
	 */
	int activate(Long id);

	/**
	 * @param sysCode
	 * @return
	 */
	List<SysCode> selectByType(SysCode sysCode);

	int getSysCodeCount();

	

}
