package com.jiguiyan.service;

import java.util.List;

import com.jiguiyan.dao.UserDao;
import com.jiguiyan.domain.User;
import com.jiguiyan.utils.PageBean;

public class UserService {

	private UserDao userDao;
	
	public PageBean getUserPageBean(Integer currentPage) {
		Integer totalCount = userDao.findAllUserNum();
		PageBean userPageBean = new PageBean(currentPage, totalCount, 8);
		List<User> list = userDao.getUserPageBean();
		userPageBean.setList(list);
		
		return userPageBean;
	}

	
	public User findUserByUsernameReturnUser(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByUsernameReturnUser(user);
	}
	
	
	public int checkUser(User user) {
		User temp = userDao.findUserByUsernameReturnUser(user);
		//�û���������
		if(temp == null)
		{
			return 1;
		}
		//�ж������Ƿ���ͬ
		if(temp.getPassword().equals(user.getPassword()))
		{
			if(temp.getState() == 1)
			{
				//��¼�ɹ�
				return 0;
			}
			else
			{
				//û�м���
				return 3;
			}
		}
		else
		{
			//�������
			return 2;
		}
	}
	
	
	
	public void activeUser(String userCode) {
		userDao.activeUser(userCode);
	}
	
	public void addUser(User user) {
		
		userDao.addUser(user);
		
	}
	public boolean findUserByUsername(String username) {
		Long count = userDao.findUserByUsernameReturnNum(username);
		return count==0?true:false;
	}
	
	
	

	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}







	



	

}
