package com.ymcatpo.app.topapp.userdetailsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.MailService.MailerService;
import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.MyUserDetails;
import com.ymcatpo.app.topapp.userDao.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 
    @Autowired
    private UserRepository userRepository;
    @Autowired
	private MailerService notificationService;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        try {
			notificationService.sendNotificaitoin(user.getEmail(), 0, user.getUsername()+", your data is updated");
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new MyUserDetails(user);
    }
 
}