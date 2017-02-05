package com.coderszone.authentication.controller;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderszone.blog.model.Blog;
import com.coderszone.common.Constants;
import com.coderszone.common.DateUtil;
import com.coderszone.common.beans.ResponseModel;
import com.coderszone.common.exception.DataBaseAccessException;

@Controller
public class IndexController {

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value="/forgetpass",method = RequestMethod.GET)
	public String forgetPass(Model model) {
		return "forgetpass";
	}
	
	
	@RequestMapping(value="/registration",method = RequestMethod.GET)
	public String registration(Model model) {
		return "register";
	}

	@RequestMapping(value = "/sessiontimeout", method = RequestMethod.GET)
	public String out(Model model) {
		model.addAttribute("message","Your session has been timed out please login again!");
		return "login";
	}
}
