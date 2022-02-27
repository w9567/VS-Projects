package com.jiguiyan.service;

import java.util.List;

import com.jiguiyan.dao.AnswerDao;
import com.jiguiyan.dao.PasteDao;
import com.jiguiyan.dao.UserDao;
import com.jiguiyan.domain.Answer;
import com.jiguiyan.domain.Paste;
import com.jiguiyan.domain.User;
import com.jiguiyan.utils.PageBean;

import antlr.debug.ParseTreeDebugParser;

public class PasteService {

	private PasteDao pasteDao;
	private AnswerDao answerDao;
	private UserDao userDao;
	
	public void solvePasteByIdAndAnswerid(String pasteid, String answerid) {
		//���ظ����˼ӽ��
		Paste paste = pasteDao.findPasteByIdReturnPaste(pasteid);
		//ͨ��answerid����userid
		Answer answer = answerDao.findAnswerById(answerid);
		//ͨ��userid��user����
		//User user = userDao.finduserByIdReturnUser(answer.getUser().getId());
		//���answer�µ�user���󣨳־û����Ͷ���
		User user = answer.getUser();
		//��Ϊ�ǳ־û����Ͷ��󣬼���ֱ���޸����ԣ�ͬ�������ݿ�
		user.setCoin(user.getCoin()+paste.getOffer());
		//������⣨���ɻظ���
		pasteDao.solvePasteByIdAndAnswerid(pasteid,answerid);
	}

	
	public Paste findPasteByIdReturnPaste(String pasteid) {
		
		//pasteDao.addPasteGlanceover(pasteid);
		//update paste set glanceover = glanceover+1 where id = ?;
		Paste paste = pasteDao.findPasteByIdReturnPaste(pasteid);
		paste.setGlanceover(paste.getGlanceover()+1);
		return paste;
	}
	
	
	public PageBean getAnsnumPageBean(Integer currentPage) {
		
		//������е�������Ŀ
		Integer totalCount = pasteDao.findAllPasteNum();
		//����PageBean
		PageBean ansnumPageBean = new PageBean(currentPage, totalCount, 8);
		//�õ�List
		List<Paste> list = pasteDao.getAnsnumPageList();
		//��װList
		ansnumPageBean.setList(list);
		
		return ansnumPageBean;
	}
	
	
	public PageBean getGlanceoverPageBean(Integer currentPage) {
		
		Integer totalCount = pasteDao.findAllPasteNum();
		PageBean glanceoverPageBean = new PageBean(currentPage, totalCount, 8);
		List<Paste> list = pasteDao.getGlanceoverPageList();
		glanceoverPageBean.setList(list);
		
		return glanceoverPageBean;
	}

	
	public PageBean getPastePageBean(Integer currentPage) {
		
		Integer totalCount = pasteDao.findAllPasteNum();
		PageBean pageBean = new PageBean(currentPage, totalCount, 3);
		List<Paste> list = pasteDao.getPastePageList(pageBean.getStart(),pageBean.getPageSize());
		pageBean.setList(list);
		
		return pageBean;
	}

	
	
	public void addPaste(Paste paste) {
		
		pasteDao.addPaste(paste);
		
	}
	public List<Paste> findAllPaste() {
		return pasteDao.findAllPaste();
	}
	
	
	
	
	public PasteDao getPasteDao() {
		return pasteDao;
	}

	public void setPasteDao(PasteDao pasteDao) {
		this.pasteDao = pasteDao;
	}


	public AnswerDao getAnswerDao() {
		return answerDao;
	}


	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}










	

}
