package com.coderszone.guest.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderszone.authentication.model.User;
import com.coderszone.authentication.service.UserCustomService;
import com.coderszone.authentication.service.UserService;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.Constants;
import com.coderszone.common.beans.ResponseModel;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.exception.UserNotRegisteredException;
import com.coderszone.common.pageutil.Page;


@Controller
public class HomeController{
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserCustomService userCustomService;
	
	@Value("${coderszone.context}")
	private String CONTEXT_ROOT;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		Page<Blog> blogPage;
		try {
			blogPage = blogService.getAllBlogsBykeyWord(null, 1, 6);
		} catch (DataBaseAccessException e) {
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "home";
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String about(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "aboutus";
	}
	
	@RequestMapping(value="/passchange", method=RequestMethod.GET)
	public String passwordChange(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "passchange";
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public @ResponseBody ResponseModel<String> changePassword(@RequestParam String cpassword,@RequestParam String password,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ResponseModel<String> res=new ResponseModel<String>();
		try {
			UserDetails user=(UserDetails) auth.getPrincipal();
			userCustomService.changePassword(user.getUsername(),cpassword,password);
			res.setResponseCode(Constants.RESPONSE_OK);
			res.setMessage("Thank you ! The password has been changed ! ");
			res.setData(null);
		} catch (DataBaseAccessException e) {
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("There is database exception ");
			res.setData(null);
		} catch (UserNotRegisteredException e) {
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("Your current password doesnot seems to be right ");
			res.setData(null);
		} catch (MailServiceException e) {
			res.setResponseCode(Constants.RESPONSE_FAILED);
			res.setMessage("Mail service has some issues in sending notification ");
			res.setData(null);
		}
		return res;
	}
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String myPage(HttpServletRequest request, @RequestParam(value="pageno",required=false) Integer pageNo,
			@RequestParam(value="pagesize",required=false) Integer pageSize, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		
		Page<Blog> blogPage;
		try {
			if(pageNo==null && pageSize==null){
				pageNo=1;
				pageSize=6;
			}
			blogPage = blogService.getAllBlogsByUserId(((UserDetails) user).getUsername(),pageNo, pageSize);
		} catch (DataBaseAccessException e) {
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "mypage";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String compose(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		List<String> tagsList = null;
		try {
			tagsList = blogService.getAlltags();
		} catch (DataBaseAccessException e) {
			e.printStackTrace();
		}
		model.addAttribute("tagsList", tagsList);
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "write";
	}
	@RequestMapping(value="/maintainimg", method=RequestMethod.GET)
	public String uploadPage(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "upload";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(HttpServletRequest request,Model model) {
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		return "test";
	}
	@RequestMapping(value = "/searchlist", method = RequestMethod.GET)
	public String searchList(HttpServletRequest request, @RequestParam("keyword") String keyword, @RequestParam("pageno") int pageNo,
			@RequestParam("pagesize") int pageSize, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		Page<Blog> blogPage;
		try {
			blogPage = blogService.getAllBlogsBykeyWord(keyword, pageNo, pageSize);
		} catch (DataBaseAccessException e) {
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("context_root",CONTEXT_ROOT);
		model.addAttribute("keyword",keyword);
		return "searchres";
	}
	
	@RequestMapping(value = "/homepg", method = RequestMethod.GET)
	public String searchList(HttpServletRequest request, @RequestParam("pageno") int pageNo,
			@RequestParam("pagesize") int pageSize, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object user= auth.getPrincipal();
		if(user instanceof User){
			model.addAttribute("username",((UserDetails) user).getUsername());
			model.addAttribute("user",((User) user));
		}else{
			//do nothing
		}
		if( request.getSession().getAttribute("isblogger") != null){
			boolean isBlogger = (boolean) request.getSession().getAttribute("isblogger");
			model.addAttribute("isblogger",isBlogger);
		}else{
			model.addAttribute("isblogger",false);
		}
		Page<Blog> blogPage;
		try {
			blogPage = blogService.getAllBlogsBykeyWord("", pageNo, pageSize);
		} catch (DataBaseAccessException e) {
			model.addAttribute("message", e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("context_root",CONTEXT_ROOT);
		return "home";
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
	public UserCustomService getUserCustomService() {
		return userCustomService;
	}

	public void setUserCustomService(UserCustomService userCustomService) {
		this.userCustomService = userCustomService;
	}
	
	
}
