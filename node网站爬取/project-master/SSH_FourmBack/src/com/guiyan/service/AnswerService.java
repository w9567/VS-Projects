package com.guiyan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guiyan.dao.AnswerDao;

public class AnswerService {

	private AnswerDao answerDao;
	
	public List<Integer> getAWeekDayData(long currentTimeMillis, int day) {
		//获取一天的毫秒值
		long dayMillis = 24*60*60*1000;
		//获取这周第一天的毫秒值
		long firstOfWeekMills = currentTimeMillis - day*dayMillis;
		List<Integer> list = new ArrayList<Integer>();
		//for
		for(long i = firstOfWeekMills;i<firstOfWeekMills+7*dayMillis;i+=dayMillis)
		{
			Date sDate = new Date(i);
			Date eDate = new Date(i+dayMillis);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String startDay = format.format(sDate);
			String endDay = format.format(eDate);
			Integer count = answerDao.finAnswerByStartAndEnd(startDay,endDay);
			list.add(count);
		}
		return list;
	}
	
	public Integer getAllAnswer() {
		// TODO Auto-generated method stub
		return answerDao.getAllAnswer();
	}

	public AnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}



}
