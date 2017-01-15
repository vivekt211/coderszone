package com.coderszone.authentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.model.User;

 
@Service
public class UserService implements UserDetailsService {
 
   
    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private UserDao userDao;
 
    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
    
  
}
