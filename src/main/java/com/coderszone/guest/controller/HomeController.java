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

import com.coderszone.authentication.model.User;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.pageutil.Page;


@Controller
public class HomeController{
	
	@Autowired
	private BlogService blogService;
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
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String myPage(HttpServletRequest request, @RequestParam("pageno") int pageNo,
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
	
	
}
