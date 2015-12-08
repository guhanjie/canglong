package com.canglong.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.canglong.model.User;
import com.canglong.util.ApplicationConstance;
import com.canglong.util.HttpUtils;

@Component(value = "interceptor")
public class Interceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(Interceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURL().toString();
        logger.debug("intercept request, servlet path:[{}]", requestPath);
        String at = HttpUtils.getCookieValueByName(request, ApplicationConstance.COOKIE_ACCESS_TOKEN);
        String userName = HttpUtils.getCookieValueByName(request, ApplicationConstance.COOKIE_USER_NAME);
        if(at != null) {
            User user = (User)request.getAttribute(at);
            if(user != null) {
                if(user.getName().equals(userName)) {
                    return true;
                }
                else {
                    int expiry = 30*24*3600;        //30天过期
                    Cookie cookie = new Cookie(ApplicationConstance.COOKIE_USER_NAME, user.getName());
                    cookie.setMaxAge(expiry);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
