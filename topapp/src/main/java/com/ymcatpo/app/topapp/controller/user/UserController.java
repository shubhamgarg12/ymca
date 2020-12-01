  package com.ymcatpo.app.topapp.controller.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ymcatpo.app.topapp.config.JwtTokenUtil;
import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.AuthenticateResponse;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.MyUserDetails;
import com.ymcatpo.app.topapp.service.user.UserService;


@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest)
			throws Exception {
			
		AuthenticateResponse response = userService.login(authenticationRequest);
		
		return ResponseEntity.ok(response);
	}

	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public BasicResponse registerStudent(@RequestBody User user) {
		
		BasicResponse response = userService.registerUser(user);
		return response;
		
	}
	@RequestMapping(value = "/register/tpo", method = RequestMethod.POST)
	public BasicResponse registerTPO(@RequestBody User user) {
		
		BasicResponse response = userService.registerTPO(user);
		return response;
		
	}
}
