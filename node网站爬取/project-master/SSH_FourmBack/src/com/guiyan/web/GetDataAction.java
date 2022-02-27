package com.guiyan.web;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.guiyan.domain.Paste;
import com.guiyan.service.AnswerService;
import com.guiyan.service.PasteService;
import com.guiyan.service.PraiseService;
import com.guiyan.service.UserService;
import com.guiyan.utils.ComputerInfo;

public class GetDataAction extends ActionSupport {
	
	private UserService userService;
	private PasteService pasteService;
	
	private AnswerService answerService;
	private PraiseService praiseService;
	
	//��ȡһ�ܵ���ϸ���ݣ�user��paste��answer��
	public String getAWeekDetailData() throws Exception {
		
		//�жϽ������ܼ�
		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		SimpleDateFormat format = new SimpleDateFormat("E");
		String week = format.format(date);
		int day = 0;
		switch(week)
		{
		case "����һ":
			day = 0;
			break;
		case "���ڶ�":
			day = 1;
			break;
		case "������":
			day = 2;
			break;
		case "������":
			day = 3;
			break;
		case "������":
			day = 4;
			break;
		case "������":
			day = 5;
			break;
		case "������":
			day = 6;
			break;
		}
		
		//��ȡһ�ܵ�user����
		List<Integer> userAWeekDayList = userService.getAWeekDayData(currentTimeMillis, day);
		ActionContext.getContext().put("userAWeekDayList", userAWeekDayList);
		//��ȡһ�ܵ�paste����
		List<Integer> pasteAWeekDayList = pasteService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("pasteAWeekDayList", pasteAWeekDayList);
		//��ȡһ�ܵ�answer����
		List<Integer> answerAWeekDayList = answerService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("answerAWeekDayList", answerAWeekDayList);
		
		return "allCountAWeekDetailMap";
	}
	
	
	//��ȡ��ǰһ�ܵ�����
	public String getAWeekUserData() throws Exception {
		
		//�жϽ������ܼ�
		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		SimpleDateFormat formate = new SimpleDateFormat("E");
		String week = formate.format(date);
		int day = 0;
		switch (week) {
		case "����һ":
			day = 0;
			break;
		case "���ڶ�":
			day = 1;
			break;
		case "������":
			day = 2;
			break;
		case "������":
			day = 3;
			break;
		case "������":
			day = 4;
			break;
		case "������":
			day = 5;
			break;
		case "������":
			day = 6;
			break;
		default:
			break;
		}
		//ʹ�õ�ǰ�ĺ���ֵ��long����ȥ�������ڣ�string��
		List<Integer> aWeekDayList = userService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("aWeekDayList", aWeekDayList);
		
		return "allCountAWeekUserMap";
	}
	
