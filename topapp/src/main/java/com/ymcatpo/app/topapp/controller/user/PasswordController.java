package com.ymcatpo.app.topapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.Password;
import com.ymcatpo.app.topapp.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class PasswordController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/change/password/tpo", method = RequestMethod.POST)
	public BasicResponse registerTPO(@RequestBody Password user) {
		
		BasicResponse response = userService.changePasswordForTpo(user);
		return response;
		
	}
}
