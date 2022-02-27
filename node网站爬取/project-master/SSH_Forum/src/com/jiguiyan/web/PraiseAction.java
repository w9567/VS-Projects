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
	
	
	//取消赞
	public String deletePraise() throws Exception {
		//肯定可以获得（用户未登录不能点赞，只有登录后才可以点赞）
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		//封装user
		//封装answer
		Answer answer = answerService.findAnswerByIdReturnAnswer(answerid);
		//封装primarykey
		PrimaryKey primaryKey = new PrimaryKey();
		primaryKey.setAnswer(answer);
		primaryKey.setUser(user);
		//封装praise
		Praise praise = new Praise();
		praise.setPrimaryKey(primaryKey);
		
		praiseService.deletePraise(praise);
		answerService.deleteAnswerAgree(answerid);
		
		ActionContext.getContext().put("pasteid", pasteid);
		return "toDetail";
		
	}
	
	//添加赞
	public String addPraise() throws Exception {
		
		//获取登录的用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null)
		{
			//如果未登录，转发到登录页面
			ActionContext.getContext().put("error", "未登录不允许点赞！！");
			return "error";
		}
		
		//根据answerid查找Answer对象
		Answer answer = answerService.findAnswerByIdReturnAnswer(answerid);
		//声明联合主键
		PrimaryKey primaryKey = new PrimaryKey();
		//放置对象
		primaryKey.setAnswer(answer);
		primaryKey.setUser(user);
		
		//创建赞的类
		Praise praise = new Praise();
		//将联合主键放入对象中
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
