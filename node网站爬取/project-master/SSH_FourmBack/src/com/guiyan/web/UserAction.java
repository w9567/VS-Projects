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
	
	//批量还原用户
	public String resUserList() throws Exception {
		//切割deleteids  xxx,xxxx,xxxx,xxxx,xxxx,xxx
		String[] ids = deleteids.split(",");
		//将String[] 变为list<String>  方便foreach循环
		List<String> list = Arrays.asList(ids);
		//foreach循环
		for(String id : list)
		{
			//通过每一次的遍历，一个一个的修改还原（isDelete字段）
			userService.resetUserById(id);
		}
		//给页面提示信息
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	//还原用户
	public String resetUser() throws Exception {
		
		userService.resetUserById(userid);
	
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//删除用户
	public String deleteUserList() throws Exception {
		
		String[] ids = deleteids.split(",");//使用逗号进行切割
		List<String> list = Arrays.asList(ids);
		
		for(String id : list)
		{
			userService.deleteUserById(id);
		}
		//给页面提示信息
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	
	//判断用户名是否存在
	public String checkUsername() throws Exception {
		
		//封装并传递username
		boolean success = userService.checkUsername(user.getUsername());
		
		//给页面提示信息（true||false）
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+success+"}");
		
		return null;
	}
	
	
	//添加用户
	public String addUser() throws Exception {
		
		//封装User
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
		//传递User
		userService.addUser(user);
		return "close";
	}
	
	
	//传递修改用户id的值
	public String toEditUser() throws Exception {
		//.out.println("toEditUser :"+ user.getId());
		return "userEdit";
	}
	//修改用户
	public String editUser() throws Exception {
		
		//System.out.println("----------------");
		//System.out.println("editUser :"+ user.getId());
		//System.out.println(user.getUsername());
		
		userService.editUser(user);
		
		return "close";
	}
	
	//删除用户
	public String deleteUser() throws Exception {
	
		
		userService.deleteUserById(userid);
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//修改用户状态
	public String changeUserState() throws Exception {
		
		userService.changeUserStateById(userid);
		ServletActionContext.getResponse().getWriter().write("{\"success\":"+true+"}");
		
		return null;
	}
	
	//通过条件得到pageBean
	public String getPageBeanByCondition() throws Exception {
		
		//如果用户传递的start为空，那么我们将时间设置为0001-01-01
		if(start.isEmpty())
		{
			start="0001-01-01";
		}
		//如果用户传递的end为空，那么我们将时间设置为9999-12-31
		if(end.isEmpty())
		{
			end="9999-12-31";
		}
		//username为空的话，hibername可以帮我们解决问题
		
		PageBean userPageBean = userService.getuserPageBeanByCondition(currentPage,start,end,user.getUsername(),isDelete);
		
		//进行对数据的回显，回显的时候需要在user-list.jsp获取
		ActionContext.getContext().put("start", start);
		ActionContext.getContext().put("end", end);
		ActionContext.getContext().put("username", user.getUsername());
		
		ActionContext.getContext().put("userPageBean", userPageBean);
		ActionContext.getContext().put("isDelete", isDelete);
		return "userList";
	}
	
	//得到所有用户
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
