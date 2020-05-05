package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.bean.AccountInfo;

public interface AccountInfoDao {
	void addAccountInfo(AccountInfo accountInfo);
	void deleteAccountById(@Param("id")Integer id);
	void updateAccount(@Param("accountInfo")AccountInfo accountInfo);
	List<AccountInfo> findAccountInfos(@Param("accountInfo")AccountInfo accountInfo);
	List<AccountInfo> findParentAccount();
}
