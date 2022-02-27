package com.jiguiyan.utils;

import java.util.List;

public class PageBean {
//	pageSize
//	currentPage
//	totalCount
//	totalPage
//	
	
	//页面大小
	private Integer pageSize;
	//当前页
	private Integer currentPage;
	//总条数
	private Integer totalCount;
	
	//总页数
	private Integer totalPage;
	
	//每页显示的数据
	private List list;
	
	public PageBean(Integer currentPage,Integer totalCount,Integer pageSize)
	{
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		
		//安全校验
		if(this.currentPage == null)
		{
			this.currentPage = 1;
		}
		if(this.pageSize == null)
		{
			this.pageSize = 8;
		}
		
		//计算totalPage
		this.totalPage = (int) Math.ceil(1.0 * this.totalCount/this.pageSize);
		
		
		//安全校验
		if(this.currentPage > this.totalPage)
		{
			this.currentPage = this.totalPage;
		}
		if(this.currentPage < 1)
		{
			this.currentPage = 1;
		}
		
		
	}

	//获得起始索引
	public Integer getStart()
	{
		return (this.currentPage-1)*this.pageSize;
	}

	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
	
	
	
	
}
