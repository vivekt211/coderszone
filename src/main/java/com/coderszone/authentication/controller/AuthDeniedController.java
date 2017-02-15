package com.coderszone.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/auth")
public class AuthDeniedController {
	
	@RequestMapping(value="/denied",method = RequestMethod.GET)
	public String denied(Model model) {
		return "index?access=denied";
	}

}
