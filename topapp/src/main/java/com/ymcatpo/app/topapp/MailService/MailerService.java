package com.ymcatpo.app.topapp.MailService;


import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailerService {
	@Autowired 
	private JavaMailSender javaMailSender;

	

	@Async
	public void sendNotificaitoin(String email, String subject, String Body) throws MailException, InterruptedException {
      System.out.println("Sending email...");
      SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("payxyztpo@gmail.com");
		mail.setSubject(subject);
		mail.setText(Body);
		javaMailSender.send(mail);
		System.out.println("Email Sent!");
	}
	
	@Async
	public void sendNotificaitoin(String[] email,String sub, String body)throws MailException, InterruptedException {
	      	for(String temp : email) {
			System.out.println("Sending email...");
	      	SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(temp);
			mail.setFrom("payxyztpo@gmail.com");
			mail.setSubject(sub);
			mail.setText(body);
			javaMailSender.send(mail);
			System.out.println("Email Sent!");
		}
	}
	
}