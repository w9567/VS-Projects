package com.jiguiyan.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jiguiyan.domain.User;

public class UserDao extends HibernateDaoSupport{

	public void addUser(User user) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(user);
	}

	public Long findUserByUsernameReturnNum(String username) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user where username = ?";
		NativeQuery query = session.createSQLQuery(sql);

		query.setParameter(1, username);
		
		BigInteger result = (BigInteger) query.uniqueResult();
		
		return result.longValue();
	}

	public void activeUser(String userCode) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "update user set state = 1 where code = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, userCode);
		query.executeUpdate();
		
	}

	public User findUserByUsernameReturnUser(User user) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from user where username = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(User.class);
		query.setParameter(1, user.getUsername());
		User temp = (User) query.uniqueResult();
		return temp;
	}

	public Integer findAllUserNum() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from user";
		NativeQuery query = session.createSQLQuery(sql);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List<User> getUserPageBean() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from user order by concat(coin,level) desc limit 0,8";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(User.class);
		List list = query.list();
		return list;
	}

}
