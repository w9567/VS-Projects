package com.jiguiyan.web;

import com.jiguiyan.domain.Answer;
import com.jiguiyan.domain.Praise;
import com.jiguiyan.domain.User;
import com.jiguiyan.service.AnswerService;
import com.jiguiyan.service.PraiseService;
import com.jiguiyan.vo.PrimaryKey;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PraiseAction extends ActionSupport{

	private String answerid;
	private String pasteid;
	private AnswerService answerService;
	private PraiseService praiseService;
	
	
	//ȡ����
	public String deletePraise() throws Exception {
		//�϶����Ի�ã��û�δ��¼���ܵ��ޣ�ֻ�е�¼��ſ��Ե��ޣ�
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		//��װuser
		//��װanswer
		Answer answer = answerService.findAnswerByIdReturnAnswer(answerid);
		//��װprimarykey
		PrimaryKey primaryKey = new PrimaryKey();
		primaryKey.setAnswer(answer);
		primaryKey.setUser(user);
		//��װpraise
		Praise praise = new Praise();
		praise.setPrimaryKey(primaryKey);
		
		praiseService.deletePraise(praise);
		answerService.deleteAnswerAgree(answerid);
		
		ActionContext.getContext().put("pasteid", pasteid);
		return "toDetail";
		
	}
	
	//�����
	public String addPraise() throws Exception {
		
		//��ȡ��¼���û�
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null)
		{
			//���δ��¼��ת������¼ҳ��
			ActionContext.getContext().put("error", "δ��¼��������ޣ���");
			return "error";
		}
		
		//����answerid����Answer����
		Answer answer = answerService.findAnswerByIdReturnAnswer(answerid);
		//������������
		PrimaryKey primaryKey = new PrimaryKey();
		//���ö���
		primaryKey.setAnswer(answer);
		primaryKey.setUser(user);
		
		//�����޵���
		Praise praise = new Praise();
		//�������������������
		praise.setPrimaryKey(primaryKey);
		
		praiseService.addPraise(praise);
		answerService.addAnswerAgree(answerid);
		ActionContext.getContext().put("pasteid", pasteid);
		
		return "toDetail";
	}

	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
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

	public String getPasteid() {
		return pasteid;
	}

	public void setPasteid(String pasteid) {
		this.pasteid = pasteid;
	}

	
	
}
