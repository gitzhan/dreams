package com.dreams.cloud.common.structs;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = 4515290528108547400L;
    
    // 默认每页大小： 10条记录
    private static final int DEFAULT_PAGE_SIZE = 10;
    // 默认起始页
    private static final int DEFAULT_START_PAGE = 1;

    private int pageNo = DEFAULT_START_PAGE;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int totalPage;// 总共几页
    private int total;// 总共数量

    private boolean hasNextPage;// 是否有下一页
    private boolean hasPreviousPage;// 是否有上一页
    private boolean firstPage;// 是否首页
    private boolean lastPage;// 是否尾页

    private List<T> rows;
    Page(){}
    /**
     * 因为涉及到计算，所以所有和分页相关的值都不能单独set，只能在构造阶段计算得出。
     * 结果集rows可以不在此例，单独进行设置
     * @param pageNo
     * @param pageSize
     * @param total
     */
    public Page(int pageNo, int pageSize, int total) {
    	//不合法的值应视为默认值，防止计算出现错误
    	if(pageSize<1){
    		pageSize=DEFAULT_PAGE_SIZE;
    	}
    	if(pageNo<1){
    		pageNo=DEFAULT_START_PAGE;
    	}
    	if(total<0){
    		total = 0;
    	}
    	
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = (total + pageSize - 1) / pageSize;

        if (total <= 0) {
            this.hasNextPage = false;
            this.hasPreviousPage = false;
            this.firstPage = true;
            this.lastPage = true;
        } else {
            this.hasNextPage = pageNo < totalPage ? true : false;
            this.hasPreviousPage = pageNo > 1 ? true : false;
            this.firstPage = pageNo <= 1 ? true : false;
            this.lastPage = pageNo >= totalPage ? true : false;
        }
    }

    public Page(int pageNo, int pageSize, int total, List<T> rows) {
        this(pageNo, pageSize, total);
        this.rows = rows;
    }

    public Page(PageParam pageParam, int total) {
        this(pageParam.getPageNo(), pageParam.getPageSize(), total);
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }
    public int getTotal() {
        return total;
    }
    public boolean isHasNextPage() {
        return hasNextPage;
    }
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
    public boolean isFirstPage() {
		return firstPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", total=" + total + ", hasNextPage=" + hasNextPage
				+ ", hasPreviousPage=" + hasPreviousPage + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", rows=" + rows + "]";
	}

}
