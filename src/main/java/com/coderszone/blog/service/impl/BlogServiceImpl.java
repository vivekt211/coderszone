package com.coderszone.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.authentication.dao.UserDao;
import com.coderszone.authentication.model.User;
import com.coderszone.blog.dao.BlogDao;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.model.Comment;
import com.coderszone.blog.model.CommentParam;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserNotRegisteredException;
import com.coderszone.common.key.KeyGenService;
import com.coderszone.common.mail.MailService;
import com.coderszone.common.pageutil.Page;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private KeyGenService keyGenService;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailService mailService;
	
	@Override
	public Blog getBlog(int id) throws DataBaseAccessException {
		try {
			Blog blog= blogDao.getBlog(id);
			blog.setTags(blogDao.getBlogTags(id));
			return blog;
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	public List<String> getAlltags() throws DataBaseAccessException {
		try {
			return blogDao.getAlltags();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public int createBlog(Blog blog) throws DataBaseAccessException {
		int id=-1;
		try {

			Map<String, Integer> tagMap = blogDao.getTagIdMap();
			 id = blogDao.insertNewBlog(blog);
			blogDao.insertTagMap(id, blog.getTags(), tagMap);

		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
		return id;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateBlog(Blog blog) throws DataBaseAccessException {
		try {
			Map<String, Integer> tagMap = blogDao.getTagIdMap();
			blogDao.updateBlog(blog);
			blogDao.deleteTagMap(blog.getId());
			blogDao.insertTagMap(blog.getId(), blog.getTags(), tagMap);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	public Page<Blog> getAllBlogsBykeyWord(String keyword, int pageNo, int pageSize) throws DataBaseAccessException {
		try{
			return blogDao.getAllBlogsBykeyWord(keyword,pageNo, pageSize);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	
	@Override
	public Page<Blog> getAllBlogsByUserId(String username, int pageNo, int pageSize) throws DataBaseAccessException {
		try{
			return blogDao.getAllBlogsByUserId(username,pageNo, pageSize);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	public List<Comment> getComments(int blogId) throws DataBaseAccessException {
		try{
			return blogDao.getAllCommentsByBlogId(blogId);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	
	@Override
	public Comment postComments(CommentParam commentParam)
			throws DataBaseAccessException {
		try{
			int id= blogDao.postComment(commentParam);
			Comment comment=blogDao.getCommentById(id);
			return comment;
		}catch(DataAccessException ex){
			ex.printStackTrace();
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void createNewPassword(String id) throws DataBaseAccessException, UserNotRegisteredException, MailServiceException {
		String pasrd = null;
		try{
			User user= userDao.loadUserById(id);
			pasrd = keyGenService.generateNewKeys(); 
			System.out.println("password :"+pasrd);
			userDao.updateUserPassword(id,pasrd);
			 mailService.sendNewPassCode(id, pasrd);
		}catch(EmptyResultDataAccessException ex){
			throw new UserNotRegisteredException("Sorry !the user is not registered");
		}catch(DataAccessException ex){
			throw new DataBaseAccessException("Sorry DataBase access has some problem");
		}
		
	}
	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	
	public KeyGenService getKeyGenService() {
		return keyGenService;
	}
	public void setKeyGenService(KeyGenService keyGenService) {
		this.keyGenService = keyGenService;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public MailService getMailService() {
		return mailService;
	}
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	

	

}
