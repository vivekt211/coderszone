package com.coderszone.blog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.coderszone.blog.model.Blog;
import com.coderszone.common.DateUtil;

public class BlogResultSetMapper implements RowMapper<Blog>{

	@Override
	public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
		Blog blog = new Blog();
		blog.setId(rs.getInt("id"));
		blog.setKeywords(rs.getString("keywords"));
		blog.setTitle(rs.getString("title"));
		blog.setDesc(rs.getString("description"));
		blog.setContent(rs.getString("content"));
		blog.setCreatedBy(rs.getString("created_by"));
		blog.setCreatedDate(DateUtil.timeStampToString(rs.getTimestamp("created_date")));
		blog.setModBy(rs.getString("mod_by"));
		blog.setModDate(DateUtil.timeStampToString(rs.getTimestamp("mod_date")));
		return blog;
	}

	
	

}
