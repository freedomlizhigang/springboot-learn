package com.coins.ums.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.ums.entity.Department;
import com.coins.ums.service.IDepartmentService;
import com.coins.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coins.ums.request.DepartmentRequest;
import com.coins.ums.serviceimpl.DepartmentServiceImpl;

@UmsApiRestController("department")
public class DepartmentController {
	@Autowired
	private DepartmentServiceImpl departmentImpl;

	@Autowired
	private IDepartmentService departmentService;
	
	@GetMapping("/tree")
	public Object getAll() {
		return departmentImpl.tree();
	}

	@GetMapping("/select")
	public Object getSelect() {
		return departmentImpl.select();
	}

	@GetMapping("/list")
	public Object getList() {
		return departmentImpl.getList();
	}
	@GetMapping("/detail")
	public Object getDetail(@Validated(DepartmentRequest.showDetail.class) DepartmentRequest department) {
		return departmentService.getById(department.detailId);
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(Department.createDepartment.class) Department department) {
		return departmentService.save(department);
	}
	
	@PostMapping("/update")
	public Object postUpdate(@Validated(Department.updateDepartment.class) Department department) {
		if (department.getId() == department.getParentId()){
			return ResultUtil.validator(403, "父级不能选择自己");
		}
		UpdateWrapper<Department> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",department.getId());
		updateWrapper.set("parent_id",department.getParentId());
		updateWrapper.set("name",department.getName());
		updateWrapper.set("status",department.getStatus());
		return departmentService.update(updateWrapper);
	}
	
	@PostMapping("/status")
	public Object postUpdateStatus(@Validated(Department.statusDepartment.class) Department department) {
		UpdateWrapper<Department> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",department.getId());
		updateWrapper.set("status",department.getStatus());
		return departmentService.update(updateWrapper);
	}
	
	@PostMapping("/remove")
	public Object postRemove(@Validated(DepartmentRequest.showDetail.class) DepartmentRequest department) {
		return departmentImpl.removeDepartment(department);
	}
	@PostMapping("/admins")
	public Object postAdmins(@Validated(DepartmentRequest.showDetail.class) DepartmentRequest department) {
		return departmentImpl.getAdmins(department);
	}
	@PostMapping("/removeadmin")
	public Object postRemoveAdmins(@Validated(DepartmentRequest.removeAdmin.class) DepartmentRequest department) {
		return departmentImpl.removeAdmins(department);
	}

}

