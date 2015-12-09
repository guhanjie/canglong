package com.canglong.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canglong.exception.WebExceptionEnum;
import com.canglong.exception.WebExceptionFactory;
import com.canglong.mapper.UserMapper;
import com.canglong.model.User;
import com.canglong.util.DateTimeUtil;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public User login(User user) {
		User u = userMapper.selectByName(user.getName());
		if(u == null) {
			throw WebExceptionFactory.exception(WebExceptionEnum.ACCESS_FORBIDDEN, "用户名不存在，请先注册");
		}
		if(user.getPassword().equals(u.getPassword())) {
			Date now = new Date();
			LOGGER.info("User[{}] login system from host[{}] on [{}]", u.getName(), u.getLastIp(), 
			                                        DateTimeUtil.DateToString(now, DateTimeUtil.DateStyle.YYYY_MM_DD_HH_MM_SS));
			u.setLastActiveTime(now);
			u.setLastIp(user.getLastIp());
			userMapper.updateByPrimaryKeySelective(u);
		}
		else {
			throw WebExceptionFactory.exception(WebExceptionEnum.ACCESS_FORBIDDEN, "密码错误");
		}
		return u;
	}
	
	public boolean register(User user) {
		//校验注册信息完整性
		if(user.getId() == null) {
			throw WebExceptionFactory.exception(WebExceptionEnum.USER_ID_NOT_EXIST);
		}
		if(user.getName() == null || StringUtils.isEmpty(user.getName())) {
			throw WebExceptionFactory.exception(WebExceptionEnum.USER_NAME_NOT_EXIST);
		}
		if(user.getName() == null || StringUtils.isEmpty(user.getPassword())) {
			throw WebExceptionFactory.exception(WebExceptionEnum.USER_PASSWORD_NOT_EXIST);
		}
		if(user.getName() == null || StringUtils.isEmpty(user.getEmail())) {
			throw WebExceptionFactory.exception(WebExceptionEnum.USER_EMAIL_NOT_EXIST);
		}
		//校验用户重名
		if(userMapper.selectByName(user.getName()) != null) {
			throw WebExceptionFactory.exception(WebExceptionEnum.DATA_HAS_EXIST, "用户名已存在");
		}
		//注册用户
		int res = userMapper.insert(user);
		return res == 1;
	}
}
