package com.jiguiyan.service;

import java.util.List;

import com.jiguiyan.dao.AnswerDao;
import com.jiguiyan.dao.PasteDao;
import com.jiguiyan.domain.Answer;
import com.jiguiyan.domain.Paste;

public class AnswerService {

	private AnswerDao answerDao;
	private PasteDao pasteDao;

	public void deleteAnswerAgree(String answerid) {
		Answer answer = answerDao.findAnswerById(answerid);
		answer.setAgree(answer.getAgree() - 1);
	}

	public void addAnswerAgree(String answerid) {
		Answer answer = answerDao.findAnswerById(answerid);
		answer.setAgree(answer.getAgree() + 1);
		// �ڶ��ַ�ʽ��sql
		// update answer set agree = agree + 1 where answer id = ?
	}

	// ����answerid����answer����
	public Answer findAnswerByIdReturnAnswer(String answerid) {
		Answer answer = answerDao.findAnswerById(answerid);
		return answer;
	}

	public void deleteAnswerById(String answerid, Paste paste) {

		paste.setAnsnum(paste.getAnsnum() - 1);
		answerDao.deleteAnswerById(answerid);

	}

	public List<Answer> findAllAnswerByPasteid(String pasteid) {

		Paste paste = pasteDao.findPasteByIdReturnPaste(pasteid);
		List<Answer> answerList = null;
		// �ж�paste�е�solve�Ƿ�Ϊ1
		if (paste.getSolve() == 1) {
			// ������Ѵ�
			Answer answer = answerDao.findAnswerById(paste.getAnswerid());
			// �������д�
			answerList = answerDao.findAllAnswerByPasteid(pasteid);
			// �������д��а�������Ѵ����Խ���Ѵ�ɾ��
			answerList.remove(answer);
			// ��ͷԪ���в�����Ѵ�
			answerList.add(0, answer);
		} else {
			answerList = answerDao.findAllAnswerByPasteid(pasteid);
		}

		return answerList;
	}

	public void addAnswer(Answer answer, Paste paste) {

		paste.setAnsnum(paste.getAnsnum() + 1);
		answerDao.addAnswer(answer);

	}

	public AnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	public PasteDao getPasteDao() {
		return pasteDao;
	}

	public void setPasteDao(PasteDao pasteDao) {
		this.pasteDao = pasteDao;
	}

}
