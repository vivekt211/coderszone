package com.coderszone.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.model.User;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserNotRegisteredException;
import com.coderszone.common.mail.MailService;

 
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserCustomService{
 
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailService mailService;
	
    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
    public User loadUserById(final String username) throws UserNotRegisteredException {
        return userDao.loadUserById(username);
    }
    
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
    public void changePassword(String userId,String cpassword, String password) throws DataBaseAccessException, UserNotRegisteredException, MailServiceException {
    	try{
    		User user= userDao.loadUserByIdPass(userId,cpassword);
    		userDao.updateUserPassword(user.getUsername(), password);
    		mailService.sendPasswordChangeAlert(user.getUsername());
    	}catch (EmptyResultDataAccessException e) {
			throw new UserNotRegisteredException("Your password is not correct");
		}catch(DataAccessException ex){
			throw new DataBaseAccessException("Sorry There is some issue with data access");
		}
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
    
}
