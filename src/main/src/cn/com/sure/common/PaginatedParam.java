package cn.com.sure.common;

public class PaginatedParam {
	
public static final int DEFAULT_PAGE_SIZE = 8;
	
    private int objectsPerPage = DEFAULT_PAGE_SIZE;
	private int pageNumber;     // ��ǰҳ��
	
	private int firstResult;
	private int maxResults;
	
	public int getObjectsPerPage() {
		return objectsPerPage;
	}
	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}	

}
