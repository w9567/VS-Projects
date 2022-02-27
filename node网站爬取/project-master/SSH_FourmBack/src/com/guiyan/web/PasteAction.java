package com.guiyan.web;

import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.guiyan.domain.Paste;
import com.guiyan.service.PasteService;
import com.guiyan.utils.PageBean;

public class PasteAction extends ActionSupport implements ModelDriven<Paste> {

	private PasteService pasteService;
	public Paste paste = new Paste();
	private String end;
	private String start;
	private Integer currentPage;
	
	private String isDelete;
	private String pasteid;
	private String deleteids;
	
	
	//批量还原
	public String resPasteList() throws Exception {
		String[] split = deleteids.split(",");
		List<String> list = Arrays.asList(split);
		for(String id : list)
		{
			pasteService.resPasteById(id);
		}
		
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		return null;
	}
	
	
	//还原帖子
	public String resPaste() throws Exception {
		
		pasteService.resPasteById(pasteid);
		
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	
	}
		
	//得到删除的paste
	public String getDeletePaste() throws Exception {
		
		PageBean pastePageBean = pasteService.getDeletePageBean(isDelete);

		ActionContext.getContext().put("pastePageBean", pastePageBean);
		ActionContext.getContext().put("isDelete", isDelete);
			
		return "pasteList";
	}
	
	//修改paste
	public String editPaste() throws Exception {
		
		pasteService.editPaste(paste);
		
		
		return "close";
		
	}
	
	public String toEditPaste() throws Exception {
		
		ActionContext.getContext().put("id", paste.getId());
		
		return "pasteEdit";
		
	}
	
	//批量删除
	public String deletePasteList() throws Exception {
		
		//封装的到的deleteids，进行切割，切割成数组的形式 把String -> String[]
		String[] ids = deleteids.split(",");
		//将String[] -> List
		List<String> list = Arrays.asList(ids);
		//通过List循环，一个一个删除
		for(String id : list)
		{
			pasteService.deletePasteById(id);
		}
		//给页面提示信息
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
		
	}
	
	
	//删除帖子
	public String deletePaste() throws Exception {
		pasteService.deletePasteById(pasteid);
	

		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	// 根据条件查询符合条件的paste
	public String getPageBeanByCondition() throws Exception {
		// 如果用户传递的start为空，那么我们将时间设置为0001-01-01
		if (start.isEmpty()) {
			start = "0001-01-01";
		}
		// 如果用户传递的end为空，那么我们将时间设置为9999-12-31
		if (end.isEmpty()) {
			end = "9999-12-31";
		}
		
		//得到pageBean
		PageBean pastePageBean = pasteService.getPageBeanByCondition(end,start,currentPage,paste.getTitle(),isDelete,paste.getSolve());
		
		//数据的回显
		ActionContext.getContext().put("start", start);
		ActionContext.getContext().put("end", end);
		ActionContext.getContext().put("title", paste.getTitle());
		
		//页面的信息
		ActionContext.getContext().put("pastePageBean", pastePageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		ActionContext.getContext().put("solve", paste.getSolve());

		return "pasteList";
	}

	// 得到solve=?帖子
	public String getSolvePaste() throws Exception {

		PageBean pastePageBean = pasteService.getSolvePageBean(paste.getSolve());

		ActionContext.getContext().put("pastePageBean", pastePageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		ActionContext.getContext().put("solve", paste.getSolve());
		
		return "pasteList";
	}

	public PasteService getPasteService() {
		return pasteService;
	}

	public void setPasteService(PasteService pasteService) {
		this.pasteService = pasteService;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	
	
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getPasteid() {
		return pasteid;
	}


	public void setPasteid(String pasteid) {
		this.pasteid = pasteid;
	}


	public String getDeleteids() {
		return deleteids;
	}


	public void setDeleteids(String deleteids) {
		this.deleteids = deleteids;
	}


	@Override
	public Paste getModel() {
		// TODO Auto-generated method stub
		return paste;
	}

}
