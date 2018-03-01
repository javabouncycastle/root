/**
 * 
 */
package cn.com.sure.common;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.socket.KmSocketService;
import cn.com.sure.socket.KmSocketThread;

/**
 * @author Limin
 *
 */
public class StartSocketThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(StartSocketThread.class);
	
	private KmSocketService socketService; 
	private ServerSocket serverSocket;

	/**
	 * @param socketService
	 */
	public StartSocketThread(KmSocketService socketService,ServerSocket serverSocket) {
		this.socketService=socketService;
		this.serverSocket=serverSocket;
	}

	
	public void run(){
		try {  
			 
	            if(null == serverSocket){  
	            	//1.启动一个新的socketServer
	            	ResourceBundleSocketMessage rbem = ResourceBundleSocketMessage.getInstance();
	                this.serverSocket = new ServerSocket(Integer.parseInt(rbem.getMessage("port",  new Object[]{ "",""})));  
	                LOG.debug("socket端口"+Integer.parseInt(rbem.getMessage("port",  new Object[]{ "",""})));
	                LOG.debug("socket start");
	                
	                while (true) {
	                    // 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
	                    Socket socket = serverSocket.accept();
	                    new Thread(new KmSocketThread(socket,socketService)).start();
	                   
	                	} 
	                }  
	            
		     }catch (Exception e) {  
		            System.out.println("SocketThread创建socket服务出错");  
		            e.printStackTrace();  
		      }
	}
}
