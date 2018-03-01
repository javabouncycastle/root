/**
 * 
 */
package cn.com.sure.common;

import cn.com.sure.keypair.service.KeypairInuseService;




/**
 * @author Limin
 *
 */
public class AutoArchThread extends Thread{

	private KeypairInuseService inuseService;

	/**
	 * @param inuseService
	 */
	public AutoArchThread(KeypairInuseService inuseService) {
		this.inuseService=inuseService;
	}

	public void run(){
		inuseService.executeAutoArchKpg();
		System.out.println("AutoArchThread run");
		
	}


}
