package com.coderszone.authentication.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coderszone.authentication.model.User;


public class UserDetailsMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		User user=new User();
		  user.setId(rs.getInt("id"));
		  user.setUsername(rs.getString("user_name"));
		  user.setVerificationKey(rs.getString("verification_key"));
		  user.setIsVerified(rs.getInt("verified"));
		  user.setName(rs.getString("name"));
		return user;
	}

}
