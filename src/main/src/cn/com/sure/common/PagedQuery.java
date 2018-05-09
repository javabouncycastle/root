package cn.com.sure.common;

import java.util.ArrayList;
import java.util.List;

/** 
 * @copyright: SURESEC
 * @author: Xuhaoming
 * @version：2017年9月13日 
 * 
 */
// 和DataTable 做映射
public class PagedQuery{
	
	private Integer start; //数据起始位置
	private Integer length; // 返回记录数
	private String draw; // 获取请求次数
	private String recordsTotal = "0";//总记录数
	private String recordsFiltered = "0";//过滤后总记录数
	private String searchValue = "";//过滤
	private String orderCol;//排序行
	private String orderDir;//排序行方式
	private List data = new ArrayList(); //返回数据
	
	public String getOrderCol() {
		return orderCol;
	}
	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public String getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public List getData() {
		return data;
	}
	public void setData(List list) {
		this.data = list;
	}
	
	

}
