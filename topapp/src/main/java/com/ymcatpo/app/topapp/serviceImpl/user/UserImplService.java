package com.ymcatpo.app.topapp.serviceImpl.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthenticateResponse response = new AuthenticateResponse();
		response.setToken(token);
		response.setUsername(userDetails.getUsername());
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
		}else {
			throw new ApiException("User already exist", HttpStatus.NOT_ACCEPTABLE);
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
		}else {
			throw new ApiException("User already exist", HttpStatus.NOT_ACCEPTABLE);
		}

		return response;
	}

	@Override
	public BasicResponse changePasswordForTpo(Password pass) {
		
		User user = userRepo.getUserByUsername(pass.getUsername());
		BasicResponse response = new BasicResponse();
		if(bCryptPasswordEncoder.matches(pass.getPrevPassword(), user.getPassword())) {
			bCryptPasswordEncoder.encode(pass.getNewPassword());
			user.setPassword(bCryptPasswordEncoder.encode(pass.getNewPassword()));			
			userRepo.save(user);
			response.setMessage("Success");
			response.setStatus(HttpStatus.CREATED.toString());
		}else {
			throw new ApiException("Invalid Password",HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
