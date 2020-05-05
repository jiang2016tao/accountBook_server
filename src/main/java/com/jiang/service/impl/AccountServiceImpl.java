package com.jiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.account.bean.AccountInfo;
import com.account.dao.AccountInfoDao;
import com.jiang.service.AccountService;
import com.jiang.util.ListUtil;
import com.jiang.web.controller.TestControll;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class AccountServiceImpl implements AccountService {

	private Logger logger=Logger.getLogger( AccountServiceImpl.class );
	@Autowired
	private AccountInfoDao accountInfoDao;
	
	@Override
	public List<AccountInfo> findParentAccount() {

		return accountInfoDao.findParentAccount();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void addAccountInfo( AccountInfo accountInfo ) {

		if(StringUtils.isEmpty( accountInfo.getAccountName() ) || accountInfo.getParentId()==null)
			return ;
		accountInfoDao.addAccountInfo( accountInfo );
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void deleteAccountInfoById( Integer id ) {

		if(id==null)
			return;
		accountInfoDao.deleteAccountById( id );
			
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void updateAccountInfo( AccountInfo accountInfo ) {

		if(accountInfo.getId()==null)
			return ;
		accountInfoDao.updateAccount( accountInfo );
			
	}

	@Override
	public List<AccountInfo> findAccountInfo( AccountInfo accountInfo ) {

		List<AccountInfo> accountInfos = accountInfoDao.findAccountInfos( accountInfo );
		if(ListUtil.isEmpty( accountInfos ))
			return accountInfos;
		Map<Integer/*id*/, String/*账户名*/> parentNameMap=new HashMap<Integer, String>();
		for(AccountInfo a:accountInfos){
			if(a.getParentId()!=-1)
				continue;
			parentNameMap.put( a.getId(), a.getAccountName() );
		}
		for(AccountInfo a:accountInfos){
			a.setParentName( parentNameMap.get( a.getId() ) );
		}
		return accountInfos;	
	}

}
