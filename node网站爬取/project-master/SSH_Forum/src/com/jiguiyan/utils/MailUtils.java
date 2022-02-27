package com.jiguiyan.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	//发邮箱，给谁发email，以及email的内容和标题
	//163发给阿里云的邮箱
	public static void sendMail(String email,String title, String emailMsg)
			throws AddressException, MessagingException {
		

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");

		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//改
				return new PasswordAuthentication("13626090301", "m13626090301");
			}
		};

		Session session = Session.getInstance(props, auth);

	
		Message message = new MimeMessage(session);
		//改
		message.setFrom(new InternetAddress("13626090301@163.com")); 

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//设置标题
		message.setSubject(title);
		// message.setText("杩欐槸涓?灏佹縺娲婚偖浠讹紝璇?<a href='#'>鐐瑰嚮</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		

		Transport.send(message);
	}
}
