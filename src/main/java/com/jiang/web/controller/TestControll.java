package com.jiang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiang.service.UserService;

@Controller
@RequestMapping(value="/")
public class TestControll {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="testVisit")
	@ResponseBody
	public String testVisit(){
		userService.login();
		return "accountBook_server activty";
	}
}
