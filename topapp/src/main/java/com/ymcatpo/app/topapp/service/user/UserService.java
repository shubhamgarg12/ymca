package com.ymcatpo.app.topapp.service.user;

import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.AuthenticateResponse;
import com.ymcatpo.app.topapp.model.BasicResponse;

public interface UserService {
	
	AuthenticateResponse login(User user);
	BasicResponse registerUser(User user);
	BasicResponse registerTPO(User user);

}
