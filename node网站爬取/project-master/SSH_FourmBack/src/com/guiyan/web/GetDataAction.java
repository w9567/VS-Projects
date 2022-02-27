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
	
	//获取一周的详细数据（user，paste，answer）
	public String getAWeekDetailData() throws Exception {
		
		//判断今天是周几
		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		SimpleDateFormat format = new SimpleDateFormat("E");
		String week = format.format(date);
		int day = 0;
		switch(week)
		{
		case "星期一":
			day = 0;
			break;
		case "星期二":
			day = 1;
			break;
		case "星期三":
			day = 2;
			break;
		case "星期四":
			day = 3;
			break;
		case "星期五":
			day = 4;
			break;
		case "星期六":
			day = 5;
			break;
		case "星期日":
			day = 6;
			break;
		}
		
		//获取一周的user数量
		List<Integer> userAWeekDayList = userService.getAWeekDayData(currentTimeMillis, day);
		ActionContext.getContext().put("userAWeekDayList", userAWeekDayList);
		//获取一周的paste数量
		List<Integer> pasteAWeekDayList = pasteService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("pasteAWeekDayList", pasteAWeekDayList);
		//获取一周的answer数量
		List<Integer> answerAWeekDayList = answerService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("answerAWeekDayList", answerAWeekDayList);
		
		return "allCountAWeekDetailMap";
	}
	
	
	//获取当前一周的数据
	public String getAWeekUserData() throws Exception {
		
		//判断今天是周几
		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		SimpleDateFormat formate = new SimpleDateFormat("E");
		String week = formate.format(date);
		int day = 0;
		switch (week) {
		case "星期一":
			day = 0;
			break;
		case "星期二":
			day = 1;
			break;
		case "星期三":
			day = 2;
			break;
		case "星期四":
			day = 3;
			break;
		case "星期五":
			day = 4;
			break;
		case "星期六":
			day = 5;
			break;
		case "星期日":
			day = 6;
			break;
		default:
			break;
		}
		//使用当前的毫秒值（long）来去代替日期（string）
		List<Integer> aWeekDayList = userService.getAWeekDayData(currentTimeMillis,day);
		ActionContext.getContext().put("aWeekDayList", aWeekDayList);
		
		return "allCountAWeekUserMap";
	}
	
	//得到详细雷达图的数据
	public String getDetailRadarData() throws Exception {
		
		List<Paste> pasteList = pasteService.findAllPaste();
		ActionContext.getContext().put("pasteList", pasteList);
		return "allCountDetailRadarMap";
	}
	
	
	//得到雷达图的数据
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
	//得到详细饼状图的数据
	public String getDetailAllData() throws Exception {
		//user数量     1
		Integer userCount = userService.getAllUser();
		ActionContext.getContext().put("userCount", userCount);
		
		//激活     2
		Integer activeUserCount = userService.getAllUserByState(1);
		ActionContext.getContext().put("activeUserCount", activeUserCount);
		
		//未激活   3
		Integer normalUserCount = userService.getAllUserByState(0);
		ActionContext.getContext().put("normalUserCount", normalUserCount);
		
		//paste数量    4
		Integer pasteCount = pasteService.getAllPaste();
		ActionContext.getContext().put("pasteCount", pasteCount);
		
		//完结	5
		Integer overPasteCount = pasteService.getAllPasteBySolve(1);
		ActionContext.getContext().put("overPasteCount", overPasteCount);
		
		//未完结	6
		Integer normalPasteCount = pasteService.getAllPasteBySolve(0);
		ActionContext.getContext().put("normalPasteCount", normalPasteCount);
		
		//点赞数量	7
		Integer praiseCount = praiseService.getAllPraise();
		ActionContext.getContext().put("praiseCount", praiseCount);
		
		//回复数量	8
		Integer answerCount = answerService.getAllAnswer();
		ActionContext.getContext().put("answerCount", answerCount);
		
		return "allCountDetailMap";
	}
	
	
	//得到所有数据
	public String getAllData() throws Exception {
		//获取四个数据
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
		//获取时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		computerInfo.setTime(format.format(new Date()));
		//获取ip地址
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		computerInfo.setIp(ip);
		//操作系统
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		computerInfo.setOs(osName);
		//计算机名称
		Map<String, String> map = System.getenv();
		String computerName = map.get("COMPUTERNAME");
		computerInfo.setComputerName(computerName);
		
		Sigar sigar = new Sigar();
		Mem mem = sigar.getMem();
		//内存
		long total = mem.getTotal();
		//内存使用量
		long used = mem.getUsed();
		//内存剩余量
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
		
		ActionContext.getContext().put("computerInfo", computerInfo);//通过使用actionContext进行转发
		
		
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
