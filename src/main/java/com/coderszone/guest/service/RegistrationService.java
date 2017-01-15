package com.coderszone.guest.service;

import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.UserIdAlreadyExistException;
import com.coderszone.guest.model.RegistrationModel;

public interface RegistrationService {

	public RegistrationModel registerUser(RegistrationModel registrationModel) throws DataBaseAccessException, UserIdAlreadyExistException;

	public boolean verifyUser(int id, String code) throws DataBaseAccessException;
}
