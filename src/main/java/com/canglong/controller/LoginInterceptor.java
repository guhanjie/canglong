package com.canglong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.canglong.config.CookieConfig;
import com.canglong.model.User;
import com.canglong.util.HttpUtils;

@Component(value = "loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("intercept request URL:[{}]", request.getRequestURL());
        String at = HttpUtils.getCookieValueByName(request, CookieConfig.ACCESS_TOKEN);
        if(at != null) {
            User user = (User)request.getSession().getAttribute(at);
            if(user != null) {
                request.setAttribute("user", user);
                return true;
            }
        }
        //URL=  http://    	localhost:    8080		/canglong			/index				/xxx					?goto=xoxoxo
        //解析:   [schema]   [domain]		[port]  		[contextpath]		[servletpath]	[pathinfo]		[querystring]
    	StringBuffer gotoURL = new StringBuffer(request.getContextPath());
    	gotoURL.append("/login?goto=");
    	String servletPath = request.getServletPath();
    	String pathInfo = request.getPathInfo();
    	String queryString = request.getQueryString();
    	gotoURL.append(servletPath==null ? "" : servletPath);
    	gotoURL.append(pathInfo==null ? "" : pathInfo);
    	gotoURL.append(queryString==null ? "" : queryString);
    	response.sendRedirect(gotoURL.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    	String at = HttpUtils.getCookieValueByName(request, ApplicationConstance.COOKIE_ACCESS_TOKEN);
//    	if(at != null) {
//    		modelAndView.addObject("loginUser", request.getSession().getAttribute(at));
//    	}
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
