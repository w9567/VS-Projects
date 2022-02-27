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
	
	//����ı�־
	private String userCode;
	
	private UserService userService;
	
	//�û��ǳ�
	public String logout() throws Exception {
		
		ActionContext.getContext().getSession().remove("user");
		
		return "toLogin";
	}
	
	
	//�û���¼
	public String login() throws Exception {
		
		int success = userService.checkUser(user);
		
		if(success == 0)
		{
			
			ActionContext.getContext().getSession().put("user", userService.findUserByUsernameReturnUser(user));
			return "toIndex";
		}
		else if(success == 1)
		{
			ActionContext.getContext().put("error", "�û��������ڣ���");
			return "login";
		}
		else if(success == 2)
		{
			ActionContext.getContext().put("error", "������󣡣�");
			return "login";
		}
		else if(success == 3)
		{
			ActionContext.getContext().put("error", "�û�δ�����");
			return "login";
		}
		else
		{
			return "error";
		}

	}
	
	
	//�û�����
	public String active() throws Exception {
	
		userService.activeUser(userCode);
		
		return "toLogin";
	}
	
	// У���û����Ƿ����
	public String checkUsername() throws Exception {

		boolean success = userService.findUserByUsername(user.getUsername());

		ServletActionContext.getResponse().getWriter().write("{\"success\":" + success + "}");

		return null;
	}
	
	//�û�ע��
	public String register() throws Exception {

		// û�е����������ֶ���װ
		user.setState(0);
		user.setCode(UUID.randomUUID().toString());
		Random r = new Random();
		user.setImage("/images/"+r.nextInt(21)+".gif");
		
		user.setLevel(1);
		user.setCoin(1000);

		// �ж��Ƿ���ӳɹ�
		userService.addUser(user);

		//MailUtils.sendMail(user.getEmail(), "�뼤��", "��ϲ��ע��ɹ���������������ӽ��м���ɣ���<a href='http://localhost:8080/SSH_Forum/UserAction_active?userCode=\"+user.getCode()+\"'>�������</a>");
		
		MailUtils.sendMail(user.getEmail(), "�뼤��", "��ϲ��ע��ɹ���������������Ӽ���� <a href='http://localhost:8080/SSH_Forum/UserAction_active?userCode="+user.getCode()+"'>�������</a>");
		
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
