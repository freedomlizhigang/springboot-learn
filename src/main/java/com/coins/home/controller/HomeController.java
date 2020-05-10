package com.coins.home.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HomeController {
	
	
	/**
     * 需要 add 权限才能访问的api
     */
    @RequiresPermissions("add")
    @GetMapping("/")
    public Object index() throws Exception {
//		throw new Exception("sdfa");
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
