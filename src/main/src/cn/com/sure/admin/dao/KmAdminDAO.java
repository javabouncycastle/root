/**
 * 
 */
package cn.com.sure.admin.dao;

import cn.com.sure.admin.entry.KmAdmin;

/**
 * @author Limin
 *
 */
public interface KmAdminDAO {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	KmAdmin fingByAdmId(int adminAuthNum);

}
