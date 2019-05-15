package com.account.dao;

import org.apache.ibatis.annotations.Param;

import com.account.bean.User;

public interface UserDao {
	void addUser(@Param("user")User user);
	
}
