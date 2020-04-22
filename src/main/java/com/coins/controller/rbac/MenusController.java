package com.coins.controller.rbac;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.rbac.Menus;
import com.coins.service.lmpl.rbac.MenusServiceImpl;
import com.coins.utils.ResultUtil;

/**
 * <p>
 *  权限菜单管理
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/menus")
public class MenusController {
	@Autowired
	private MenusServiceImpl menuImpl;
	
	//	获取菜单树
	@GetMapping("tree")
	public Object getTree() {
		return menuImpl.tree();
	}
	
	//	获取下拉菜单树
	@GetMapping("select")
	public Object getSelect() {
		return menuImpl.select();
	}
	
	@PostMapping("create")
	public Object postCreate(@Validated(Menus.createMenu.class) Menus menu, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return menuImpl.create(menu);
	}
	
	@PostMapping("update")
	public Object postUpdate(@Validated(Menus.updateMenu.class) Menus menu, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return menuImpl.update(menu);
	}
	
	@PostMapping("detail")
	public Object postDetail(@Validated(Menus.detailMenu.class) Menus menu, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return menuImpl.detail(menu);
	}
	
	@PostMapping("remove")
	public Object postRemove(@Validated(Menus.detailMenu.class) Menus menu, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return menuImpl.remove(menu);
	}
}

