package com.jiang.service;

import com.account.bean.User;
import com.jiang.util.Hession;

@Hession
public interface UserService {
	public String login();
	void addUser(User user);
}
