package com.dreams.cloud.common.structs;

import java.io.Serializable;

public class PageParam implements Serializable {

	private static final long serialVersionUID = -1357849063569557357L;

	private int pageNo;

	private int pageSize;

	public PageParam() {

	}

	public PageParam(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return 偏移量
	 */
	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

	@Override
	public String toString() {
		return "PageParam [pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}

}
