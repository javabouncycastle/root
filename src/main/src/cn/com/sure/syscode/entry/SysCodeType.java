/**
 * 
 */
package cn.com.sure.syscode.entry;

import javax.persistence.Id;

/**
 * @author Limin
 *  系统代码类别描述 - 标识类别 比如性别，职称等
 *  
 */
public class SysCodeType {
	
	@Id
    private Long id;
   
	/**
	 * 参数值(显示值)
	 */
    private String paraType;
    
    
	/**
	 * 备注
	 */
    private String notes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getParaType() {
		return paraType;
	}


	public void setParaType(String paraType) {
		this.paraType = paraType;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

}
