package cn.com.sure.common;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

//����ģʽ
public class ResourceBundleSocketMessage {
	
	public static final String propertiesFilePath = "socket";

	private static ResourceBundleSocketMessage resourceBundleSocketMessage;
	
	private Map<String,MessageFormat> resourceBundleMap = new HashMap<String,MessageFormat>();
	
	public static synchronized ResourceBundleSocketMessage getInstance(){
		
		if(resourceBundleSocketMessage == null){
			resourceBundleSocketMessage = new ResourceBundleSocketMessage();
			resourceBundleSocketMessage.reload();
		}
		
		return resourceBundleSocketMessage;

	}
	
	public void reload(){
		
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesFilePath, new Locale("", "", ""));
		Enumeration<String> keys = bundle.getKeys();
		while(keys.hasMoreElements()){
		      String key = keys.nextElement();
		      resourceBundleMap.put(key,new MessageFormat(bundle.getString(key)));
		}
		
	}
	
	private ResourceBundleSocketMessage(){}
	
	public String getMessage(int errCode, Object[] args) {

		return getMessage(String.valueOf(errCode), args);
	}

	public String getMessage(String key, Object[] args) {

		MessageFormat messageFormat = resourceBundleMap.get(key);
		
		if (messageFormat == null) {
			return "Unknown Error : " + key;
		}
		return 	messageFormat.format(args);
	}
	


	public static void main(String[] args){
		ResourceBundleSocketMessage rbem = ResourceBundleSocketMessage.getInstance();
		System.out.println(rbem.getMessage("port", new Object[]{ "port",""}));
	}


}
