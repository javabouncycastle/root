package cn.com.sure.syscode.entry;

import javax.persistence.Id;

public class KmSysCode {

	@Id
    private Long id;
	
	/**
	 * 参数名称
	 */
	private String paraCode;
    
	/**
	 * 参数值(显示值)
	 */
    private String paraValue;
    
	/**
	 * 参数类别 - 引用km_sys_code_type表
	 */
    private KmSysCodeType paraType;
    
	/**
	 * 是否有效
	 */
    private Integer isValid;
        
	/**
	 * 备注
	 */
    private String notes;
    

	public KmSysCodeType getParaType() {
		return paraType;
	}

	public void setParaType(KmSysCodeType paraType) {
		this.paraType = paraType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
}
