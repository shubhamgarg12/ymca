package com.ymcatpo.app.topapp.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServicee {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String to, String body ,String subject) {
		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
			simpleMailMessage.setFrom("payxyztpo@gmail.com");
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);
			javaMailSender.send(simpleMailMessage);
			System.out.println("mail send");
		
	}
	
	
}
