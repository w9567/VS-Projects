package com.guiyan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guiyan.dao.UserDao;
import com.guiyan.domain.User;
import com.guiyan.utils.PageBean;

public class UserService {

	private UserDao userDao;
	
	public List<Integer> getAWeekDayData(long currentTimeMillis, int day) {
		//����һ��ĺ���ֵ
		long dayMillis = 24*60*60*1000;
		//��ȡ���ܵ�һ�������
		long firstOfWeekMills = currentTimeMillis - day*dayMillis;
		List<Integer> list = new ArrayList<Integer>();
		//ʹ��forѭ���õ���ǰһ�ܵ����ӣ�7������ӣ�
		for(long i = firstOfWeekMills;i<firstOfWeekMills + 7*dayMillis; i+=dayMillis)
		{
			
			Date sDate = new Date(i);
			Date eDate = new Date(i+dayMillis);
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			//2018-9-10
			String startDay = formate.format(sDate);
			//2018-9-11
			String endDay = formate.format(eDate);
			Integer count = userDao.findUserByStartAndEnd(startDay,endDay);
			list.add(count);
		}
		
		return list;
	}

	
	
	public Integer getAllUserByState(int state) {
		return userDao.getAllUserByState(state);
		

	}

	public void resetUserById(String userid) {
		userDao.resetUserById(userid);
		
	}
	
	public boolean checkUsername(String username) {
		Integer count = userDao.checkUsername(username);
		return count==0?true:false;
	}

	
	
	public void addUser(User user) {
		userDao.addUser(user);
		
	}

	
	public void editUser(User user) {
		User getUser = userDao.getUserById(user.getId());
		getUser.setName(user.getName());
		getUser.setPassword(user.getPassword());
		getUser.setEmail(user.getEmail());
	}
	
	public void deleteUserById(String userid) {
		userDao.deleteUserById(userid);
		
	}

	
	public void changeUserStateById(String userid) {
		//û��ǣ�浽isDelete
		User user = userDao.changeUserStateById(userid);

		if(user.getState()==1)
		{
			user.setState(0);
		}
		else
		{
			user.setState(1);
		}
	}

	
	
	public PageBean getuserPageBeanByCondition(Integer currentPage, String start, String end, String username, String isDelete) {
		
		//�õ������������û���Ŀ
		Integer totalCount = userDao.getAllUserByCondition(start,end,username,isDelete);
		PageBean pageBean = new PageBean(currentPage, 6, totalCount);//6��ʾҳ����ʾ������
		//todo ���������õ�list����װlist
		List<User> list = userDao.getPageBeanListByCondition(pageBean,start,end,username,isDelete);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	public PageBean getUserPageBean(Integer currentPage, String isDelete) {
		
		Integer totalCount = userDao.getAllUserByIsDelete(isDelete);
		PageBean pageBean = new PageBean(currentPage, 6, totalCount);
		List<User> list = userDao.getPageBeanList(pageBean,isDelete);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	public Integer getAllUser() {
		
		return userDao.getAllUser();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}








	








	

	



}
