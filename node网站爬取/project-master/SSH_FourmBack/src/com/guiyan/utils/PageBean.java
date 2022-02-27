package com.guiyan.utils;

import java.util.List;

public class PageBean {
	private Integer currentPage;//起始页
	private Integer pageSize;//页面大小
	private Integer totalPage;//总页数(（总条数/页面大小）向上取整)
	private Integer totalCount;//总条数  select count(*)
	private List list;//list
	
	public PageBean(Integer currentPage, Integer pageSize,Integer totalCount)
	{
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		if(this.currentPage == null)
		{
			this.currentPage = 1;
		}
		if(this.pageSize == null)
		{
			this.pageSize = 5;//默认显示5条
		}
		
		//自动算出总页数 ，进行向上取整
		  this.totalPage = (int) Math.ceil(1.0* this.totalCount / this.pageSize );
		
		if(this.currentPage > this.totalPage)
		{
			this.currentPage = this.totalPage;
		}
		if(this.currentPage < 1)
		{
			this.currentPage = 1;
		}
		
		
	
				
		
	}
	
	
	public Integer getStart()
	{
		return (this.currentPage - 1)*this.pageSize;
	}
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	
	
	
}