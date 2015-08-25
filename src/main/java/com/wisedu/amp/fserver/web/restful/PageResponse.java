package com.wisedu.amp.fserver.web.restful;

import java.util.List;

/**
 * 分页查询集合的格式
 * 
 * @author ych
 *
 */
public class PageResponse<T> {
	/**
	 * 数据集合 数组、collection
	 */
	private List<T> data;
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 起始页
	 */
	private int pageIndex;
	/**
	 * 每页记录数
	 */
	private int pageSize;

	public List<T> getData() {
		return data;
	}

	public PageResponse<T> setData(List<T> data) {
		this.data = data;
		return this;
	}

	public long getTotal() {
		return total;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageResponse [data=" + data + ", total=" + total
				+ ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}

}
