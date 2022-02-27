package com.jiguiyan.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jiguiyan.domain.Answer;
import com.jiguiyan.domain.Paste;
import com.jiguiyan.domain.User;
import com.jiguiyan.service.AnswerService;
import com.jiguiyan.service.PasteService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AnswerAction extends ActionSupport implements ModelDriven<Answer> {

	public Answer answer = new Answer();
	private String pasteid;
	private AnswerService answerService;
	private PasteService pasteService;
	private String answerid;
	
	//删除answer
	public String deleteAnswer() throws Exception {
		
		
		
		Paste paste = pasteService.findPasteByIdReturnPaste(pasteid);
		
		answerService.deleteAnswerById(answerid,paste);
		ActionContext.getContext().put("pasteid", pasteid);
		return "toDetail";
	}
	
	//添加answer
	public String addAnswer() throws Exception {
		
		User user =	(User) ActionContext.getContext().getSession().get("user");
		if(user == null)
		{
			ActionContext.getContext().put("error", "未登录不能回复！！");
			return "error";
		}
//		//回复时间
//		private String anstime;
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String anstime = format.format(date);
		answer.setAnstime(anstime);
//		//同意数
//		private Integer agree;
		answer.setAgree(0);
//		//是否解决
//		private Integer solve;
		answer.setSolve(0);
//		
//		private User user;
		answer.setUser(user);
//		
//		private Paste paste;
		Paste paste = pasteService.findPasteByIdReturnPaste(pasteid);
		answer.setPaste(paste);
		
		
		answerService.addAnswer(answer,paste);
		
		
		
		return "toDetail";
	}

	
	
	public AnswerService getAnswerService() {
		return answerService;
	}



	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}



	@Override
	public Answer getModel() {
		// TODO Auto-generated method stub
		return answer;
	}



	public String getPasteid() {
		return pasteid;
	}



	public void setPasteid(String pasteid) {
		this.pasteid = pasteid;
	}



	public PasteService getPasteService() {
		return pasteService;
	}



	public void setPasteService(PasteService pasteService) {
		this.pasteService = pasteService;
	}

	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	
	
}
