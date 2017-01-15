package com.coderszone.blog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;



public class TagIdResultSetExtractor implements ResultSetExtractor<Map<String,Integer>>{

	@Override
	public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Integer> mp=new HashMap<String,Integer>();
		while(rs.next()){
			mp.put(rs.getString("name"), rs.getInt("id"));
		}
		return mp;
	}

	
}
