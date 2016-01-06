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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canglong.config.CookieConfig;
import com.canglong.config.SecurityConfig;
import com.canglong.exception.WebException;
import com.canglong.model.User;
import com.canglong.service.UserService;
import com.canglong.util.DESUtils;
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
	public String login(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setLastIp(HttpUtils.getIpAddress(request));
		try {
		    user = userService.login(user);
		    String promotionID = "";
			if(user == null || user.getId() == null) {
				promotionID = "推广ID生成失败，未获取到用户信息";
			}
			promotionID = DESUtils.encrypt(user.getId().toString(), SecurityConfig.DES_SECRET_KEY);
			user.setPromotionID(promotionID);
		} catch(WebException e) {
		    request.setAttribute("loginError", e.getScreenMessage());
		    return "login";
		}
        String ticket = UUID.randomUUID().toString().replace("-", "");
        int expiry = 90*24*3600;        //90天过期
        Cookie cookie = new Cookie(CookieConfig.ACCESS_TOKEN, ticket);
        //cookie.setDomain(domainName);
        //cookie.setSecure(secure);// 为true时用于https
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);								//store in cookie
		request.getSession().setAttribute(ticket, user);		//store in session
		String gotoUrl = request.getParameter("goto");
		if(StringUtils.isBlank(gotoUrl)) {
			model.addAttribute("loginUser", user);
		    return "user";
		}
		else {
		    return "redirect:"+gotoUrl;
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Object login(User user, HttpServletRequest request, HttpServletResponse response) {
		user.setLastIp(HttpUtils.getIpAddress(request));
		try {
		    user = userService.login(user);
		    String promotionID = "";
			if(user == null || user.getId() == null) {
				promotionID = "推广ID生成失败，未获取到用户信息";
			}
			promotionID = DESUtils.encrypt(user.getId().toString(), SecurityConfig.DES_SECRET_KEY);
			user.setPromotionID(promotionID);
		} catch(WebException e) {
		    return fail(response, e);
		}
        String ticket = UUID.randomUUID().toString().replace("-", "");
        int expiry = 90*24*3600;        //90天过期
        Cookie cookie = new Cookie(CookieConfig.ACCESS_TOKEN, ticket);
        //cookie.setDomain(domainName);
        //cookie.setSecure(secure);// 为true时用于https
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);								//store in cookie
		request.getSession().setAttribute(ticket, user);		//store in session
		return success(user);
	}
	
   @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
       Cookie[] cookies = request.getCookies();
       if(cookies != null) {
           for(Cookie cookie : cookies) {
               if(CookieConfig.USER_NAME.equals(cookie.getName())) {
            	   cookie.setPath("/");
                   cookie.setMaxAge(0); //Delete Cookie
					response.addCookie(cookie);
               }
               else if(CookieConfig.ACCESS_TOKEN.equals(cookie.getName())) {
            	   cookie.setPath("/");
                   cookie.setMaxAge(0); //Delete Cookie
					response.addCookie(cookie);
                   request.getSession().removeAttribute(cookie.getValue()); //Delete session with request
               }
           }
       }
        return "redirect:login";
    }
	
   @RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(HttpServletRequest request, HttpServletResponse response) {
	   User user = getUser(request);
		if(user != null) {
			return "user";
		}
		//store in cookie	
		String promotion = request.getParameter("promotion");
        Cookie cookie = new Cookie(CookieConfig.PROMOTION, promotion);
        response.addCookie(cookie);
		return "signup";
	}
   
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public Object signup(User user, HttpServletRequest request, HttpServletResponse response) {
	    if(!CaptchaController.validate(request)) {
	        return fail("验证码不正确，请重新输入");
	    }
	    String promotion = HttpUtils.getCookieValueByName(request, CookieConfig.PROMOTION);
	    if(promotion != null) {
	    	String promotor = DESUtils.decrypt(promotion, SecurityConfig.DES_SECRET_KEY);
	    	user.setPromotor(Long.valueOf(promotor));
	    }
		long userId = IdGenerator.getInstance().nextId();
		user.setId(userId);
		user.setLastIp(HttpUtils.getIpAddress(request));
		try {
			if(userService.register(user)) {
				return success(user);
				//return login(user, request, response);
			}
		} catch(Exception e) {
			//request.setAttribute(e.getMessage(), e.getScreenMessage());
		    return fail(response, e);
		}
		return fail("对不起，用户注册失败");
	}
	
	@RequestMapping(value="/promotion", method=RequestMethod.GET)
	@ResponseBody
	public Object getPromotionURL(HttpServletRequest request) {
		User user = getUser(request);
		if(user == null || user.getId() == null) {
			return fail("推广ID生成失败，未获取到用户信息");
		}
		String promotionID = DESUtils.encrypt(user.getId().toString(), SecurityConfig.DES_SECRET_KEY);
		StringBuffer promotionURL = new StringBuffer(request.getContextPath());
		promotionURL.append("/signup?promotion=");
		promotionURL.append(promotionID);
		return success(promotionURL);
	}
	
    private User getUser(HttpServletRequest request) {
        String at = HttpUtils.getCookieValueByName(request, CookieConfig.ACCESS_TOKEN);
        if(at != null) {
            User user = (User)request.getSession().getAttribute(at);
            return user;
        }
        return null;
    }

}
