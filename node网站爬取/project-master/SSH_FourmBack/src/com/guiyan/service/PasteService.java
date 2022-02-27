package com.guiyan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guiyan.dao.PasteDao;
import com.guiyan.domain.Paste;
import com.guiyan.utils.PageBean;

public class PasteService {

	private PasteDao pasteDao;

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
			Integer count = pasteDao.finPasteByStartAndEnd(startDay,endDay);
			list.add(count);
		}
		return list;
	}

	
	
	public List<Paste> findAllPaste() {
		return pasteDao.findAllPaste();

	}

	public Integer getAllPasteBySolve(int solve) {
		return pasteDao.getAllPasteBySolve(solve);

	}
	
	public void resPasteById(String pasteid) {
		pasteDao.resPasteById(pasteid);
		
	}

	
	
	public void editPaste(Paste paste) {
		// 得到持久化对象Paste
		Paste edit = pasteDao.getPasteById(paste.getId());
		// 修改
		edit.setTitle(paste.getTitle());
		edit.setContent(paste.getContent());
	}

	public void deletePasteById(String pasteid) {
		pasteDao.deletePasteById(pasteid);

	}

	public PageBean getPageBeanByCondition(String end, String start, Integer currentPage, String title, String isDelete,
			Integer solve) {
		// 得到数目
		Integer totalCount = pasteDao.getAllPasteByCondition(start, end, title, isDelete, solve);

		PageBean pageBean = new PageBean(currentPage, 6, totalCount);
		// 得到list
		List<Paste> list = pasteDao.getPageBeanListByCondition(start, end, title, isDelete, solve, pageBean);
		pageBean.setList(list);

		return pageBean;
	}

	public PageBean getSolvePageBean(Integer solve) {

		// 得到数目
		Integer totalCount = pasteDao.getSolvePasteCount(solve);
		PageBean pageBean = new PageBean(null, 6, totalCount);
		// 得到list
		List<Paste> list = pasteDao.getSolvePaste(solve, pageBean);
		pageBean.setList(list);

		return pageBean;
	}

	public PageBean getDeletePageBean(String isDelete) {
		// 得到数目
		Integer totalCount = pasteDao.getDeletePasteCount(isDelete);
		PageBean pageBean = new PageBean(null, 6, totalCount);
		// 得到list
		List<Paste> list = pasteDao.getDeletePaste(isDelete, pageBean);
		pageBean.setList(list);

		return pageBean;
	}

	public Integer getAllPaste() {
		// TODO Auto-generated method stub
		return pasteDao.getAllPaste();
	}

	public PasteDao getPasteDao() {
		return pasteDao;
	}

	public void setPasteDao(PasteDao pasteDao) {
		this.pasteDao = pasteDao;
	}







}
