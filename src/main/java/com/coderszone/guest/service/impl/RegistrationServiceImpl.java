package com.coderszone.guest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserIdAlreadyExistException;
import com.coderszone.common.key.KeyGenService;
import com.coderszone.common.mail.MailService;
import com.coderszone.guest.dao.RegistrationDao;
import com.coderszone.guest.model.RegistrationModel;
import com.coderszone.guest.model.User;
import com.coderszone.guest.service.RegistrationService;


@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private KeyGenService keyGenService;
	@Autowired
	private MailService MailService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public RegistrationModel registerUser(RegistrationModel registrationModel) throws DataBaseAccessException,UserIdAlreadyExistException, MailServiceException {
				try {
					 User user=registrationDao.getUserByUserId(registrationModel.getEmail());
					 if(user!=null){
						 throw new UserIdAlreadyExistException("Sorry ! It seems you have already registered with this Id. Please choose another Id or click forget password link");
					 }else{
						 register(registrationModel);
					 }
				}catch (EmptyResultDataAccessException ex) {
					register(registrationModel);
				} catch (DataAccessException ex) {
					ex.printStackTrace();
					throw new DataBaseAccessException("Sorry DataBase access has some problem");
				}
		return registrationModel;
	}
	private void register(RegistrationModel registrationModel) throws DataBaseAccessException, MailServiceException{
		try {
			String s=keyGenService.generateNewKeys();
			int id = registrationDao.registerUser(registrationModel,s);
			registrationDao.insertUserRole(registrationModel.getEmail());
			registrationModel.setId(id);
			MailService.sendVerificationCode(registrationModel.getEmail(),s);
			
		} catch (DataAccessException exx) {
			exx.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public boolean verifyUser(int id, String code) throws DataBaseAccessException {
		try {
			 User user=registrationDao.getUserByid(id);
			 if(user.getVerified()==0 && user.getVerificationCode().equals(code)){
				 registrationDao.verifyUserByid(id);
				 return true; 
			 }
		}catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
		return false;
	}

	public MailService getMailService() {
		return MailService;
	}

	public void setMailService(MailService mailService) {
		MailService = mailService;
	}
	

}
