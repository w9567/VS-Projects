package com.jiguiyan.domain;

public class Answer {
//	id varchar(50) primary key,
//	userid varchar(50) not null,
//	pasteid varchar(50) not null,
//	content varchar(3000) not null,
//	anstime varchar(100) not null,
//	agree int default 0,
//	solve int default 0
	
	private String id;
	//内容
	private String content;
	//回复时间
	private String anstime;
	//同意数
	private Integer agree;
	//是否解决
	private Integer solve;
	
	private User user;
	
	private Paste paste;
	
	//登录的用户是否点赞了改回复
	private Integer loginUserIsAgree;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content.replaceAll("<br />", ".");;
	}
	public String getAnstime() {
		return anstime;
	}
	public void setAnstime(String anstime) {
		this.anstime = anstime;
	}
	public Integer getAgree() {
		return agree;
	}
	public void setAgree(Integer agree) {
		this.agree = agree;
	}
	public Integer getSolve() {
		return solve;
	}
	public void setSolve(Integer solve) {
		this.solve = solve;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Paste getPaste() {
		return paste;
	}
	public void setPaste(Paste paste) {
		this.paste = paste;
	}
	public Integer getLoginUserIsAgree() {
		return loginUserIsAgree;
	}
	public void setLoginUserIsAgree(Integer loginUserIsAgree) {
		this.loginUserIsAgree = loginUserIsAgree;
	}
	
	
	
	
	
}
