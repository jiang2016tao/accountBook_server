package com.jiang.service;

import java.util.List;

import com.account.bean.AccountInfo;
import com.jiang.util.Hession;

@Hession
public interface AccountService {

	void addAccountInfo(AccountInfo accountInfo);
	void deleteAccountInfoById(Integer id);
	void updateAccountInfo(AccountInfo accountInfo);
	List<AccountInfo> findAccountInfo(AccountInfo accountInfo);
}
