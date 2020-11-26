package com.ymcatpo.app.topapp.controller.Tpo;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.MailService.MailerService;
import com.ymcatpo.app.topapp.UserDefineException.ApiException;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;

@RestController
@RequestMapping("/ymca/api/password")
public class ResetPassword {
	
	@Autowired
	StudentPersonalDetailsDao studentDao;
	@Autowired
	MailerService mailService;
	@GetMapping("/reset/{studentId}")
	public ResponseEntity<?> resetPassword(@PathVariable String  studentId) throws MailException, InterruptedException{
		try {
			
		StudentPersonalDetails stu = studentDao.getOne(studentId);
		String email = stu.getEmail();
		String password =  RandomStringUtils.random(10, true, true);
		mailService.sendNotificaitoin(email, 1, password);
		return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			throw new ApiException("Unbale to update/send mail", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		
	}

}
