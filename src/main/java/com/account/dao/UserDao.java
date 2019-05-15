package com.account.dao;

import org.apache.ibatis.annotations.Param;

import com.account.bean.User;

public interface UserDao {
	void addUser(@Param("user")User user);
	void deleteUser(@Param("id")Integer id);
	User findUserByFilter(@Param("user")User user);
	void updatePassWord(@Param("id")Integer id,@Param("newPassWord")String newPassWord);
}
