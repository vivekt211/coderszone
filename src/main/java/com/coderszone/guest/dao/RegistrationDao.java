package com.coderszone.guest.dao;

import com.coderszone.guest.model.RegistrationModel;
import com.coderszone.guest.model.User;

public interface RegistrationDao {

	User getUserByUserId(String email);

	int registerUser(RegistrationModel registrationModel, String regKey);

	User getUserByid(int id);

	void verifyUserByid(int id);

	void insertUserRole(String email);

}
