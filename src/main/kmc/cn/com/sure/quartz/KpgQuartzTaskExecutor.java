/**
 * 
 */
package cn.com.sure.quartz;

import java.security.NoSuchAlgorithmException;

import cn.com.sure.common.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface KpgQuartzTaskExecutor {
	
	/**
	 * @throws NoSuchAlgorithmException 
	 * @throws KmApplicationexception 
	 * 
	 */
	void executeTask() throws NoSuchAlgorithmException, KmApplicationexception;

}
