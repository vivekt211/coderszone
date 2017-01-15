package com.coderszone.guest.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coderszone.common.DateUtil;
import com.coderszone.guest.model.User;



public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		User user=new User();
		  user.setId(rs.getInt("id"));
		  user.setEmail(rs.getString("user_name"));
		  user.setPassword(rs.getString("password"));
		  user.setName(rs.getString("name"));
		  user.setLastModBy(rs.getString("mod_by"));
		  user.setLastModDate(DateUtil.timeStampToString(rs.getTimestamp("mod_date")));
		  user.setVerified(rs.getInt("verified"));
		  user.setVerificationCode(rs.getString("verification_key"));
		return user;
	}

}
