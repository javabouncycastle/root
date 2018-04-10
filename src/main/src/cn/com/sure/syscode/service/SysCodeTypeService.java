/**
 * 
 */
package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
public interface SysCodeTypeService {

	/**
	 * @param sysCodeType
	 * @return 
	 */
	int insert(SysCodeType sysCodeType)throws Applicationexception;

	/**
	 * @param sysCodeType
	 * @return 
	 */
	int update(SysCodeType sysCodeType);

	/**
	 * @param id
	 * @return 
	 */
	int delete(Long id);

	/**
	 * @param sysCodeType
	 * @return
	 */
	List<SysCodeType> selectAll(SysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 * @return
	 */
	List<SysCodeType> searchByCondition(SysCodeType sysCodeType);

	/**
	 * @param id
	 * @return
	 */
	SysCodeType selectById(Long id);
	
}
