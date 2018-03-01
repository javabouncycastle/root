/**
 * 
 */
package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.syscode.entry.KmSysCodeType;

/**
 * @author Limin
 *
 */
public interface KmSysCodeTypeDAO {

	/**
	 * @param paraType
	 * @return 
	 */
	public KmSysCodeType findByType(String paraType);

	/**
	 * @param sysCodeType
	 * @return 
	 */
	public int insert(KmSysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 * @return 
	 */
	public int update(KmSysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 */
	public List<KmSysCodeType> selectAll(KmSysCodeType sysCodeType);

	/**
	 * @param id
	 * @return 
	 */
	public int delete(Long id);

	/**
	 * @param sysCodeType
	 * @return
	 */
	public List<KmSysCodeType> searchByCondition(KmSysCodeType sysCodeType);

	/**
	 * @param id
	 * @return
	 */
	public KmSysCodeType selectById(Long id);
}
