package com.canglong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canglong.mapper.UserMapper;
import com.canglong.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
