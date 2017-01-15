package com.coderszone.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.model.User;

 
@Service
public class UserCustomService{
 
	@Autowired
	private UserDao userDao;
	   
    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
    public User loadUserById(final String username) throws UsernameNotFoundException {
        return userDao.loadUserById(username);
    }
}
