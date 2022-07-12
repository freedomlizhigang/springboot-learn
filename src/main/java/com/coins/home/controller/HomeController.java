package com.coins.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coins.home.entity.ApiToken;
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
		ApiToken tokenObj = new ApiToken();
		tokenObj.name = "Li";
		tokenObj.age = 23;
		Map<String,String> privMap = new HashMap();
		privMap.put("token","a");
		privMap.put("token1","b");
		privMap.put("token2","c");
		privMap.put("token3","d");
		tokenObj.priv = privMap;
		List<String> label = new ArrayList<>();
		label.add("home/s");
		tokenObj.label = label;
		redisUtils.set("token",tokenObj);
		Object ojb = redisUtils.get("token");
		System.out.println(ojb);
		ApiToken aobj = (ApiToken) ApiToken.class.cast(ojb);
		System.out.println(aobj.priv.get("token1"));
		String str = "hello world!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 0);
		map.put("list", aobj.priv.get("token2"));
		return map;
    }
	
	@GetMapping("/401")
    public Object errors() throws Exception {
		throw new Exception("401");
    }
	
}
