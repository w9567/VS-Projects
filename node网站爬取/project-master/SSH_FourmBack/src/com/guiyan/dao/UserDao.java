package com.guiyan.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guiyan.domain.User;
import com.guiyan.utils.PageBean;

public class UserDao extends HibernateDaoSupport{

	
	public Integer getAllUser() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user";
		NativeQuery query = session.createSQLQuery(sql);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	
	public Integer getAllUserByIsDelete(String isDelete) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user where isdelete = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, isDelete);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List getPageBeanList(PageBean pageBean, String isDelete) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from user where isdelete = ? limit ?,?";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(User.class);
		query.setParameter(1, isDelete);
		query.setParameter(2, pageBean.getStart());//起始页的索引，在PageBean.java封装了该方法
		query.setParameter(3, pageBean.getPageSize());
		List list = query.list();
		return list;
	}
	
	//1、2通过开始日期与结束日期还有用户名条件进行查询
  //1
	public Integer getAllUserByCondition(String start, String end, String username, String isDelete) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		String sql = "select count(*) from user where createtime > ? and createtime < ? and username like ? and isdelete = ?";           
		
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, start);
		query.setParameter(2, end);
		query.setParameter(3, "%"+username+"%");
		query.setParameter(4, isDelete);
		BigInteger result = (BigInteger) query.uniqueResult();
		
		return result.intValue();
	}
   //2
	public List<User> getPageBeanListByCondition(PageBean pageBean, String start, String end, String username, String isDelete) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		//需要三个条件
		String sql = "select * from user where createtime > ? and createtime < ? and username like ? and isdelete = ? limit ?,?";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(User.class);
		query.setParameter(1, start);
		query.setParameter(2, end);
		query.setParameter(3, "%"+username+"%");
		query.setParameter(4, isDelete);
		query.setParameter(5, pageBean.getStart());
		query.setParameter(6, pageBean.getPageSize());
		
		List list = query.list();
		
		return list;
	}

	public User changeUserStateById(String userid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		User user = session.get(User.class, userid);
		return user;
	}

	public void deleteUserById(String userid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "update user set isdelete = 1 where id = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, userid);
		query.executeUpdate();
	}



	public User getUserById(String id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		User user = session.get(User.class, id);
		return user;
	}


	public void addUser(User user) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(user);
		
	}


	public Integer checkUsername(String username) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user where username = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, username);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}


	public void resetUserById(String userid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "update user set isdelete = 0 where id = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, userid);
		query.executeUpdate();
		
	}


	public Integer getAllUserByState(int state) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user where state = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, state);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}


	public Integer findUserByStartAndEnd(String startDay, String endDay) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user where createtime >= ? and createtime < ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, startDay);
		query.setParameter(2, endDay);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}



}
