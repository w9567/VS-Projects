package com.guiyan.utils;

import java.util.List;

public class PageBean {
	private Integer currentPage;//��ʼҳ
	private Integer pageSize;//ҳ���С
	private Integer totalPage;//��ҳ��(��������/ҳ���С������ȡ��)
	private Integer totalCount;//������  select count(*)
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
			this.pageSize = 5;//Ĭ����ʾ5��
		}
		
		//�Զ������ҳ�� ����������ȡ��
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