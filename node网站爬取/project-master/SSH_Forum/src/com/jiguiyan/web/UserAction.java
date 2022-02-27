package com.jiguiyan.web;

import java.util.Random;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.jiguiyan.domain.User;
import com.jiguiyan.service.UserService;
import com.jiguiyan.utils.MailUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	public User user = new User();
	
	//激活的标志
	private String userCode;
	
	private UserService userService;
	
	//用户登出
	public String logout() throws Exception {
		
		ActionContext.getContext().getSession().remove("user");
		
		return "toLogin";
	}
	
	
	//用户登录
	public String login() throws Exception {
		
		int success = userService.checkUser(user);
		
		if(success == 0)
		{
			
			ActionContext.getContext().getSession().put("user", userService.findUserByUsernameReturnUser(user));
			return "toIndex";
		}
		else if(success == 1)
		{
			ActionContext.getContext().put("error", "用户名不存在！！");
			return "login";
		}
		else if(success == 2)
		{
			ActionContext.getContext().put("error", "密码错误！！");
			return "login";
		}
		else if(success == 3)
		{
			ActionContext.getContext().put("error", "用户未激活！！");
			return "login";
		}
		else
		{
			return "error";
		}

	}
	
	
	//用户激活
	public String active() throws Exception {
	
		userService.activeUser(userCode);
		
		return "toLogin";
	}
	
	// 校验用户名是否存在
	public String checkUsername() throws Exception {

		boolean success = userService.findUserByUsername(user.getUsername());

		ServletActionContext.getResponse().getWriter().write("{\"success\":" + success + "}");

		return null;
	}
	
	//用户注册
	public String register() throws Exception {

		// 没有的数据我们手动封装
		user.setState(0);
		user.setCode(UUID.randomUUID().toString());
		Random r = new Random();
		user.setImage("/images/"+r.nextInt(21)+".gif");
		
		user.setLevel(1);
		user.setCoin(1000);

		// 判断是否添加成功
		userService.addUser(user);

		//MailUtils.sendMail(user.getEmail(), "请激活", "恭喜你注册成功，请点击下面的链接进行激活吧！！<a href='http://localhost:8080/SSH_Forum/UserAction_active?userCode=\"+user.getCode()+\"'>点击这里</a>");
		
		MailUtils.sendMail(user.getEmail(), "请激活", "恭喜您注册成功，请点击下面的链接激活！！ <a href='http://localhost:8080/SSH_Forum/UserAction_active?userCode="+user.getCode()+"'>点击这里</a>");
		
		return "toRegisterSuccess";

	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
