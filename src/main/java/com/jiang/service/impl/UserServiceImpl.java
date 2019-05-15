package com.jiang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.account.bean.User;
import com.account.dao.UserDao;
import com.jiang.service.UserService;
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void addUser( User user ) {

		userDao.addUser( user );	
	}
	@Override
	public String login() {
		return "login success";
	}

}
