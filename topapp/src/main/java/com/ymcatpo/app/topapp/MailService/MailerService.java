package com.ymcatpo.app.topapp.MailService;


import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import java.io.ByteArrayInputStream;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MailerService {
	@Autowired 
	private JavaMailSender javaMailSender;
	
	public static String generatSub(int no) {
		String subject="";
		switch (no) {
		case 0:
			subject = "Welcome to P.A.Y. – Registration Successful.";
		break;
		case 1:
			subject= "P.A.Y. – Password Reset Request Email.";
		break;
		case 2:
			subject= "P.A.Y. – Password Changed.";
		break;
		case 3:
			subject= "Congrats to become a TPO member of P.A.Y.";
		break;
		case 4:
			subject= "APPLICATION CONFIRMATION";
		break;
		}
				
		return subject;
	}
	String generatSub(String companyName, int comapnyId) {
		StringBuffer st = new StringBuffer("P.A.Y. – Thanks for applying to ");
		st.append(companyName);
		st.append("( "+Integer.toString(comapnyId)+" ).");
		return st.toString();
	}
	
	static String getBody() {
		return "Dear User,\r\n \r\n You have successfully changed your password.\r\n \r\n Best Regards\r\n P.A.Y. (Placement Application of J.C .Bose UST YMCA)	";
	}
	
	static String getBody(String comapnyName, int no) {
		if(no==1)
		return "Dear Student,\r\n \r\n You have requested a password reset on P.A.Y. Use below temporary password to login:\r\n \r\n Password: "+comapnyName +" \r\n \r\n PS: Please update your password ASAP.\r\n \r\n Best Regards\r\n P.A.Y. (Placement Application of J.C .Bose UST YMCA)";
		
		
		return "Dear Student,\r\n \r\n Your application for the "+ comapnyName+" job was submitted successfully.\r\n \r\n ALL THE BEST!!!!! \r\n \r\n Regards\r\n P.A.Y. (Placement Application of J.C .Bose UST YMCA)";
	}
	
	static String getBody(String email, String userName, int no) {
		if(no==0) 
		return "Dear Student, \r\n\r\n Your registration is successful on P.A.Y. (Placement Application of J.C .Bose UST YMCA).\r\n This email contains yours details:\r\n	Email Address: "+email+"\r\n" + 
				"	Username: " +userName+"\r\n\r\n Best Regards\r\n P.A.Y. (Placement Application of J.C .Bose UST YMCA)";
		return "Dear Member, \r\n \r\n Congratulation!!!!!!\r\n\r\n You successful resisted as TPO member of P.A.Y. (Placement Application of J.C .Bose UST YMCA).\r\n This email contains yours details:\r\n	Email Address: " + 
			email+ "\r\n 	Username :" +userName+"\r\n \r\n Best Regards\r\n P.A.Y. (Placement Application of J.C .Bose UST YMCA)";
		
	}
	

	@Async
	public void sendNotificaitoin(String email, int subjectId, String data) throws MailException, InterruptedException {
      System.out.println("Sending email...");
      SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("payxyztpo@gmail.com");
		mail.setSubject(MailerService.generatSub(subjectId));
		
		switch (subjectId) {
		case 0:
			mail.setText(MailerService.getBody(email, data, subjectId));
		break;
		case 1:
			mail.setText(MailerService.getBody(data,subjectId));
		break;
		case 2:
			mail.setText(MailerService.getBody());
		break;
		case 3:
			mail.setText(MailerService.getBody(email, data, subjectId));
		break;
		case 4:
			mail.setText(MailerService.getBody(data,subjectId));
		break;
		}
	
		
		javaMailSender.send(mail);
		System.out.println("Email Sent!");
	}
	
	@Async
	public void sendNotificaitoin(String[] email,String sub, String body, int no)throws MailException, InterruptedException {
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
	
	@Async
	public void sendattachment( String fileName,ByteArrayInputStream in,String email) throws IOException, MessagingException {
		
		 MimeMessage message = javaMailSender.createMimeMessage();
		 MimeMessageHelper helper = null;
		 try {	
	            helper = new MimeMessageHelper(message, true);
	            helper.setFrom("payxyztpo@gmail.com");
	            helper.setTo(email);
	            helper.setSubject("Exported File of"+ fileName);
	            
	            

	            helper.addAttachment(fileName+".xlsx", new ByteArrayResource(IOUtils.toByteArray(in)));
	            helper.setText("", true);
	            System.out.println("mail send");
	            javaMailSender.send(message);
	            System.out.println("done");
		 } catch (MessagingException e ) {
	            throw e;
	        }
	}        
		
		/*
		 * JavaMailSenderImpl sender = new JavaMailSenderImpl();
		 * sender.setHost("smtp.gmail.com"); sender.setPort(587); Properties props = new
		 * Properties(); props.put("mail.smtp.ssl.enable", "true"); MimeMessage message
		 * = sender.createMimeMessage(); MimeMessageHelper helper = new
		 * MimeMessageHelper(message, true, CharEncoding.UTF_8); try {
		 * helper.setFrom("payxyztpo@gmail.com"); helper.setTo(email);
		 * helper.addAttachment(fileName, new
		 * ByteArrayResource(IOUtils.toByteArray(in)));
		 * helper.setText("Thank you for ordering!"); sender.send(message); } catch
		 * (MessagingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	
	
}