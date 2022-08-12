package com.coins.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coins.home.entity.ApiToken;
import com.coins.utils.JWTUtil;
import com.coins.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

	protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private RedisUtils redisUtils;
	
	/**
     * 需要 add 权限才能访问的api
     */
    @GetMapping("/")
    public Object index() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 0);
		map.put("list", "fsad");
		return map;
    }
	
	@GetMapping("/401")
    public Object errors() throws Exception {
		throw new Exception("401");
    }
	
}
