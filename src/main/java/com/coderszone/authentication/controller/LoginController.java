package com.coderszone.authentication.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderszone.authentication.model.User;
import com.coderszone.authentication.service.UserCustomService;
import com.coderszone.authentication.service.UserService;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.service.BlogService;
import com.coderszone.common.Constants;
import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.UserNotRegisteredException;
import com.coderszone.common.pageutil.Page;

@Controller
@RequestMapping(value = "/main")
public class LoginController {
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private BlogService blogService;
	@Autowired
	private UserCustomService userCustomService;

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Collection<GrantedAuthority> authri = (Collection<GrantedAuthority>) auth.getAuthorities();
		User u=null;
		try {
			log.debug("Login Request : UserName : "+name);
			u = userCustomService.loadUserById(name);
		} catch (UserNotRegisteredException e1) {
			log.error("Login Request : UserName : "+name+" UserName not found | "+e1.getMessage());
			model.addAttribute("message", e1.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e1.printStackTrace(pw);
			model.addAttribute("trace", sw.toString());
			return "error";
		}
		if (u.getIsVerified() == 0) {
			log.debug("Login Request : UserName : "+name+" UserName is not verified | ");
			model.addAttribute("id", u.getId());
			return "verify";
		}else {
			Iterator it = authri.iterator();
			boolean isAdmin = false;
			boolean isBlogger = false;
			while (it.hasNext()) {
				String role = ((GrantedAuthority) it.next()).getAuthority();
				log.debug("Login Request : UserName : "+name+" has role "+role);
				if (role.equalsIgnoreCase(Constants.ROLE_ADMIN) || role.equalsIgnoreCase(Constants.ROLE_SUPER_ADMIN)) {
					isAdmin = true;
				} else if (role.equalsIgnoreCase(Constants.ROLE_BLOGGER)) {
					isBlogger = true;
				}
			}
			if (isAdmin) {
				UserDetails user = (UserDetails) auth.getPrincipal();
				log.debug("Login Request : UserName : "+name+" is an admin user ");
				model.addAttribute("user", user);
				model.addAttribute("isblogger", true);
				request.getSession().setAttribute("isblogger", true);
				model.addAttribute("isadmin", true);
				request.getSession().setAttribute("isadmin", true);
				Page<Blog> blogPage;
				try {
					blogPage = blogService.getAllBlogsByUserId(((UserDetails) user).getUsername(), 1, 9);
				} catch (DataBaseAccessException e) {
					log.error("Login Request : getting all blogs for this user resulted in error | "+ e.getMessage());
					model.addAttribute("message", e.getMessage());
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					model.addAttribute("trace", sw.toString());
					return "error";
				}
				model.addAttribute("blogPage", blogPage);
				return "mypage";
			} else if (isBlogger) {
				UserDetails user = (UserDetails) auth.getPrincipal();
				log.debug("Login Request : UserName : "+name+" is a blogger user ");
				model.addAttribute("user", user);
				model.addAttribute("isblogger", true);
				request.getSession().setAttribute("isblogger", true);
				model.addAttribute("isadmin", false);
				request.getSession().setAttribute("isadmin", false);
				
				Page<Blog> blogPage;
				try {
					blogPage = blogService.getAllBlogsByUserId(((UserDetails) user).getUsername(), 1, 9);
				} catch (DataBaseAccessException e) {
					log.error("Login Request : getting all blogs for "+((UserDetails) user).getUsername()+" user resulted in error | "+ e.getMessage());
					model.addAttribute("message", e.getMessage());
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					model.addAttribute("trace", sw.toString());
					return "error";
				}
				model.addAttribute("blogPage", blogPage);
				return "mypage";
			} else {
				log.error("Login Request : user has no valid roles  ");
				model.addAttribute("isblogger", false);
				return "login";
			}
		}

	}

	public UserCustomService getUserCustomService() {
		return userCustomService;
	}

	public void setUserCustomService(UserCustomService userCustomService) {
		this.userCustomService = userCustomService;
	}

}
