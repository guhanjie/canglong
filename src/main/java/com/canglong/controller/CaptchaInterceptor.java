package com.canglong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.canglong.model.User;
import com.canglong.util.ApplicationConstance;
import com.canglong.util.HttpUtils;

@Component(value = "captchaInterceptor")
public class CaptchaInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("intercept request URL:[{}]", request.getRequestURL());
        
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
