package com.coderszone.blog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.coderszone.blog.model.Blog;
import com.coderszone.common.DateUtil;

public class BlogResultSetExtractor implements ResultSetExtractor<List<Blog>>{

	@Override
	public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Blog> BlogListMap=new HashMap<Integer,Blog>();
		List<Blog> blogList =new ArrayList<Blog>();
		List<Integer> idList=new ArrayList<Integer>();  //to maintain same order as resultset
		
		while(rs.next()){
			Blog blog = new Blog();
			int id = rs.getInt("id");
			blog.setId(id);
			blog.setTitle(rs.getString("title"));
			blog.setKeywords(rs.getString("keywords"));
			blog.setTags(rs.getString("tagName"));
			blog.setDesc(rs.getString("description"));
			blog.setCreatedBy(rs.getString("created_by"));
			blog.setCreatedDate(DateUtil.timeStampToString(rs.getTimestamp("created_date")));
			blog.setModBy(rs.getString("mod_by"));
			blog.setModDate(DateUtil.timeStampToString(rs.getTimestamp("mod_date")));
			BlogListMap.put(id, blog);
			idList.add(id);
		}
		
		for(Integer blogid:idList){
			blogList.add(BlogListMap.get(blogid));
		}
		return blogList;
	}

}