	//�õ���ϸ�״�ͼ������
	public String getDetailRadarData() throws Exception {
		
		List<Paste> pasteList = pasteService.findAllPaste();
		ActionContext.getContext().put("pasteList", pasteList);
		return "allCountDetailRadarMap";
	}
	
	
	//�õ��״�ͼ������
	public String getRadarData() throws Exception {
		Integer userCount = userService.getAllUser();
		ActionContext.getContext().put("userCount", userCount);
		
		Integer pasteCount = pasteService.getAllPaste();
		ActionContext.getContext().put("pasteCount", pasteCount);
		
		Integer answerCount = answerService.getAllAnswer();
		ActionContext.getContext().put("answerCount", answerCount);
		
		Integer praiseCount = praiseService.getAllPraise();
		ActionContext.getContext().put("praiseCount", praiseCount);
		
		return "allCountRadarMap";
		
	}
	//�õ���ϸ��״ͼ������
	public String getDetailAllData() throws Exception {
		//user����     1
		Integer userCount = userService.getAllUser();
		ActionContext.getContext().put("userCount", userCount);
		
		//����     2
		Integer activeUserCount = userService.getAllUserByState(1);
		ActionContext.getContext().put("activeUserCount", activeUserCount);
		
		//δ����   3
		Integer normalUserCount = userService.getAllUserByState(0);
		ActionContext.getContext().put("normalUserCount", normalUserCount);
		
		//paste����    4
		Integer pasteCount = pasteService.getAllPaste();
		ActionContext.getContext().put("pasteCount", pasteCount);
		
		//���	5
		Integer overPasteCount = pasteService.getAllPasteBySolve(1);
		ActionContext.getContext().put("overPasteCount", overPasteCount);
		
		//δ���	6
		Integer normalPasteCount = pasteService.getAllPasteBySolve(0);
		ActionContext.getContext().put("normalPasteCount", normalPasteCount);
		
		//��������	7
		Integer praiseCount = praiseService.getAllPraise();
		ActionContext.getContext().put("praiseCount", praiseCount);
		
		//�ظ�����	8
		Integer answerCount = answerService.getAllAnswer();
		ActionContext.getContext().put("answerCount", answerCount);
		
		return "allCountDetailMap";
	}
	
	
	//�õ���������
	public String getAllData() throws Exception {
		//��ȡ�ĸ�����
		Integer userCount = userService.getAllUser();
		ActionContext.getContext().put("userCount", userCount);
		
		Integer pasteCount = pasteService.getAllPaste();
		ActionContext.getContext().put("pasteCount", pasteCount);
		
		Integer answerCount = answerService.getAllAnswer();
		ActionContext.getContext().put("answerCount", answerCount);
		
		Integer praiseCount = praiseService.getAllPraise();
		ActionContext.getContext().put("praiseCount", praiseCount);
		return "allCountMap";
	}
	
	public String getIndex() throws Exception {
		
		return "index";
	}
	
	
	public String getData() throws Exception {
		
		ComputerInfo computerInfo = new ComputerInfo();
		//��ȡʱ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		computerInfo.setTime(format.format(new Date()));
		//��ȡip��ַ
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		computerInfo.setIp(ip);
		//����ϵͳ
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		computerInfo.setOs(osName);
		//���������
		Map<String, String> map = System.getenv();
		String computerName = map.get("COMPUTERNAME");
		computerInfo.setComputerName(computerName);
		
		Sigar sigar = new Sigar();
		Mem mem = sigar.getMem();
		//�ڴ�
		long total = mem.getTotal();
		//�ڴ�ʹ����
		long used = mem.getUsed();
		//�ڴ�ʣ����
		long free = mem.getFree();
		
		computerInfo.setMemTotal(total/1024/1024/1024 + "G");
		computerInfo.setMemUse(used/1024/1024/1024 + "G");
		computerInfo.setMemFree(free/1024/1024/1024 + "G");
		
//		System.err.println(computerInfo.getTime());
//		System.err.println(computerInfo.getIp());
//		System.err.println(computerInfo.getOs());
//		System.err.println(computerInfo.getComputerName());
//		
//		System.err.println(1.0 * total/1024/1024/1024);
//		System.err.println(1.0 * used/1024/1024/1024);
//		System.err.println(1.0 * free/1024/1024/1024);
		
		ActionContext.getContext().put("computerInfo", computerInfo);//ͨ��ʹ��actionContext����ת��
		
		
		Integer userCount = userService.getAllUser();
		ActionContext.getContext().put("userCount", userCount);
		
		Integer pasteCount = pasteService.getAllPaste();
		ActionContext.getContext().put("pasteCount", pasteCount);
		
		Integer answerCount = answerService.getAllAnswer();
		ActionContext.getContext().put("answerCount", answerCount);
		
		Integer praiseCount = praiseService.getAllPraise();
		ActionContext.getContext().put("praiseCount", praiseCount);
		
		
		return "welcome";
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public PasteService getPasteService() {
		return pasteService;
	}


	public void setPasteService(PasteService pasteService) {
		this.pasteService = pasteService;
	}


	public AnswerService getAnswerService() {
		return answerService;
	}


	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}


	public PraiseService getPraiseService() {
		return praiseService;
	}


	public void setPraiseService(PraiseService praiseService) {
		this.praiseService = praiseService;
	}

	
	
}
