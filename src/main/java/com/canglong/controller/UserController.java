/** 
 * Project Name:		canglong 
 * Package Name:	com.canglong.controller 
 * File Name:			UserController.java 
 * Create Date:		2015年12月1日 上午10:06:45 
 * Copyright (c) 2008-2015, 平安集团-平安万里通 All Rights Reserved.
 */  
package com.canglong.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canglong.model.User;
import com.canglong.service.UserService;
import com.canglong.util.HttpUtils;
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
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(HttpServletRequest request) {        
        return "login";
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpServletRequest request, HttpServletResponse response) {
		user.setLastIp(HttpUtils.getIpAddress(request));
		user = userService.login(user);
        String ticket = UUID.randomUUID().toString().replace("-", "");
        int expiry = 30*24*3600;        //30天过期
        Cookie cookie = new Cookie("user_name", user.getName());
		cookie.setMaxAge(expiry);
		cookie.setPath("/");
		response.addCookie(cookie);
		cookie = new Cookie("access_token", ticket);
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        response.addCookie(cookie);
		//cookie.setDomain(domainName);
        //cookie.setSecure(secure);// 为true时用于https
		request.getSession().setAttribute(ticket, user);
		String gotoUrl = request.getParameter("gotoURL");
		if(StringUtils.isBlank(gotoUrl)) {
		    return "index";
		}
		else {
		    return "forward:"+gotoUrl;
		}
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public void signup(User user, HttpServletRequest request) {
		long userId = IdGenerator.getInstance().nextId();
		user.setId(userId);
		user.setLastIp(request.getRemoteAddr());
		userService.register(user);
	}
}
