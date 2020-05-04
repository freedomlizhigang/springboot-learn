package com.coins.rbac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coins.rbac.request.AdminRequest;
import com.coins.rbac.service.lmpl.AdminsServiceImpl;
import com.coins.utils.ResultUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/admins")
public class AdminsController {
	@Autowired
	private AdminsServiceImpl adminImpl;
	@GetMapping("/list")
	public Object getAll(AdminRequest admin) {
		return adminImpl.getList(admin);
	}
	
	@GetMapping("/detail")
	public Object getDetail(@Validated(AdminRequest.showDetail.class) AdminRequest admin, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return adminImpl.getDetail(admin);
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(AdminRequest.createAdmin.class) AdminRequest admin, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return adminImpl.create(admin);
	}
	@PostMapping("/update")
	public Object postUpdate(@Validated(AdminRequest.updateAdmin.class) AdminRequest admin, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return adminImpl.updateadmin(admin);
	}
	@PostMapping("/pwd")
	public Object postPwd(@Validated(AdminRequest.updatePwd.class) AdminRequest admin, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return adminImpl.updatepwd(admin);
	}
	@PostMapping("/remove")
	public Object postRemove(@Validated(AdminRequest.showDetail.class) AdminRequest admin, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return adminImpl.remove(admin);
	}
}

