package com.coins.ums.controller;

import com.coins.ums.entity.Admin;
import com.coins.ums.serviceimpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: TODO 类描述 
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/29
 **/
@UmsApiRestController("/")
public class LoginController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @PostMapping("login")
    public Object postLogin(@Validated(Admin.loginData.class) Admin admin) throws Exception {
        return adminServiceImpl.postLogin(admin);
    }
}
