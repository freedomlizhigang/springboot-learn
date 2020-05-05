package com.coins.home.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HomeController {
	
	@GetMapping("/")
    public Object index() throws Exception {
//		throw new Exception("sdfa");
		String str = "hello world!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 0);
		map.put("list", str);
		return map;
    }
	
}
