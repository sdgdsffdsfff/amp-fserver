package com.wisedu.amp.fserver.web.restful;

import java.util.Map;

/**
 * 分页查询请求体格式 注意：目前只支持一个字段排序，暂不支持字段组合排序
 * 
 * @author ych
 *
 */
public class PageRequest {

	/**
	 * 排序类型
	 */
	public static final String SORT_ASC = "ASC";
	public static final String SORT_DESC = "DESC";

	/**
	 * 默认起始页为第1页
	 */
	private static final int DEFAULT_PAGE_INDEX = 1;
	/**
	 * 默认每页15条
	 */
	private static final int DEFAULT_PAGE_SIZE = 15;

	private static final String DEFAULT_SORT = SORT_ASC;

	/**
	 * 排序字段，默认ID排序
	 */
	private String orderBy;

	/**
	 * 排序规则，升序ASC，降序DESC，默认升序
	 */
	private String sort = DEFAULT_SORT;

	/**
	 * 查询起始页
	 */
	private int pageIndex = DEFAULT_PAGE_INDEX;

	/**
	 * 每页条数，默认10
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 查询参数
	 */
	private Map<String, Object> params;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex < 0) {
			pageIndex = DEFAULT_PAGE_INDEX;
		}
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		if (pageSize < 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
