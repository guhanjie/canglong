package com.canglong.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canglong.exception.WebExceptionEnum;
import com.canglong.exception.WebExceptionFactory;
import com.canglong.mapper.UserMapper;
import com.canglong.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public void register(User user) {
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
		if(userMapper.selectByName(user.getName()) == null) {
			throw WebExceptionFactory.exception(WebExceptionEnum.DATA_HAS_EXIST, "user.name.duplicate");
		}		
		//校验图形验证码
		
		//注册用户
		
	}
}
