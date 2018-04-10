/**
 * 
 */
package cn.com.sure.quartz;

import java.security.NoSuchAlgorithmException;

import cn.com.sure.common.Applicationexception;

/**
 * @author Limin
 *
 */
public interface KpgQuartzTaskExecutor {
	
	/**
	 * @throws NoSuchAlgorithmException 
	 * @throws Applicationexception 
	 * 
	 */
	void executeTask() throws NoSuchAlgorithmException, Applicationexception;

}
