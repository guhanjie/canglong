/** 
 * Project Name:		canglong 
 * Package Name:	com.canglong.controller 
 * File Name:			UserController.java 
 * Create Date:		2015年12月1日 上午10:06:45 
 * Copyright (c) 2008-2015, Canglong All Rights Reserved.
 */  
package com.canglong.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canglong.exception.WebException;
import com.canglong.model.User;
import com.canglong.service.UserService;
import com.canglong.util.ApplicationConstance;
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
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(HttpServletRequest request) {
		User user = getUser(request);
		if(user != null) {
			return "user";
		}
        return "login";
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpServletRequest request, HttpServletResponse response) {
		user.setLastIp(HttpUtils.getIpAddress(request));
		user = userService.login(user);
        String ticket = UUID.randomUUID().toString().replace("-", "");
        int expiry = 90*24*3600;        //90天过期
        Cookie cookie = new Cookie(ApplicationConstance.COOKIE_ACCESS_TOKEN, ticket);
        //cookie.setDomain(domainName);
        //cookie.setSecure(secure);// 为true时用于https
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);								//store in cookie
		request.getSession().setAttribute(ticket, user);		//store in session
		String gotoUrl = request.getParameter("goto");
		if(StringUtils.isBlank(gotoUrl)) {
		    return "user";
		}
		else {
		    return "redirect:"+gotoUrl;
		}
	}
	
   @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
       Cookie[] cookies = request.getCookies();
       if(cookies != null) {
           for(Cookie cookie : cookies) {
               if(ApplicationConstance.COOKIE_USER_NAME.equals(cookie.getName())) {
            	   cookie.setPath("/");
                   cookie.setMaxAge(0); //Delete Cookie
					response.addCookie(cookie);
               }
               else if(ApplicationConstance.COOKIE_ACCESS_TOKEN.equals(cookie.getName())) {
            	   cookie.setPath("/");
                   cookie.setMaxAge(0); //Delete Cookie
					response.addCookie(cookie);
                   request.getSession().removeAttribute(cookie.getValue()); //Delete session with request
               }
           }
       }
        return "login";
    }
	
   @RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(HttpServletRequest request) {
	   User user = getUser(request);
		if(user != null) {
			return "user";
		}
		return "signup";
	}
   
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(User user, HttpServletRequest request, HttpServletResponse response) {
		long userId = IdGenerator.getInstance().nextId();
		user.setId(userId);
		user.setLastIp(HttpUtils.getIpAddress(request));
		try {
			if(userService.register(user)) {
				return login(user, request, response);
			}
		} catch(WebException e) {
			request.setAttribute(e.getMessage(), e.getScreenMessage());
		}
		return "signup";
	}
}
