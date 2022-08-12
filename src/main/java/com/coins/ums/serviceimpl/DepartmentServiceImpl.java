package com.coins.ums.serviceimpl;

import com.coins.ums.entity.Admin;
import com.coins.ums.entity.Department;
import com.coins.ums.entity.DepartmentAdmin;
import com.coins.ums.mapper.DepartmentMapper;
import com.coins.ums.request.DepartmentRequest;
import com.coins.ums.service.IDepartmentAdminService;
import com.coins.ums.service.IAdminService;
import com.coins.ums.service.IDepartmentService;
import com.coins.utils.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import com.coins.utils.TreeNodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IDepartmentAdminService departmentAdminService;

    // 获取所有部门
    public Object tree() {
        // 查出所有菜单来
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<Department> list = this.list(queryWrapper);
        //树形结构数据生成
        List<Map> result = TreeNodeUtils.toTree(0, list.stream().collect(Collectors.toList()));
        return result;
    }

    public Object select() {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id").select("id","parent_id","name");
        List<Department> list = this.list(queryWrapper);
        //树形结构数据生成
        List<Map> result = TreeNodeUtils.toSelect(0, list.stream().collect(Collectors.toList()));
        return result;
    }

    public List<Department> getList() {
        // 查出所有菜单来
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id").eq("status", 1);
        List<Department> list = this.list(queryWrapper);
        return list;
    }

    //	删除单条
    public Object removeDepartment(DepartmentRequest department) {
        // 查所有菜单出来
        List<Department> list = this.list(null);
        // 置空所有ids的存放空间
        List<Integer> ids = TreeNodeUtils.getIds(department.detailId,list.stream().collect(Collectors.toList()));
        ids.add(department.detailId);
        // 查有没有用户
        QueryWrapper<DepartmentAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_id", department.detailId);
        Long count = departmentAdminService.count(queryWrapper);
        if (count > 0) {
            return ResultUtil.validator(403, "确认所有部门下没有用户才可以删除");
        }
        Boolean result = this.removeByIds(ids);
        return result;
    }


    //	查用户信息
    public List<Admin> getAdmins(DepartmentRequest department) {
        QueryWrapper<DepartmentAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_id", department.detailId);
        queryWrapper.select("admin_id");
        List<Object> list = departmentAdminService.listObjs(queryWrapper);
        QueryWrapper<Admin> queryAdmins = new QueryWrapper<>();
        queryAdmins.in(list.size() > 0, "id", list).lt(list.size() <= 0, "id", 0);
        List<Admin> admins = adminService.list(queryAdmins);
        return admins;
    }

    // 移出用户
    public Boolean removeAdmins(DepartmentRequest department) {
        QueryWrapper<DepartmentAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_id", department.detailId);
        queryWrapper.eq("admin_id", department.adminId);
        Boolean result = departmentAdminService.remove(queryWrapper);
        return result;
    }
}
