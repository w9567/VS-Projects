package com.guiyan.service;

import com.guiyan.dao.PraiseDao;

public class PraiseService {
	private PraiseDao praiseDao;

	public Integer getAllPraise() {
		
		return praiseDao.getAllPraise();
	}

	public PraiseDao getPraiseDao() {
		return praiseDao;
	}

	public void setPraiseDao(PraiseDao praiseDao) {
		this.praiseDao = praiseDao;
	}
	

}
