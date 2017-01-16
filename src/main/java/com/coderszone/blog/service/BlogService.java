package com.coderszone.blog.service;

import java.util.List;

import com.coderszone.blog.model.Blog;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.pageutil.Page;

public interface BlogService {

	int createBlog(Blog blog) throws DataBaseAccessException;

	void updateBlog(Blog blog) throws DataBaseAccessException;

	Blog getBlog(int id)throws DataBaseAccessException;

	List<String> getAlltags() throws DataBaseAccessException;

	Page<Blog> getAllBlogsBykeyWord(String keyword, int pageNo, int pageSize) throws DataBaseAccessException;

	Page<Blog> getAllBlogsByUserId(String username, int pageNo, int pageSize) throws DataBaseAccessException;

}