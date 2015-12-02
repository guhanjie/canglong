/** 
 * Project Name:		canglong 
 * Package Name:	com.canglong.controller 
 * File Name:			UserController.java 
 * Create Date:		2015年12月1日 上午10:06:45 
 * Copyright (c) 2008-2015, 平安集团-平安万里通 All Rights Reserved.
 */  
package com.canglong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canglong.model.User;
import com.canglong.service.UserService;
import com.canglong.util.IdGenerator;

/**
 * Class Name:		UserController<br/>
 * Description:		[description]
 * @time				2015年12月1日 上午10:06:45
 * @author			canglong
 * @version			1.0.0 
 * @since 			JDK 1.6 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{userId}/login", method=RequestMethod.PUT)
	public String login(User user, HttpServletRequest request) {
		user.setLastIp(request.getRemoteAddr());
		userService.login(user);
		return "index";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public void signup(User user, HttpServletRequest request) {
		long userId = IdGenerator.getInstance().nextId();
		user.setId(userId);
		user.setLastIp(request.getRemoteAddr());
		userService.register(user);
	}
}
