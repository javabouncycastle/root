/**
 * 
 */
package cn.com.sure.kpgtask.service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import cn.com.sure.common.Applicationexception;

/**
 * @author Limin
 *
 */
public interface KpgTaskExecuteService {
	
	void executeTaskSlice(Long taskId)throws NoSuchAlgorithmException, NoSuchProviderException, Applicationexception, ClassNotFoundException, InstantiationException, IllegalAccessException;

}
