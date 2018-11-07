package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.configure.MyProperties;
import com.springboot.configure.MyProperties2;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;

@RequestMapping("/properties")
@RestController
public class PropertiesController {
	private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);

    private final MyProperties myProperties;
    
    private final MyProperties2 myProperties2;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    public PropertiesController(MyProperties myProperties1,MyProperties2 myProperties2) {
        this.myProperties = myProperties1;
        this.myProperties2 = myProperties2;
    }
    
    @GetMapping("/1")
    public MyProperties myProperties() {
    	final int row1 = userMapper.insert(new User("u1", "p1"));
        log.info("[ss] - [{}]", row1);
        
        log.info("=================================================================================================");
        log.info(myProperties.toString());
        log.info("=================================================================================================");
        return myProperties;
    }
    
    @GetMapping("/2")
    public MyProperties2 myProperties2() {
        log.info("=================================================================================================");
        log.info(myProperties2.toString());
        log.info("=================================================================================================");
        return myProperties2;
    }
}
