package cn.com.sure.common;

public class ReCode {
	
	private String retrunCode;//成功还是失败
	
	private String des;//返回信息描述
	
	private Object data;//有时候额外传值到前端 放到这里边 暂时不用

	public String getRetrunCode() {
		return retrunCode;
	}

	public void setRetrunCode(String retrunCode) {
		this.retrunCode = retrunCode;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
