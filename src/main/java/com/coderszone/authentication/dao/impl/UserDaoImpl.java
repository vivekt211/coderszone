package com.coderszone.authentication.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.mapper.GrantedAuthorityMapper;
import com.coderszone.authentication.mapper.UserDetailsMapper;
import com.coderszone.authentication.mapper.UserMapper;
import com.coderszone.authentication.model.User;

public class UserDaoImpl implements UserDao{

	private DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}
	private JdbcTemplate jdbcTemplate;
	
	
    public void setDataSource(DataSource dataSource) {
    	this.dataSource= dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	private String queryForUserByUserId;
	private String queryForRolesByUserId;
	

	@Override
	public User loadUserByUsername(String username) {
		User user=null;
		try{
		user= jdbcTemplate.queryForObject(queryForUserByUserId, new Object[]{username},new UserMapper());
		List<GrantedAuthority> roleList=jdbcTemplate.query(queryForRolesByUserId,new Object[]{username},new GrantedAuthorityMapper());
		user.setAuthorities(roleList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
		
	}


	public String getQueryForUserByUserId() {
		return queryForUserByUserId;
	}


	public void setQueryForUserByUserId(String queryForUserByUserId) {
		this.queryForUserByUserId = queryForUserByUserId;
	}


	public String getQueryForRolesByUserId() {
		return queryForRolesByUserId;
	}


	public void setQueryForRolesByUserId(String queryForRolesByUserId) {
		this.queryForRolesByUserId = queryForRolesByUserId;
	}


	@Override
	public User loadUserById(String username) {
		User user=null;
		try{
		user= jdbcTemplate.queryForObject(queryForUserByUserId, new Object[]{username},new UserDetailsMapper());
		List<GrantedAuthority> roleList=jdbcTemplate.query(queryForRolesByUserId,new Object[]{username},new GrantedAuthorityMapper());
		user.setAuthorities(roleList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}


	
}
