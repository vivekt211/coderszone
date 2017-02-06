package com.coderszone.guest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderszone.common.Constants;
import com.coderszone.common.beans.ResponseModel;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserIdAlreadyExistException;
import com.coderszone.guest.model.RegistrationModel;
import com.coderszone.guest.service.RegistrationService;


@Controller
public class RegistrationController{
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public @ResponseBody ResponseModel<RegistrationModel> register(@ModelAttribute RegistrationModel registrationModel,Model model) {
		ResponseModel<RegistrationModel> rs=new ResponseModel<RegistrationModel>();
		try {
			RegistrationModel regis=registrationService.registerUser(registrationModel);
			rs.setResponseCode(Constants.RESPONSE_OK);
			rs.setMessage("Thank You! You have been registered Please Enter the Code sent to the email for verification");
			rs.setData(regis);
			
		}catch (UserIdAlreadyExistException e) {
			rs.setResponseCode(Constants.RESPONSE_FAILED);
			rs.setMessage(e.getMessage());
			rs.setData(registrationModel);
			
		} catch (DataBaseAccessException e) {
			rs.setResponseCode(Constants.RESPONSE_FAILED);
			rs.setMessage(e.getMessage());
			rs.setData(registrationModel);
		} catch (MailServiceException e) {
			rs.setResponseCode(Constants.RESPONSE_FAILED);
			rs.setMessage(e.getMessage());
			rs.setData(registrationModel);
		} 
		return rs;
	}
	
	@RequestMapping(value="/verifyreg", method=RequestMethod.GET)
	public @ResponseBody ResponseModel<RegistrationModel> verify(@RequestParam int id,@RequestParam String code,Model model) {
		ResponseModel<RegistrationModel> rs=new ResponseModel<RegistrationModel>();
		try {
			boolean isVerified=registrationService.verifyUser(id,code);
			if(isVerified){
				rs.setResponseCode(Constants.RESPONSE_OK);
				rs.setMessage("Thank You! You have been registered Please Login ");
				rs.setData(null);
			}else{
				rs.setResponseCode(Constants.RESPONSE_FAILED);
				rs.setMessage("Sorry ! The code does not seem valid ");
				rs.setData(null);
			}
		}catch (DataBaseAccessException e) {
			rs.setResponseCode(Constants.RESPONSE_FAILED);
			rs.setMessage(e.getMessage());
			rs.setData(null);
		} 
		return rs;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	
}
