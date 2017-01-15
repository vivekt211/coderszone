package com.coderszone.blog.dao;

import java.util.List;
import java.util.Map;

import com.coderszone.blog.model.Blog;
import com.coderszone.common.pageutil.Page;

public interface BlogDao {

	Map<String, Integer> getTagIdMap();

	int insertNewBlog(Blog blog);

	//void insertTagMap(int id, List<String> tags, Map<String, Integer> tagMap);

	void updateBlog(Blog blog);

	void deleteTagMap(int id);

	Blog getBlog(int id);

	List<String> getAlltags();

	String getBlogTags(int id);

	Page<Blog> getAllBlogsBykeyWord(String keyword, int pageNo, int pageSize);

	Page<Blog> getAllBlogsByUserId(String username, int pageNo, int pageSize);

	void insertTagMap(int id, String tags, Map<String, Integer> tagMap);


}
