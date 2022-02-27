package com.guiyan.dao;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class AnswerDao extends HibernateDaoSupport{

	public Integer getAllAnswer() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from answer";
		NativeQuery query = session.createSQLQuery(sql);
		BigInteger result = (BigInteger) query.uniqueResult();
		
		
		return result.intValue();
	}

	public Integer finAnswerByStartAndEnd(String startDay, String endDay) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql ="select count(*) from answer where anstime > ? and anstime < ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, startDay);
		query.setParameter(2, endDay);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

}
