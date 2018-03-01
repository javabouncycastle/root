/**
 * 
 */
package cn.com.sure.admin.service;

import cn.com.sure.admin.entry.KmAdmin;

/**
 * @author Limin
 *
 */
public interface KmAdminService {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	KmAdmin fingByAdmId(int adminAuthNum);
	
	

}
