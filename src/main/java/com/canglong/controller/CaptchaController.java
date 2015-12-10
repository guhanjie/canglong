/** 
 * Project Name:		canglong 
 * Package Name:	com.canglong.controller 
 * File Name:			CaptchaValidateController.java 
 * Create Date:		2015年12月1日 下午4:08:14 
 * Copyright (c) 2008-2015, Canglong All Rights Reserved.
 */  
package com.canglong.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canglong.util.ApplicationConstance;
import com.canglong.util.CaptchaGenerator;

/**
 * Class Name:		CaptchaValidateController<br/>
 * Description:		[description]
 * @time				2015年12月1日 下午4:08:14
 * @author			canglong
 * @version			1.0.0 
 * @since 			JDK 1.6 
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {
		
	@RequestMapping(value="", method=RequestMethod.GET)
	public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //设置验证码答案
        HttpSession session = request.getSession();            
        CaptchaGenerator vCode = new CaptchaGenerator(100,30,4,50);  
        session.setAttribute(ApplicationConstance.SESSION_CAPTCHA, vCode.getCode());  
        vCode.write(response.getOutputStream());  
	}
	
	@RequestMapping(value="/validation", method=RequestMethod.POST)
	@ResponseBody
	public Object validate(String captchaStr, HttpServletRequest request, HttpServletResponse response) {
		String answer = (String)request.getSession().getAttribute(ApplicationConstance.SESSION_CAPTCHA);
		if(captchaStr.equalsIgnoreCase(answer)) {
			return success();
		}
		return fail();
	}
}
