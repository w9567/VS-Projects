package com.jiguiyan.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jiguiyan.domain.Praise;

public class PraiseDao extends HibernateDaoSupport{

	//ÃÌº”‘ﬁ
	public void addPraise(Praise praise) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(praise);
		
	}

	//∏˘æ›id’“praise
	public Praise findPraiseByIdReturnPraise(String userid, String answerid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from praise where userid = ? and answerid = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(Praise.class);
		query.setParameter(1, userid);
		query.setParameter(2, answerid);
		Praise result = (Praise) query.uniqueResult();
		return result;
	}

	public void deletePraise(Praise praise) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(praise);
		
	}

}
