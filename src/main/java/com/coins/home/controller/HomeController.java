package com.coins.home.controller;

import java.util.HashMap;
import java.util.Map;

import com.coins.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HomeController {

	protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private RedisUtils redisUtils;
	
	/**
     * 需要 add 权限才能访问的api
     */
    @GetMapping("/")
    public Object index() throws Exception {
//		throw new Exception("sdfa");
		redisUtils.set("token","ssss");
		Object ojb = redisUtils.get("token");
		System.out.println(ojb);
		String str = "hello world!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 0);
		map.put("list", str);
		return map;
    }
	
	@GetMapping("/401")
    public Object errors() throws Exception {
		throw new Exception("401");
    }
	
}
