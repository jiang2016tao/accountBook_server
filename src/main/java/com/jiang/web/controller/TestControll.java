package com.jiang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/")
public class TestControll {

	@RequestMapping(value="testVisit")
	@ResponseBody
	public String testVisit(){
		return "accountBook_server activty";
	}
}
