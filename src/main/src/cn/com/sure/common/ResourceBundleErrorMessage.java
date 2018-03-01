package cn.com.sure.common;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

//����ģʽ
public class ResourceBundleErrorMessage {
	
	public static final String propertiesFilePath = "Messages_SURE";

	private static ResourceBundleErrorMessage resourceBundleErrorMessage;
	
	private Map<String,MessageFormat> resourceBundleMap = new HashMap<String,MessageFormat>();
	
	public static synchronized ResourceBundleErrorMessage getInstance(){
		
		if(resourceBundleErrorMessage == null){
			resourceBundleErrorMessage = new ResourceBundleErrorMessage();
			resourceBundleErrorMessage.reload();
		}
		
		return resourceBundleErrorMessage;

	}
	
	public void reload(){
		
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesFilePath, new Locale("", "", ""));
		Enumeration<String> keys = bundle.getKeys();
		while(keys.hasMoreElements()){
		      String key = keys.nextElement();
		      resourceBundleMap.put(key,new MessageFormat(bundle.getString(key)));
		}
		
	}
	
	private ResourceBundleErrorMessage(){}
	
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
		ResourceBundleErrorMessage rbem = ResourceBundleErrorMessage.getInstance();
		System.out.println(rbem.getMessage(9, new Object[]{ "11111151","1"}));
	}


}
