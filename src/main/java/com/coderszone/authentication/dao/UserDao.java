package com.coderszone.authentication.dao;

import com.coderszone.authentication.model.User;


public interface UserDao {

	User loadUserByUsername(String username);

	User loadUserById(String username);

}
