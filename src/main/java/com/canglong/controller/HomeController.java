package com.canglong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canglong.model.User;
import com.canglong.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	public String hello(@PathVariable("userId") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "user";
	}
}