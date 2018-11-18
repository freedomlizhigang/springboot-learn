package com.coins.controller.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class HomeController {
	@GetMapping("/")
    public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		// 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "我的第一个WEB页面");
        view.addObject("desc", "欢迎进入battcn-web 系统");
        return view;
    }
	
}
