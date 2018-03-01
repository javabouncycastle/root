package cn.com.sure.algorthm.entry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keypair_algorithm")
public class KeyPairAlgorithm {
	
	public KeyPairAlgorithm(){
		
	}
	
	public KeyPairAlgorithm(Long id){
		this.id=id;
	}
	
	/**
	 * 主键
	 */
	@Id
	private Long id;
	
	/**
	 * 别名
	 */
	private String name;

	
	private String algorithmOid;//算法OID
	
	private String algorithmName;//算法英文缩写
	/**
	 * 密钥长度
	 */
	private Integer keysize;
	
	private String notes;//

    private Integer isValid;//是否有效,0无效，1有效

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlgorithmOid() {
		return algorithmOid;
	}

	public void setAlgorithmOid(String algorithmOid) {
		this.algorithmOid = algorithmOid;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public Integer getKeysize() {
		return keysize;
	}

	public void setKeysize(Integer keysize) {
		this.keysize = keysize;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

}
