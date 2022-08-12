package com.coins.ums.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.ums.entity.*;
import com.coins.ums.mapper.RoleMapper;
import com.coins.ums.request.RoleRequest;
import com.coins.ums.service.*;
import com.coins.utils.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    @Lazy
    private IAdminService adminService;
    @Autowired
    private IRoleAdminService roleAdminService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRolePrivService roleprivService;

    // 获取列表
    public Map<String, Object> getList(RoleRequest roleRequest) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(roleRequest.key != "", "name", roleRequest.key);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + roleRequest.offset + "," + roleRequest.pageSize);
        List<Role> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }

    //	更新单条名称
    public Boolean updatename(Role role) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", role.getId());
        updateWrapper.set("name", role.getName());
        updateWrapper.set("status", role.getStatus());
        Boolean detail = this.update(new Role(), updateWrapper);
        return detail;
    }

    //	更新单条状态
    public Boolean updatestatus(Role role) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", role.getId());
        updateWrapper.set("status", role.getStatus());
        Boolean detail = this.update(new Role(), updateWrapper);
        return detail;
    }

    //	删除单条
    @Transactional
    public Object remove(RoleRequest roleRequest) throws Exception {
        // 先检查部门下有没有用户
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleRequest.detailId);
        if (roleAdminService.count(queryWrapper) > 0) {
            return ResultUtil.validator(403, "角色下有用户");
        }
        Boolean result = this.removeById(roleRequest.detailId);
        // 同时删除关联的权限信息
        QueryWrapper<RolePriv> privWrapper = new QueryWrapper<>();
        privWrapper.eq("role_id", roleRequest.detailId);
        roleprivService.remove(privWrapper);
        return result;
    }

    @Transactional
    public Object removeMultiple(RoleRequest roleRequest) throws Exception {
        // 先检查部门下有没有用户
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleRequest.detailIds);
        if (roleAdminService.count(queryWrapper) > 0) {
            return ResultUtil.validator(403, "角色下有用户");
        }
        Boolean result = this.removeByIds(roleRequest.detailIds);
        // 同时删除关联的权限信息
        QueryWrapper<RolePriv> privWrapper = new QueryWrapper<>();
        privWrapper.in("role_id", roleRequest.detailIds);
        roleprivService.remove(privWrapper);
        return result;
    }

    // 获取权限信息
    public Object getPriv(RoleRequest roleRequest) throws Exception {
        // 先检查角色权限
        QueryWrapper<RolePriv> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleRequest.detailId).select("id", "menu_id");
        List<RolePriv> rp = roleprivService.list(queryWrapper);
        List<Menu> all = menuService.list(null);
        // 转格式
        List<Map> treeMenu = createTree(rp, all, 0);
        return treeMenu;
    }

    // 递归树形菜单
    private List<Map> createTree(List<RolePriv> privs, List<Menu> menus, int pid) {
        List<Map> treeMenu = new ArrayList<>();
        for (Menu menu : menus) {
            if (pid == menu.getParentId()) {
                Map<String, Object> tmp = new HashMap();
                tmp.put("menu_id", menu.getId());
                tmp.put("title", menu.getName());
                tmp.put("expand", false);
                tmp.put("checked", false);
                // 所有子菜单都选中的时候，此菜单选中checked，部分选中时selected
                List<Object> child_count = new ArrayList<>();
                child_count = menus.stream().filter((Menu m) -> Arrays.asList(m.getParentId()).contains(menu.getId())).collect(Collectors.toList());
                // 没有子菜单的时候，判断他本身
                if (child_count.size() == 0) {
                    child_count = privs.stream().filter((RolePriv rpt) -> Arrays.asList(rpt.getMenuId()).contains(menu.getId())).collect(Collectors.toList());
                    boolean checked = child_count.size() > 0 ? true : false;
                    tmp.put("checked", checked);
                }
                List<Map> children = createTree(privs, menus, (int) tmp.get("menu_id"));
                tmp.put("children", children);
                treeMenu.add(tmp);
            }
        }
        return treeMenu;
    }

    // 修改权限
    @Transactional
    public Object postPriv(RoleRequest roleRequest) throws Exception {
        // 先检查部门下有没有用户
        QueryWrapper<RolePriv> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleRequest.detailId);
        // 删除旧的
        roleprivService.remove(queryWrapper);
        // 将查出来的数据组成数组插入到role_privs表里
        List<Integer> menuids = roleRequest.menuId;
        Integer role_id = roleRequest.detailId;
        if (menuids.size() > 0) {
            QueryWrapper<Menu> listWrapper = new QueryWrapper<>();
            listWrapper.in("id", menuids);
            List<Menu> all = menuService.list(listWrapper);
            List<RolePriv> inserts = new ArrayList<>();
            for (Menu menu : all) {
                RolePriv tmp = new RolePriv();
                tmp.setRoleId(role_id);
                tmp.setMenuId(menu.getId());
                tmp.setName(menu.getName());
                tmp.setUrl(menu.getUrl());
                tmp.setLabel(menu.getLabel());
                inserts.add(tmp);
            }
            roleprivService.saveBatch(inserts);
        }
        return ResultUtil.success("success");
    }

    // 获取所有用户
    public Object getAdmins(RoleRequest role) throws Exception {
        // 先检查角色权限
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", role.detailId).select("admin_id");
        List<Object> adminIds = roleAdminService.listObjs(queryWrapper);
        QueryWrapper<Admin> adminWrapper = new QueryWrapper<>();
        List<Admin> list = new ArrayList<>();
        if (adminIds.size() > 0) {
            adminWrapper.in("id", adminIds);
            list = adminService.list(adminWrapper);
        }
        return list;
    }

    // 移除用户
    public Object postRemoveAdmin(RoleRequest roleRequest) throws Exception {
        // 先检查角色权限
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleRequest.detailId).eq("admin_id", roleRequest.adminId);
        Boolean result = roleAdminService.remove(queryWrapper);
        return result;
    }
}
