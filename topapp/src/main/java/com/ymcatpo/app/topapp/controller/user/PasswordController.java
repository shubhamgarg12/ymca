package com.ymcatpo.app.topapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.Password;
import com.ymcatpo.app.topapp.service.user.UserService;

@RestController
@RequestMapping(value = "/ymca/api")
@CrossOrigin
public class PasswordController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/change/password/tpo", method = RequestMethod.POST)
	public BasicResponse changePasswordTPO(@RequestBody Password user) throws MailException, InterruptedException {
		
		BasicResponse response = userService.changePasswordForTpo(user);
		return response;
		
	}
	
	@RequestMapping(value = "/reset/password/student/{username}", method = RequestMethod.POST)
	public BasicResponse resetPasswordStudent(@PathVariable String username) {
		
		BasicResponse response = userService.resetPasswordStudent(username);
		return response;
		
	}

	
}
