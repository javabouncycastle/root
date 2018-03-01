package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.syscode.entry.KmSysCode;

public interface KmSysCodeDAO {


	/**
	 * @param sysCode
	 * @return 
	 */
	public List<KmSysCode> serachByContion(KmSysCode sysCode);

	/**
	 * @param algorithmName
	 * @return
	 */
	public int countNum(String algorithmName);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<KmSysCode> selectMin(KmSysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<KmSysCode> selectBuffer(KmSysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<KmSysCode> selectBufSize(KmSysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public KmSysCode findByName(KmSysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public int insert(KmSysCode sysCode)throws KmApplicationexception;

	/**
	 * @param sysCode
	 * @return
	 */
	public int update(KmSysCode sysCode);

	/**
	 * @param id
	 * @return
	 */
	public int delete(Long id);

	/**
	 * @param id
	 * @return
	 */
	public KmSysCode findById(Long id);

	/**
	 * @param sysCode
	 */
	public void updateValid(KmSysCode sysCode);

	/**
	 * @return
	 */
	public List<KmSysCode> selectAll();

	/**
	 * @param sysCode
	 * @return
	 */
	public List<KmSysCode> findByType(KmSysCode sysCode);


}
