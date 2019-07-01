package com.jiang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.account.bean.User;
import com.account.dao.UserDao;
import com.jiang.service.UserService;
@Service
//@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void addUser( User user ) {

		userDao.addUser( user );	
	}
	
	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void deleteUser( Integer id ) {

		if(id==null)
			return;
		userDao.deleteUser( id );
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void updatePassWord( Integer id, String newPassWord ) {

		if(id==null || StringUtils.isEmpty( newPassWord ))
			return ;
		userDao.updatePassWord( id, newPassWord );
		
	}

	@Override
	public User findUserByFilter( User user ) {

		if(StringUtils.isEmpty( user.getUserName()) || StringUtils.isEmpty( user.getPassWord() ))
			return null;
		return userDao.findUserByFilter( user );
		
	}

	@Override
	public String login() {
		return "login success";
	}

}
