package com.coderszone.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
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
