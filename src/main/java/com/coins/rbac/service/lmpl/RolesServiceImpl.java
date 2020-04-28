package com.coins.rbac.service.lmpl;

import com.coins.rbac.entity.Admins;
import com.coins.rbac.entity.Menus;
import com.coins.rbac.entity.RolePrivs;
import com.coins.rbac.entity.RoleUsers;
import com.coins.rbac.entity.Roles;
import com.coins.rbac.mapper.MenusMapper;
import com.coins.rbac.mapper.RolePrivsMapper;
import com.coins.rbac.mapper.RoleUsersMapper;
import com.coins.rbac.mapper.RolesMapper;
import com.coins.rbac.request.RoleListRequest;
import com.coins.rbac.service.IRolePrivsService;
import com.coins.rbac.service.IRolesService;
import com.coins.utils.CommonResult;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {
	@Autowired
	private RolesMapper roleMapper;
	@Autowired
	private RoleUsersMapper roleUserMapper;
	@Autowired
	private RolePrivsMapper rolePrivMapper;
	@Autowired
	private MenusMapper menuMapper;
	@Autowired
	private IRolePrivsService roleprivService;
	// 获取列表
	public Map<String, Object> getList(RoleListRequest roleList) {
		QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().like(roleList.key != null,Roles::getName, roleList.key).last("limit " + roleList.page * roleList.pageSize + "," + roleList.pageSize);
		List<Roles> all = roleMapper.selectList(queryWrapper);
		Integer count = roleMapper.selectCount(queryWrapper);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", all);
		return map;
	}
//	获取单条
	public Roles getDetail(RoleListRequest role) {
		QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",role.detailId);
		Roles detail = roleMapper.selectOne(queryWrapper);
		return detail;
	}
//	插入单条
	public Integer create(Roles role) {
		int detail = roleMapper.insert(role);
		return detail;
	}
//	更新单条名称
	public Integer updatename(Roles role) {
		int detail = roleMapper.updateById(role);
		return detail;
	}
//	更新单条状态
	public Integer updatestatus(Roles role) {
		int detail = roleMapper.updateById(role);
		return detail;
	}
	//	删除单条
	@Transactional
	public Object remove(RoleListRequest role) throws Exception {
		// 先检查部门下有没有用户
		QueryWrapper<RoleUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id",role.detailId);
		Integer hav = roleUserMapper.selectCount(queryWrapper);
		if (hav > 0) {
			// 手动回滚事物
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtil.error(403, "角色下有用户");
		}
		int res = roleMapper.deleteById(role.detailId);
		// 同时删除关联的权限信息
		QueryWrapper<RolePrivs> privWrapper = new QueryWrapper<>();
		privWrapper.eq("role_id",role.detailId);
		rolePrivMapper.delete(privWrapper);
		return res;
	}
	// 获取权限信息
	public Object getPriv(RoleListRequest role) throws Exception {
		// 先检查角色权限
		QueryWrapper<RolePrivs> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id",role.detailId).select("id","menu_id");
		List<RolePrivs> rp = rolePrivMapper.selectList(queryWrapper);
		List<Menus> all = menuMapper.selectList(null);
		// 转格式
		List<Map> treeMenu = createTree(rp, all,0);
		return treeMenu;
	}
	// 递归树形菜单
	private List<Map> createTree(List<RolePrivs> privs, List<Menus> menus,int pid) {
        List<Map> treeMenu = new ArrayList<>();
        for (Menus menu : menus) {
            if (pid == menu.getParentid()) {
                Map<String,Object> tmp = new HashMap();
                tmp.put("menu_id", menu.getId());
                tmp.put("title", menu.getName());
                tmp.put("expand", false);
                tmp.put("checked", false);
                // 所有子菜单都选中的时候，此菜单选中checked，部分选中时selected
                List<Object> child_count = new ArrayList<>();
                child_count = menus.stream().filter((Menus m) -> Arrays.asList(m.getParentid()).contains(menu.getId())).collect(Collectors.toList());
                // 没有子菜单的时候，判断他本身
                if(child_count.size() == 0){
                	child_count = privs.stream().filter((RolePrivs rpt) -> Arrays.asList(rpt.getMenuId()).contains(menu.getId())).collect(Collectors.toList());
                	boolean checked = child_count.size() > 0 ? true : false;
                	tmp.put("checked",checked);
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
	public Object postPriv(RoleListRequest role) throws Exception {
		// 先检查部门下有没有用户
		QueryWrapper<RolePrivs> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id",role.detailId);
		// 删除旧的
		rolePrivMapper.delete(queryWrapper);
		// 将查出来的数据组成数组插入到role_privs表里
		List<Integer> menuids = role.menuId;
		Integer role_id = role.detailId;
		if(menuids.size() > 0)
		{
			QueryWrapper<Menus> listWrapper = new QueryWrapper<>();
			listWrapper.in("id",menuids);
			List<Menus> all = menuMapper.selectList(listWrapper);
			List<RolePrivs> inserts = new ArrayList<>();
			for (Menus menu : all) {
				RolePrivs tmp = new RolePrivs();
				tmp.setRoleId(role_id);
				tmp.setMenuId(menu.getId());
				tmp.setUrl(menu.getUrl());
				tmp.setLabel(menu.getLabel());
				inserts.add(tmp);
//				rolePrivMapper.insert(tmp);
			}
			roleprivService.saveBatch(inserts);
		}
		return ResultUtil.success(200, "success");
	}
}
