package com.coderszone.blog.controller;

import java.util.Date;
import java.util.List;

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

import com.coderszone.authentication.model.User;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.Constants;
import com.coderszone.common.DateUtil;
import com.coderszone.common.beans.ResponseModel;
import com.coderszone.common.exception.DataBaseAccessException;


@Controller
public class BlogController{

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
			blog.setCreatedBy(user.getUsername());
			blog.setCreatedDate(DateUtil.utilToString(new Date()));
			blog.setModBy(user.getUsername());
			blog.setModDate(DateUtil.utilToString(new Date()));
			int id=blogService.createBlog(blog);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! your post has been submitted ");
			/*Blog blog1=blogService.getBlog(id);
			List<String> tagsList=blogService.getAlltags();
			model.addAttribute("blog",blog1);
			model.addAttribute("tagsList",tagsList);
		*/
		} catch (DataBaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@RequestParam int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		try {
			Blog blog=blogService.getBlog(id);
			List<String> tagsList=blogService.getAlltags();
			model.addAttribute("blog",blog);
			model.addAttribute("tagsList",tagsList);
		} catch (DataBaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		try {
			Blog blog=blogService.getBlog(id);
			List<String> tagsList=blogService.getAlltags();
			model.addAttribute("blog",blog);
			model.addAttribute("tagsList",tagsList);
		} catch (DataBaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			blog.setCreatedBy(user.getUsername());
			blog.setCreatedDate(DateUtil.utilToString(new Date()));
			blog.setModBy(user.getUsername());
			blog.setModDate(DateUtil.utilToString(new Date()));
			blogService.updateBlog(blog);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! your post has been submitted ");
			res.setData(null);
		
		} catch (DataBaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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