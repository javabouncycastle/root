package cn.com.sure.kpgtask.service;

public class test {
	
	public static void main(String [] args) {
		
		String str="1,2,3,34,15";
		String[] strs = str.split(",");
		String s = null ;
		for(int i=0;i<strs.length;i++) {
			
			
			
			s = s+";"+ strs[i];
			
		}
		System.out.println(s);
	}

}
