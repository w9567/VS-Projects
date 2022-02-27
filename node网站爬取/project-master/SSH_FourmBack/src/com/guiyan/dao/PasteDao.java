package com.guiyan.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guiyan.domain.Paste;
import com.guiyan.utils.PageBean;

public class PasteDao extends HibernateDaoSupport{

	public Integer getAllPaste() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from paste";
		NativeQuery query = session.createSQLQuery(sql);
		BigInteger result = (BigInteger) query.uniqueResult();
		
		
		return result.intValue();
	}

	public Integer getSolvePasteCount(Integer solve) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from paste where solve = ? and isdelete = 0";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, solve);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List<Paste> getSolvePaste(Integer solve,PageBean pageBean) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from paste where solve = ? and isdelete = 0 limit ?,?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, solve);
		query.setParameter(2, pageBean.getStart());
		query.setParameter(3, pageBean.getPageSize());
		query.addEntity(Paste.class);
		List<Paste> list = query.list();
		return list;
	}

	public Integer getAllPasteByCondition(String start, String end, String title, String isDelete,Integer solve) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = null;
		if(solve==null)
		{
			sql = "select count(*) from paste where createtime > ? and createtime < ? and title like ? and isdelete = ?";
		}
		else
		{
			sql = "select count(*) from paste where createtime > ? and createtime < ? and title like ? and isdelete = ? and solve = ?";		
		}
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, start);
		query.setParameter(2, end);
		query.setParameter(3, "%"+title+"%");
		query.setParameter(4, isDelete);
		if(solve!=null)
		{
			query.setParameter(5, solve);
		}
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List<Paste> getPageBeanListByCondition(String start, String end, String title, String isDelete,Integer solve,PageBean pageBean) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = null;
		if(solve == null)
		{
			sql = "select * from paste where createtime > ? and createtime < ? and title like ? and isdelete = ? limit ?,?";
		}
		else
		{
			sql = "select * from paste where createtime > ? and createtime < ? and title like ? and isdelete = ? and solve = ? limit ?,?";			
		}
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(Paste.class);
		query.setParameter(1, start);
		query.setParameter(2, end);
		query.setParameter(3, "%"+title+"%");
		query.setParameter(4, isDelete);
		if(solve==null)
		{
			query.setParameter(5, pageBean.getStart());
			query.setParameter(6, pageBean.getPageSize());
		}
		else
		{
			query.setParameter(5, solve);
			query.setParameter(6, pageBean.getStart());
			query.setParameter(7, pageBean.getPageSize());
		}
		
		List<Paste> list = query.list();
		return list;
	}

	public void deletePasteById(String pasteid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "update paste set isdelete = 1 where id = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, pasteid);
		query.executeUpdate();
		
	}

	public Paste getPasteById(String id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Paste paste = session.get(Paste.class, id);
		return paste;
	}

	public Integer getDeletePasteCount(String isDelete) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from paste where isdelete = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, isDelete);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List<Paste> getDeletePaste(String isDelete, PageBean pageBean) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from paste where isdelete = ? limit ?,?";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(Paste.class);
		query.setParameter(1, isDelete);
		query.setParameter(2, pageBean.getStart());
		query.setParameter(3, pageBean.getPageSize());
		List<Paste> list = query.list();
		return list;
	}

	public void resPasteById(String pasteid) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql="update paste set isdelete = 0 where id = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, pasteid);
		query.executeUpdate();
		
	}

	public Integer getAllPasteBySolve(int solve) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select count(*) from paste where solve = ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, solve);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public List<Paste> findAllPaste() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select * from paste";
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(Paste.class);
		List<Paste> list = query.list();
		return list;
	}

	public Integer finPasteByStartAndEnd(String startDay, String endDay) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql ="select count(*) from paste where createtime > ? and createtime < ?";
		NativeQuery query = session.createSQLQuery(sql);
		query.setParameter(1, startDay);
		query.setParameter(2, endDay);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

}
