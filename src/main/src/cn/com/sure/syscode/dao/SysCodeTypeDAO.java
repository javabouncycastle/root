/**
 * 
 */
package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
public interface SysCodeTypeDAO {

	/**
	 * @param paraType
	 * @return 
	 */
	public SysCodeType findByType(String paraType);

	/**
	 * @param sysCodeType
	 * @return 
	 */
	public int insert(SysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 * @return 
	 */
	public int update(SysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 */
	public List<SysCodeType> selectAll(SysCodeType sysCodeType);

	/**
	 * @param id
	 * @return 
	 */
	public int delete(Long id);

	/**
	 * @param sysCodeType
	 * @return
	 */
	public List<SysCodeType> searchByCondition(SysCodeType sysCodeType);

	/**
	 * @param id
	 * @return
	 */
	public SysCodeType selectById(Long id);
}
