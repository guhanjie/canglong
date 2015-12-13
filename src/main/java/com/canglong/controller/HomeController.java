package com.canglong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canglong.model.User;
import com.canglong.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order() {
		return "order";
	}
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable("userId") Long id) {
		User user = userService.getUser(id);
		user.setPassword("*");
		return user;
	}
}
