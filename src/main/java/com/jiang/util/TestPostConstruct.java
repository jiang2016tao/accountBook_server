package com.jiang.util;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TestPostConstruct {

	private Logger logger=Logger.getLogger( TestPostConstruct.class );
	@PostConstruct
	private void init(){
		logger.warn( "@PostConstruct init" );
	}
}
