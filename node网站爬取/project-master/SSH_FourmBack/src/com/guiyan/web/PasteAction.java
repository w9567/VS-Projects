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
	
	
	//������ԭ
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
	
	
	//��ԭ����
	public String resPaste() throws Exception {
		
		pasteService.resPasteById(pasteid);
		
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	
	}
		
	//�õ�ɾ����paste
	public String getDeletePaste() throws Exception {
		
		PageBean pastePageBean = pasteService.getDeletePageBean(isDelete);

		ActionContext.getContext().put("pastePageBean", pastePageBean);
		ActionContext.getContext().put("isDelete", isDelete);
			
		return "pasteList";
	}
	
	//�޸�paste
	public String editPaste() throws Exception {
		
		pasteService.editPaste(paste);
		
		
		return "close";
		
	}
	
	public String toEditPaste() throws Exception {
		
		ActionContext.getContext().put("id", paste.getId());
		
		return "pasteEdit";
		
	}
	
	//����ɾ��
	public String deletePasteList() throws Exception {
		
		//��װ�ĵ���deleteids�������и�и���������ʽ ��String -> String[]
		String[] ids = deleteids.split(",");
		//��String[] -> List
		List<String> list = Arrays.asList(ids);
		//ͨ��Listѭ����һ��һ��ɾ��
		for(String id : list)
		{
			pasteService.deletePasteById(id);
		}
		//��ҳ����ʾ��Ϣ
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
		
	}
	
	
	//ɾ������
	public String deletePaste() throws Exception {
		pasteService.deletePasteById(pasteid);
	

		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	// ����������ѯ����������paste
	public String getPageBeanByCondition() throws Exception {
		// ����û����ݵ�startΪ�գ���ô���ǽ�ʱ������Ϊ0001-01-01
		if (start.isEmpty()) {
			start = "0001-01-01";
		}
		// ����û����ݵ�endΪ�գ���ô���ǽ�ʱ������Ϊ9999-12-31
		if (end.isEmpty()) {
			end = "9999-12-31";
		}
		
		//�õ�pageBean
		PageBean pastePageBean = pasteService.getPageBeanByCondition(end,start,currentPage,paste.getTitle(),isDelete,paste.getSolve());
		
		//���ݵĻ���
		ActionContext.getContext().put("start", start);
		ActionContext.getContext().put("end", end);
		ActionContext.getContext().put("title", paste.getTitle());
		
		//ҳ�����Ϣ
		ActionContext.getContext().put("pastePageBean", pastePageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		ActionContext.getContext().put("solve", paste.getSolve());

		return "pasteList";
	}

	// �õ�solve=?����
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
