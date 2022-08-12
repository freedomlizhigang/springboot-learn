package com.coins.ums.controller;


import com.coins.ums.service.IMenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coins.ums.entity.Menu;
import com.coins.ums.serviceimpl.MenuServiceImpl;
import com.coins.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestParam;

@UmsApiRestController("menu")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuImpl;
	@Autowired
	private IMenuService menuService;
	
	//	获取菜单树
	@GetMapping("tree")
	public Object getTree() {
		return ResultUtil.success(menuImpl.tree());
	}
	//	获取下拉菜单树
	@GetMapping("list")
	public Object getList() throws JsonProcessingException {
		return menuImpl.leftList();
	}
	
	//	获取下拉菜单树
	@GetMapping("select")
	public Object getSelect() {
		return menuImpl.select();
	}

	//	获取菜单路径
	@GetMapping("breadcrumb")
	public Object getBreadCrumb(@RequestParam String url) {
		return menuImpl.breadCrumb(url);
	}

	@PostMapping("create")
	public Object postCreate(@Validated(Menu.createMenu.class) Menu menu) {
		return menuService.save(menu);
	}
	
	@PostMapping("update")
	public Object postUpdate(@Validated(Menu.updateMenu.class) Menu menu) {
		return menuImpl.update(menu);
	}
	
	@GetMapping("detail")
	public Object postDetail(@Validated(Menu.detailMenu.class) Menu menu) {
		return menuService.getById(menu.getId());
	}
	
	@PostMapping("remove")
	public Object postRemove(@Validated(Menu.detailMenu.class) Menu menu) {
		return menuImpl.remove(menu);
	}

	@PostMapping("sort")
	public Object postSort(@Validated(Menu.sortMenu.class) Menu menu){
		return menuImpl.sort(menu);
	}
}

