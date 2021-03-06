package com.jiang.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.bean.User;
import com.jiang.service.UserService;

@Controller
@RequestMapping(value="/")
public class TestControll {

	@Autowired
	private UserService userService;
	
	private Logger logger=Logger.getLogger( TestControll.class );
	
	@RequestMapping(value="testVisit")
	@ResponseBody
	public String testVisit(){
		logger.debug( "debug log" );
		logger.info( "info log" );
		logger.warn( "warn log" );
		logger.error( "error log" );
		userService.login();
		return "accountBook_server activty1";
	}
//	@RequestMapping(value="addUser")
//	@ResponseBody
//	public String addUser(User user){
//		try{
//			userService.addUser( user );
//			return "success";
//		}catch (Exception e) {
//			logger.error( e.getMessage(),e );
//			return e.getMessage();
//		}
//		
//	}
}
