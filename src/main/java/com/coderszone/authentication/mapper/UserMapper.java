package com.coderszone.authentication.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coderszone.authentication.model.User;


public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		User user=new User();
		  user.setUsername(rs.getString("user_name"));
		  user.setPassword(rs.getString("password"));
		  user.setName(rs.getString("name"));
		return user;
	}

}
