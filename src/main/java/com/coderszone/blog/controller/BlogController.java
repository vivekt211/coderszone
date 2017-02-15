package com.coderszone.blog.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderszone.authentication.controller.LoginController;
import com.coderszone.authentication.model.User;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.model.Comment;
import com.coderszone.blog.model.CommentParam;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.Constants;
import com.coderszone.common.DateUtil;
import com.coderszone.common.beans.ResponseModel;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserNotRegisteredException;


@Controller
public class BlogController{

	static Logger log = Logger.getLogger(BlogController.class.getName());

	@Autowired
	private BlogService blogService;
	
	@Value("${coderszone.context}")
	private String CONTEXT_ROOT;
	
	@RequestMapping(value="/postblog", method=RequestMethod.POST)
	public @ResponseBody ResponseModel<Blog> createBlog(@ModelAttribute Blog blog,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<Blog> res=new ResponseModel<Blog>();
		try {
			
			UserDetails user=(UserDetails) auth.getPrincipal();
			log.debug("request recieved for /postblog by "+user.getUsername()+" blog is "+blog.getTitle());
			blog.setCreatedBy(user.getUsername());
			blog.setCreatedDate(DateUtil.utilToString(new Date()));
			blog.setModBy(user.getUsername());
			blog.setModDate(DateUtil.utilToString(new Date()));
			int id=blogService.createBlog(blog);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! your post has been submitted ");
			
		} catch (DataBaseAccessException e) {
			log.error("request /postblog resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage(e.getMessage());
			res.setData(null);
		}
		return res;
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			log.debug("request recieved for /detail from user "+((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			log.debug("request recieved for /detail from anonymous user ");
		}
		try {
			Blog blog=blogService.getBlog(id);
			List<String> tagsList=blogService.getAlltags();
			model.addAttribute("blog",blog);
			model.addAttribute("tagsList",tagsList);
		} catch (DataBaseAccessException e) {
			log.error("request /detail resulted in error "+e.getMessage());
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "details";
	}
	
	@RequestMapping(value="/getupdate", method=RequestMethod.GET)
	public String getUpdate(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			log.debug("request recieved for /getupdate?id="+id+" from user "+((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			log.error("request recieved for /getupdate?id="+id+" from anonymous user ");
			model.addAttribute("message", "You are not loggedin please login");
			StringWriter sw = new StringWriter();
			model.addAttribute("trace", sw.toString());
			return "error";
	    }
		try {
			Blog blog=blogService.getBlog(id);
			List<String> tagsList=blogService.getAlltags();
			model.addAttribute("blog",blog);
			model.addAttribute("tagsList",tagsList);
		} catch (DataBaseAccessException e) {
			log.error("request /getupdate?id="+id+" resulted in error "+e.getMessage());
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "update";
	}
	
	@RequestMapping(value="/updateblog", method=RequestMethod.POST)
	public @ResponseBody ResponseModel<Blog> updateBlog(@ModelAttribute Blog blog,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<Blog> res=new ResponseModel<Blog>();
		try {
			UserDetails user=(UserDetails) auth.getPrincipal();
			log.debug("request recieved for /updateblog?id="+blog.getId()+" from user "+((UserDetails) user).getUsername());
			blog.setCreatedBy(user.getUsername());
			blog.setCreatedDate(DateUtil.utilToString(new Date()));
			blog.setModBy(user.getUsername());
			blog.setModDate(DateUtil.utilToString(new Date()));
			blogService.updateBlog(blog);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! your post has been submitted ");
			res.setData(null);
		
		} catch (DataBaseAccessException e) {
			log.error("request /updateblog?id="+blog.getId()+" resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage(e.getMessage());
			res.setData(null);
		}
		return res;
	}

	@RequestMapping(value="/comments", method=RequestMethod.GET)
	public @ResponseBody ResponseModel<List<Comment>> getComments(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<List<Comment>> res=new ResponseModel<List<Comment>>();
		try {
			List<Comment> commentList=blogService.getComments(id);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("");
			res.setData(commentList);
		
		} catch (DataBaseAccessException e) {
			e.printStackTrace();
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("There is database exception ");
			res.setData(null);
		}
		return res;
	}
	
	@RequestMapping(value="/postcomment", method=RequestMethod.POST)
	public @ResponseBody ResponseModel<Comment> postComments(@ModelAttribute CommentParam commentParam,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<Comment> res=new ResponseModel<Comment>();
		try {
			UserDetails user=(UserDetails) auth.getPrincipal();
			log.debug("request recieved for /postcomment from user "+((UserDetails) user).getUsername());
			Comment comment=blogService.postComments(commentParam);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("");
			res.setData(comment);
		
		} catch (DataBaseAccessException e) {
			log.error("request /postcomment resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage(e.getMessage());
			res.setData(null);
		}
		return res;
	}
	@RequestMapping(value="/delpost", method=RequestMethod.GET)
	public @ResponseBody ResponseModel<String> delPost(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<String> res=new ResponseModel<String>();
		try {
			UserDetails user=(UserDetails) auth.getPrincipal();
			log.debug("request recieved for /delpost from user "+((UserDetails) user).getUsername());
			blogService.deleteBlog(id,user.getUsername());
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! The post has been deleted ! ");
			res.setData(null);
		} catch (DataBaseAccessException e) {
			log.error("request /delpost resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage(e.getMessage());
			res.setData(null);
		}
		return res;
	}
	@RequestMapping(value="/delcomment", method=RequestMethod.GET)
	public @ResponseBody ResponseModel<String> delComment(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<String> res=new ResponseModel<String>();
		try {
			UserDetails user=(UserDetails) auth.getPrincipal();
			log.debug("request recieved for /delcomment from user "+((UserDetails) user).getUsername());
			blogService.deleteComment(id,user.getUsername());
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! The comment has been deleted ! ");
			res.setData(null);
		} catch (DataBaseAccessException e) {
			log.error("request /delpost resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("There is database exception ");
			res.setData(null);
		}
		return res;
	}
	@RequestMapping(value="/newpass",method = RequestMethod.GET)
	public @ResponseBody ResponseModel<String> getNewPass(@RequestParam String id,Model model) {
		ResponseModel<String> res=new ResponseModel<String>();
		try {
			log.debug("request recieved for /newpass  for "+id);
			blogService.createNewPassword(id);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! Your new password has been send to you. Please check your mail ");
		} catch (DataBaseAccessException e) {
			log.error("request /newpass resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("Sorry ! Something went horribly wrong. I will fix it. please give me some time ! ");
			e.printStackTrace();
		} catch (UserNotRegisteredException e) {
			log.error("request /newpass resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("Sorry ! The user doesnot seems to be registered . ");
		} catch (MailServiceException e) {
			log.error("request /newpass resulted in error "+e.getMessage());
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("Sorry ! We are experiencing problem in mail service, The new password could not be sent. ");
		}
		return res;
	}
	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public String getCONTEXT_ROOT() {
		return CONTEXT_ROOT;
	}

	public void setCONTEXT_ROOT(String cONTEXT_ROOT) {
		CONTEXT_ROOT = cONTEXT_ROOT;
	}

		
}
