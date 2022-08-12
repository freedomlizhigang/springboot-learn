package com.coins.ums.controller;


import com.coins.configure.CryptProperties;
import com.coins.ums.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coins.ums.request.AdminRequest;
import com.coins.ums.serviceimpl.AdminServiceImpl;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@UmsApiRestController("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @GetMapping("/list")
    public Object getList(AdminRequest admin)  throws Exception{
        return adminServiceImpl.getListAndRoleDepartment(admin);
    }

    @GetMapping("/list/names")
    public Object getListNames(AdminRequest admin)  throws Exception{
        return adminServiceImpl.getList(admin);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(AdminRequest.showDetail.class) AdminRequest admin) {
        return adminServiceImpl.getDetail(admin);
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(Admin.createAdmin.class) Admin admin) throws Exception {
        return adminServiceImpl.create(admin);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(Admin.updateAdmin.class) Admin admin) {
        return adminServiceImpl.updateadmin(admin);
    }

    @PostMapping("/pwd")
    public Object postPwd(@Validated(Admin.updatePwd.class) Admin admin) throws Exception {
        return adminServiceImpl.updatepwd(admin);
    }

    @PostMapping("/status")
    public Object postStatus(@Validated(Admin.updateStatus.class) Admin admin) throws Exception {
        return adminServiceImpl.updatestatus(admin);
    }

    @PostMapping("/remove")
    public Object postRemove(@Validated(AdminRequest.removeDetail.class) AdminRequest admin) {
        return adminServiceImpl.remove(admin);
    }

    @PostMapping("/removemultiple")
    public Object postRemoveMultiple(@Validated(AdminRequest.removeMultiple.class) AdminRequest admin) throws Exception {
        return adminServiceImpl.removeMultiple(admin);
    }

    @PostMapping("/selfinfo")
    public Object postSelfEdit(@Validated(Admin.updateStatus.class) Admin admin) throws Exception {
        return adminServiceImpl.updateSelf(admin);
    }

    @PostMapping("/selfpassword")
    public Object postSelfPwd(@Validated(Admin.updateStatus.class) Admin admin) throws Exception {
        return adminServiceImpl.updateSelfPwd(admin);
    }
}

