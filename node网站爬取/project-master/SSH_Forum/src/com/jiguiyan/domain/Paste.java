package com.jiguiyan.domain;

import java.util.HashSet;
import java.util.Set;

public class Paste {

//	id varchar(50)primary key,
//	title varchar(1000) not null,
//	content varchar(3000) not null,
//	offer int not null,
//	ansnum int default 0,
//	createtime varchar(100) not null,
//	glanceover int default 0,
//	solve int default 0,
//	delete int default 0,
//	
//	answerid varchar(50),
//	userid varchar(50)
	
	private String id;
	private String title;
	private String content;
	private Integer offer;
	private Integer ansnum;
	private String createtime;
	private Integer glanceover;
	//ÊÇ·ñ½áÌû
	private Integer solve;
	private Integer isdelete;
	
	
	private String answerid;
	
	private User user;
	
	private Set<User> userAnswerSet = new HashSet<User>();
	
	
	public Set<User> getUserAnswerSet() {
		return userAnswerSet;
	}
	public void setUserAnswerSet(Set<User> userAnswerSet) {
		this.userAnswerSet = userAnswerSet;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content.replaceAll("<br />", ".");
	}
	
	
	public Integer getOffer() {
		return offer;
	}
	public void setOffer(Integer offer) {
		this.offer = offer;
	}
	public Integer getAnsnum() {
		return ansnum;
	}
	public void setAnsnum(Integer ansnum) {
		this.ansnum = ansnum;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getGlanceover() {
		return glanceover;
	}
	public void setGlanceover(Integer glanceover) {
		this.glanceover = glanceover;
	}
	public Integer getSolve() {
		return solve;
	}
	public void setSolve(Integer solve) {
		this.solve = solve;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	
	
	
	
}
