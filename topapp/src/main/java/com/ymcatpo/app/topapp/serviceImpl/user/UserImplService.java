package com.ymcatpo.app.topapp.serviceImpl.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.MailService.MailerService;
import com.ymcatpo.app.topapp.config.JwtTokenUtil;
import com.ymcatpo.app.topapp.entity.user.Role;
import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.exception.ApiException;
import com.ymcatpo.app.topapp.model.AuthenticateResponse;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.MyUserDetails;
import com.ymcatpo.app.topapp.model.Password;
import com.ymcatpo.app.topapp.service.user.UserService;
import com.ymcatpo.app.topapp.userDao.RoleRepository;
import com.ymcatpo.app.topapp.userDao.UserRepository;

@Service
public class UserImplService implements UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	MailerService mailService;

	@Override
	public AuthenticateResponse login(User user) {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ApiException("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
		}
		MyUserDetails userDetails = (MyUserDetails) jwtInMemoryUserDetailsService
				.loadUserByUsername(user.getUsername());
		User us = userRepo.getUserByUsername(user.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthenticateResponse response = new AuthenticateResponse();
		response.setToken(token);
		response.setUsername(userDetails.getUsername());
		response.setEmail(us.getEmail());
		userDetails.getAuthorities().forEach(s -> {

			response.setRole(s.toString());
		});
		return response;
	}

	@Override
	public BasicResponse registerUser(User user) {

		User checkUser = userRepo.getUserByUsername(user.getUsername());
		BasicResponse response = new BasicResponse();
		if (checkUser == null) {

			Set<Role> roles = new HashSet<Role>();
			Optional<Role> role = roleRepository.findById(2);
			roles.add(role.get());
			user.setRoles(roles);
			bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User userSaved = userRepo.save(user);
			if (userSaved != null) {

				response.setMessage("Success");
				response.setStatus(HttpStatus.CREATED.toString());
			} else {
				throw new ApiException("Error", HttpStatus.BAD_GATEWAY);
			}
		} else {
			throw new ApiException("User already exist", HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			mailService.sendNotificaitoin(user.getEmail(), 0, user.getUsername());
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return response;

	}

	@Override
	public BasicResponse registerTPO(User user) {

		User checkUser = userRepo.getUserByUsername(user.getUsername());
		BasicResponse response = new BasicResponse();
		if (checkUser == null) {

			Set<Role> roles = new HashSet<Role>();
			Optional<Role> role = roleRepository.findById(1);
			roles.add(role.get());
			user.setRoles(roles);
			bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User userSaved = userRepo.save(user);
			if (userSaved != null) {

				response.setMessage("Success");
				response.setStatus(HttpStatus.CREATED.toString());
			} else {
				throw new ApiException("Error", HttpStatus.BAD_GATEWAY);
			}
		} else {
			throw new ApiException("User already exist", HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			mailService.sendNotificaitoin(user.getEmail(), 3, user.getUsername());
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public BasicResponse changePasswordForTpo(Password pass) {

		User user = userRepo.getUserByUsername(pass.getUsername());
		BasicResponse response = new BasicResponse();
		if (bCryptPasswordEncoder.matches(pass.getPrevPassword(), user.getPassword())) {
			bCryptPasswordEncoder.encode(pass.getNewPassword());
			user.setPassword(bCryptPasswordEncoder.encode(pass.getNewPassword()));
			userRepo.save(user);
			response.setMessage("Success");
			response.setStatus(HttpStatus.CREATED.toString());
			try {
				mailService.sendNotificaitoin(user.getEmail(), 2,"");
			} catch (MailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ApiException("Invalid Password", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public BasicResponse resetPasswordStudent(String username) {
		User user = userRepo.getUserByUsername(username);
		BasicResponse response = new BasicResponse();
		if (user != null) {
			String password = RandomStringUtils.random(10, true, true);
			try {
				
				user.setPassword(bCryptPasswordEncoder.encode(password));
				userRepo.save(user);
				response.setMessage("Success");
				response.setStatus(HttpStatus.CREATED.toString());
				mailService.sendNotificaitoin(user.getEmail(), 1, password);
			} catch (MailException e) {
				throw new ApiException("Mail Error", HttpStatus.BAD_REQUEST);
			} catch (InterruptedException e) {
				throw new ApiException("Error Occured", HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new ApiException("No user found", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	public BasicResponse updateTpo(User user,String username) {
		User checkUser = userRepo.getUserByUsername(username);
		BasicResponse response = new BasicResponse();
		if (checkUser != null) {
			checkUser.setEmail(user.getEmail());
			checkUser.setName(user.getName());
			checkUser.setDepartment(user.getDepartment());
			checkUser.setPhoneNumber(user.getPhoneNumber());
			checkUser.setUsername(username);
			checkUser.setId(checkUser.getId());
			User userSaved = userRepo.save(checkUser);
			if (userSaved != null) {
				response.setMessage("Success");
				response.setStatus(HttpStatus.CREATED.toString());
			} else {
				throw new ApiException("Error", HttpStatus.BAD_GATEWAY);
			}
		}
		return response;

	}
	public User getTpoById(String username) {
		User checkUser = userRepo.getUserByUsername(username);
		BasicResponse response = new BasicResponse();
		if (checkUser != null) {
				response.setMessage("Success");
				response.setStatus(HttpStatus.CREATED.toString());
			} else {
				throw new ApiException("Error", HttpStatus.BAD_GATEWAY);
			}
		return checkUser;

	}

}
