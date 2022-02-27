package com.jiguiyan.web;


import com.jiguiyan.service.PasteService;
import com.jiguiyan.service.UserService;
import com.jiguiyan.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetDataAction extends ActionSupport{

	private PasteService pasteService;
	private UserService userService;
	private Integer currentPage;

	public String getData() throws Exception {
		
		//�õ�����
		PageBean pastePageBean = pasteService.getPastePageBean(currentPage);
		ActionContext.getContext().put("pastePageBean", pastePageBean);
		//�õ��������
		PageBean glanceoverPageBean = pasteService.getGlanceoverPageBean(currentPage);
		ActionContext.getContext().put("glanceoverPageBean", glanceoverPageBean);
		//�õ��������
		PageBean ansnumPageBean = pasteService.getAnsnumPageBean(currentPage);
		ActionContext.getContext().put("ansnumPageBean", ansnumPageBean);
		//�õ�ר������
		PageBean userPageBean = userService.getUserPageBean(currentPage);
		ActionContext.getContext().put("userPageBean", userPageBean);
		
		return "index";
	}

	public PasteService getPasteService() {
		return pasteService;
	}

	public void setPasteService(PasteService pasteService) {
		this.pasteService = pasteService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	
}
