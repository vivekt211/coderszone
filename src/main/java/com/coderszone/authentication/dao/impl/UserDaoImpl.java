package com.coderszone.authentication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.mapper.GrantedAuthorityMapper;
import com.coderszone.authentication.mapper.UserDetailsMapper;
import com.coderszone.authentication.mapper.UserMapper;
import com.coderszone.authentication.model.User;
import com.coderszone.common.exception.UserNotRegisteredException;

public class UserDaoImpl implements UserDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private String queryForUserByUserId;
	private String queryForRolesByUserId;
	private String updatePassword;
	private String queryForUserByUserIdPass;

	
	@Override
	public User loadUserByUsername(String username) {
		User user = null;
		try{
		user = jdbcTemplate.queryForObject(queryForUserByUserId, new Object[] { username }, new UserMapper());
		List<GrantedAuthority> roleList = jdbcTemplate.query(queryForRolesByUserId, new Object[] { username }, new GrantedAuthorityMapper());
		user.setAuthorities(roleList);
		}catch(EmptyResultDataAccessException e){
			throw new UsernameNotFoundException("User Id not found");
		}
		return user;

	}

	@Override
	public User loadUserById(String username) throws UserNotRegisteredException {
		User user = null;
		user = jdbcTemplate.queryForObject(queryForUserByUserId, new Object[] { username }, new UserDetailsMapper());
		List<GrantedAuthority> roleList = jdbcTemplate.query(queryForRolesByUserId, new Object[] { username }, new GrantedAuthorityMapper());
		user.setAuthorities(roleList);
		
		return user;
	}

	@Override
	public void updateUserPassword(final String id, final String password) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(updatePassword, 1);
				ps.setString(1, password);
				ps.setString(2, id);
				return ps;
			}
		});
	}
	@Override
	public User loadUserByIdPass(String username,String pass) {
		User user = null;
		user = jdbcTemplate.queryForObject(queryForUserByUserIdPass, new Object[] { username,pass }, new UserDetailsMapper());
		return user;
	}
	public String getUpdatePassword() {
		return updatePassword;
	}

	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
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
	public String getQueryForUserByUserIdPass() {
		return queryForUserByUserIdPass;
	}

	public void setQueryForUserByUserIdPass(String queryForUserByUserIdPass) {
		this.queryForUserByUserIdPass = queryForUserByUserIdPass;
	}

}
