package com.jiang.service.impl;

import org.springframework.stereotype.Service;

import com.jiang.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public String login() {
		return "login success";
	}

}
