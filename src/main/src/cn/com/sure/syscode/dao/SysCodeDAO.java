package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.syscode.entry.SysCode;

public interface SysCodeDAO {


	/**
	 * @param sysCode
	 * @return 
	 */
	public List<SysCode> serachByContion(SysCode sysCode);

	/**
	 * @param algorithmName
	 * @return
	 */
	public int countNum(String algorithmName);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectMin(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectBuffer(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectBufSize(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public SysCode findByName(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public int insert(SysCode sysCode)throws Applicationexception;

	/**
	 * @param sysCode
	 * @return
	 */
	public int update(SysCode sysCode);

	/**
	 * @param id
	 * @return
	 */
	public int delete(Long id);

	/**
	 * @param id
	 * @return
	 */
	public SysCode findById(Long id);

	/**
	 * @param sysCode
	 */
	public void updateValid(SysCode sysCode);

	/**
	 * @return
	 */
	public List<SysCode> selectAll();

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> findByType(SysCode sysCode);


}
