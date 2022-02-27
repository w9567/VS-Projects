package com.jiguiyan.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jiguiyan.domain.Answer;
import com.jiguiyan.domain.Paste;
import com.jiguiyan.domain.User;
import com.jiguiyan.service.AnswerService;
import com.jiguiyan.service.PasteService;
import com.jiguiyan.service.PraiseService;
import com.jiguiyan.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PasteAction extends ActionSupport implements ModelDriven<Paste> {

	public Paste paste = new Paste();

	private String pasteid;
	private PasteService pasteService;
	private AnswerService answerService;
	private PraiseService praiseService;
	
	//�������
	public String solvePaste() throws Exception {
		
		
		
		pasteService.solvePasteByIdAndAnswerid(pasteid,paste.getAnswerid());
		ActionContext.getContext().put("pasteid", pasteid);
		//getDetail();
		return "toDetail";
	}
	
	// �鿴������ϸ��Ϣ
	public String getDetail() throws Exception {

		User user = (User) ActionContext.getContext().getSession().get("user");
		
		// �õ��������
		PageBean glanceoverPageBean = pasteService.getGlanceoverPageBean(null);
		ActionContext.getContext().put("glanceoverPageBean", glanceoverPageBean);
		// �õ��������
		PageBean ansnumPageBean = pasteService.getAnsnumPageBean(null);
		ActionContext.getContext().put("ansnumPageBean", ansnumPageBean);

		//�������
		Paste paste = pasteService.findPasteByIdReturnPaste(pasteid);
		ActionContext.getContext().put("paste", paste);

		//��ø����ӵ����лظ�
		List<Answer> answerList = answerService.findAllAnswerByPasteid(pasteid);
		for(Answer answer : answerList)
		{
			if(user != null)
			{
				//�ж��û��Ƿ�����˸ûظ�
				boolean success = praiseService.findPraiseByIdReturnPraise(user.getId(),answer.getId());
				if(success)
				{
					//�����ѯ���û������˸ûظ�����loginUserIsAgree����Ϊ1
					answer.setLoginUserIsAgree(1);
				}
				else
				{
					//���û�в�ѯ���û������˸ûظ�����loginUserIsAgree����Ϊ0
					answer.setLoginUserIsAgree(0);
				}
			}
			else
			{
				//���û��¼����loginUserIsAgree����Ϊ0
				answer.setLoginUserIsAgree(0);
			}
		}
		ActionContext.getContext().put("answerList", answerList);
		
		return "detail";
	}

	// �������
	public String addPaste() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");

		if (user == null) {
			ActionContext.getContext().put("error", "ֻ�е�¼֮��ſ��Է����ӣ���");
			return "error";
		}

//		private Integer ansnum;
		paste.setAnsnum(0);
//		private String createtime;
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String createtime = format.format(date);
		paste.setCreatetime(createtime);
//		private Integer glanceover;
		paste.setGlanceover(0);
//		//�Ƿ������0��δ�� 1��������
//		private Integer solve;
		paste.setSolve(0);
//		private Integer delete;
		paste.setIsdelete(0);
//		private User user;

		paste.setUser(user);

		pasteService.addPaste(paste);

		return "toIndex";
	}

	public PasteService getPasteService() {
		return pasteService;
	}

	public void setPasteService(PasteService pasteService) {
		this.pasteService = pasteService;
	}

	public String getPasteid() {
		return pasteid;
	}

	public void setPasteid(String pasteid) {
		this.pasteid = pasteid;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}


	public PraiseService getPraiseService() {
		return praiseService;
	}

	public void setPraiseService(PraiseService praiseService) {
		this.praiseService = praiseService;
	}

	@Override
	public Paste getModel() {
		// TODO Auto-generated method stub
		return paste;
	}

}
