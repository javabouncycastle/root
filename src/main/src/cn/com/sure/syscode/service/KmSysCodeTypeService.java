/**
 * 
 */
package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.syscode.entry.KmSysCodeType;

/**
 * @author Limin
 *
 */
public interface KmSysCodeTypeService {

	/**
	 * @param sysCodeType
	 * @return 
	 */
	int insert(KmSysCodeType sysCodeType)throws KmApplicationexception;

	/**
	 * @param sysCodeType
	 * @return 
	 */
	int update(KmSysCodeType sysCodeType);

	/**
	 * @param id
	 * @return 
	 */
	int delete(Long id);

	/**
	 * @param sysCodeType
	 * @return
	 */
	List<KmSysCodeType> selectAll(KmSysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 * @return
	 */
	List<KmSysCodeType> searchByCondition(KmSysCodeType sysCodeType);

	/**
	 * @param id
	 * @return
	 */
	KmSysCodeType selectById(Long id);
	
}
