package com.ymcatpo.app.topapp.service.user;

import org.springframework.mail.MailException;

import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.AuthenticateResponse;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.Password;

public interface UserService {
	
	AuthenticateResponse login(User user);
	BasicResponse registerUser(User user) throws MailException, InterruptedException;
	BasicResponse registerTPO(User user) throws MailException, InterruptedException;
	BasicResponse changePasswordForTpo(Password pass) throws MailException, InterruptedException;
	BasicResponse resetPasswordStudent(String email);
	User getTpoById(String username);
	BasicResponse updateTpo(User user,String username);

}
