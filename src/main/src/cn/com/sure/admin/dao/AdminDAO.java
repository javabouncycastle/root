/**
 * 
 */
package cn.com.sure.admin.dao;

import cn.com.sure.admin.entry.Admin;

/**
 * @author Limin
 *
 */
public interface AdminDAO {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	Admin fingByAdmId(int adminAuthNum);

}
