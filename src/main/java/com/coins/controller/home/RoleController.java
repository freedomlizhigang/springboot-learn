package com.coins.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.Role;
import com.coins.handle.CoinException;
import com.coins.service.RoleService;
import com.coins.utils.CommonResult;
import com.coins.utils.ResultEnum;
import com.coins.utils.ResultUtil;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
    private RoleService RoleService;
	@GetMapping
//	public CommonResult<Role> queryRoles(){
//		Role role =  RoleService.selectByName("侃侃");
//		return ResultUtil.success(200,"success",role);
//	}
	public Role queryRoles(){
		Role role =  RoleService.selectByName("侃侃");
		return role;
	}
	@GetMapping("/all")
	public List<Role> queryAllRoles(){
		List<Role> role =  RoleService.selectAllUser();
		return role;
	}
	@GetMapping("/error")
	public void queryError() throws Exception {
//		throw new CoinException(ResultEnum.PRIMARY_SCHOOL);
		RoleService.changemoney();
	}
}