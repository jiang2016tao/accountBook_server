package com.jiang.service.impl;

import org.springframework.stereotype.Service;

import com.jiang.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public String addAccount() {
		return "add account success";
	}

}
