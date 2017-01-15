package com.coderszone.authentication.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public class GrantedAuthorityMapper implements RowMapper<GrantedAuthority>{

	@Override
	public GrantedAuthority mapRow(ResultSet rs, int rowNum)
			throws SQLException {
			GrantedAuthority auth=new SimpleGrantedAuthority(rs.getString("role_id"));
		 	return auth;
	}

}
