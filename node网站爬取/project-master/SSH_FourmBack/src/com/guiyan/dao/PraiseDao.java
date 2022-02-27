package com.guiyan.dao;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class PraiseDao  extends HibernateDaoSupport{

	public Integer getAllPraise() {
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql="select count(*) from praise";
		   NativeQuery query= session.createSQLQuery(sql);
		   BigInteger result=(BigInteger) query.uniqueResult();
		return result.intValue();
	}

}
