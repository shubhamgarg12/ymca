package com.ymcatpo.app.topapp.service.user;

import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.AuthenticateResponse;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.model.Password;

public interface UserService {
	
	AuthenticateResponse login(User user);
	BasicResponse registerUser(User user);
	BasicResponse registerTPO(User user);
	BasicResponse changePasswordForTpo(Password pass);
	BasicResponse resetPasswordStudent(String email);
	User getTpoById(String username);
	BasicResponse updateTpo(User user,String username);

}
