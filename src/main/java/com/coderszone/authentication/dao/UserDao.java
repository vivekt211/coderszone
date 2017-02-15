package com.coderszone.authentication.dao;

import com.coderszone.authentication.model.User;
import com.coderszone.common.exception.UserNotRegisteredException;


public interface UserDao {

	User loadUserByUsername(String username);

	User loadUserById(String username) throws UserNotRegisteredException;

	void updateUserPassword(String id, String password);

	User loadUserByIdPass(String userId, String cpassword);

}
