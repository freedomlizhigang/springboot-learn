package com.coins.rbac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coins.rbac.entity.Roles;
import com.coins.rbac.request.RoleListRequest;
import com.coins.rbac.service.lmpl.RolesServiceImpl;
import com.coins.utils.ResultUtil;

/**
 * <p>
 *  角色管理控制器，分配权限
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
	@Autowired
	private RolesServiceImpl roleImpl;
	
	@GetMapping("/list")
	public Object getAll(RoleListRequest roleList) {
		return roleImpl.getList(roleList);
	}
	
	@GetMapping("/detail")
	public Object getDetail(@Validated(RoleListRequest.showDetail.class) RoleListRequest role, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.getDetail(role);
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(Roles.createRole.class) Roles role, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.create(role);
	}
	
	@PostMapping("/update")
	public Object postUpdate(@Validated(Roles.updateName.class) Roles role, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.updatename(role);
	}
	
	@PostMapping("/updatestatus")
	public Object postUpdateStatus(@Validated(Roles.updateStatus.class) Roles role, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.updatestatus(role);
	}
	
	@PostMapping("/remove")
	public Object postRemove(@Validated(RoleListRequest.showDetail.class) RoleListRequest role, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.remove(role);
	}
	
	@GetMapping("/priv")
	public Object getPriv(@Validated(RoleListRequest.showDetail.class) RoleListRequest role, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.getPriv(role);
	}
	@PostMapping("/priv")
	public Object postPriv(@Validated(RoleListRequest.showDetail.class) RoleListRequest role, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return roleImpl.postPriv(role);
	}
}

