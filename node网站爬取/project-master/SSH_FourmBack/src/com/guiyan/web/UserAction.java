package com.guiyan.web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.guiyan.domain.User;
import com.guiyan.service.UserService;
import com.guiyan.utils.PageBean;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	public User user = new User();
	private UserService userService;
	private Integer currentPage;
	private String start;
	private String end;
	//private String username;
	private String userid;
	private String isDelete;
	private String deleteids;
	
	//������ԭ�û�
	public String resUserList() throws Exception {
		//�и�deleteids  xxx,xxxx,xxxx,xxxx,xxxx,xxx
		String[] ids = deleteids.split(",");
		//��String[] ��Ϊlist<String>  ����foreachѭ��
		List<String> list = Arrays.asList(ids);
		//foreachѭ��
		for(String id : list)
		{
			//ͨ��ÿһ�εı�����һ��һ�����޸Ļ�ԭ��isDelete�ֶΣ�
			userService.resetUserById(id);
		}
		//��ҳ����ʾ��Ϣ
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	//��ԭ�û�
	public String resetUser() throws Exception {
		
		userService.resetUserById(userid);
	
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//ɾ���û�
	public String deleteUserList() throws Exception {
		
		String[] ids = deleteids.split(",");//ʹ�ö��Ž����и�
		List<String> list = Arrays.asList(ids);
		
		for(String id : list)
		{
			userService.deleteUserById(id);
		}
		//��ҳ����ʾ��Ϣ
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	//�ж��û����Ƿ����
	public String checkUsername() throws Exception {
		
		//��װ������username
		boolean success = userService.checkUsername(user.getUsername());
		
		//��ҳ����ʾ��Ϣ��true||false��
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+success+"}");
		
		return null;
	}
	
	
	//����û�
	public String addUser() throws Exception {
		
		//��װUser
//		private Integer state;
		user.setState(1);
//		private String code;
		user.setCode(UUID.randomUUID().toString());
//		private String image;
		user.setImage("0");
//		private Integer level;
		user.setLevel(1);
//		private Integer coin;
		user.setCoin(1000);
//		private Integer isdelete;
		user.setIsdelete(0);
//		private String createtime;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		user.setCreatetime(format.format(new Date()));
		//����User
		userService.addUser(user);
		return "close";
	}
	
	
	//�����޸��û�id��ֵ
	public String toEditUser() throws Exception {
		//.out.println("toEditUser :"+ user.getId());
		return "userEdit";
	}
	//�޸��û�
	public String editUser() throws Exception {
		
		//System.out.println("----------------");
		//System.out.println("editUser :"+ user.getId());
		//System.out.println(user.getUsername());
		
		userService.editUser(user);
		
		return "close";
	}
	
	//ɾ���û�
	public String deleteUser() throws Exception {
	
		
		userService.deleteUserById(userid);
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//�޸��û�״̬
	public String changeUserState() throws Exception {
		
		userService.changeUserStateById(userid);
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//ͨ�������õ�pageBean
	public String getPageBeanByCondition() throws Exception {
		
		//����û����ݵ�startΪ�գ���ô���ǽ�ʱ������Ϊ0001-01-01
		if(start.isEmpty())
		{
			start="0001-01-01";
		}
		//����û����ݵ�endΪ�գ���ô���ǽ�ʱ������Ϊ9999-12-31
		if(end.isEmpty())
		{
			end="9999-12-31";
		}
		//usernameΪ�յĻ���hibername���԰����ǽ������
		
		PageBean userPageBean = userService.getuserPageBeanByCondition(currentPage,start,end,user.getUsername(),isDelete);
		
		//���ж����ݵĻ��ԣ����Ե�ʱ����Ҫ��user-list.jsp��ȡ
		ActionContext.getContext().put("start", start);
		ActionContext.getContext().put("end", end);
		ActionContext.getContext().put("username", user.getUsername());
		
		ActionContext.getContext().put("userPageBean", userPageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		return "userList";
	}
	
	//�õ������û�
	public String getAllUser() throws Exception {
		
		PageBean userPageBean = userService.getUserPageBean(currentPage,isDelete);
		
		ActionContext.getContext().put("userPageBean", userPageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		return "userList";
	}


	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public String getDeleteids() {
		return deleteids;
	}


	public void setDeleteids(String deleteids) {
		this.deleteids = deleteids;
	}


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}



	
	
}
