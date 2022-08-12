package com.coins.ums.controller;


import com.coins.ums.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coins.ums.entity.Role;
import com.coins.ums.request.RoleRequest;
import com.coins.ums.serviceimpl.RoleServiceImpl;
/**
 * @description: 角色管理控制器
 * @author: lzg(萤火科技：854378082@qq.com)
 * @date:  2022/07/15
 **/
@UmsApiRestController("role")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleImpl;

	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/list")
	public Object getAll(RoleRequest roleList) {
		return roleImpl.getList(roleList);
	}
	
	@GetMapping("/detail")
	public Object getDetail(@Validated(RoleRequest.showDetail.class) RoleRequest role) {
		return roleService.getById(role.getDetailId());
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(Role.createRole.class) Role role) {
		return roleService.save(role);
	}
	
	@PostMapping("/update")
	public Object postUpdate(@Validated(Role.updateName.class) Role role) {
		return roleImpl.updatename(role);
	}
	
	@PostMapping("/status")
	public Object postUpdateStatus(@Validated(Role.updateStatus.class) Role role) {
		return roleImpl.updatestatus(role);
	}
	
	@PostMapping("/remove")
	public Object postRemove(@Validated(RoleRequest.showDetail.class) RoleRequest role) throws Exception {
		return roleImpl.remove(role);
	}

	@PostMapping("/removemultiple")
	public Object postRemoveMultiple(@Validated(RoleRequest.removeMultiple.class) RoleRequest role) throws Exception {
		return roleImpl.removeMultiple(role);
	}
	
	@GetMapping("/priv")
	public Object getPriv(@Validated(RoleRequest.showDetail.class) RoleRequest role) throws Exception {
		return roleImpl.getPriv(role);
	}
	@PostMapping("/priv")
	public Object postPriv(@Validated(RoleRequest.showDetail.class) RoleRequest role) throws Exception {
		return roleImpl.postPriv(role);
	}
	@GetMapping("/admins")
	public Object getAdmins(@Validated(RoleRequest.showDetail.class) RoleRequest role) throws Exception {
		return roleImpl.getAdmins(role);
	}
	@PostMapping("/removeadmin")
	public Object postRemoveAdmin(@Validated(RoleRequest.removeDetail.class) RoleRequest role) throws Exception {
		return roleImpl.postRemoveAdmin(role);
	}
}

