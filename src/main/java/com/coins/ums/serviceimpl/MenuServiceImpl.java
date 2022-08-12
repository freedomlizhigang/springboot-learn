package com.coins.ums.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.ums.entity.Menu;
import com.coins.ums.mapper.MenuMapper;
import com.coins.ums.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import com.coins.utils.JsonUtil;
import com.coins.utils.ResultUtil;
import com.coins.utils.TreeNodeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
	@Autowired
    private HttpServletRequest request;
	@Autowired
	private MenuMapper menuMapper;
	public Object tree() {
		// 查出所有菜单来
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("sort","id");
		List<Menu> list = this.list(queryWrapper);
		//树形结构数据生成
		List<Map> result = TreeNodeUtils.toTree(0, list.stream().collect(Collectors.toList()));
		return result;
	}

	public Object leftList() throws JsonProcessingException {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("display",1).orderByAsc("sort","id");
		List<Menu> list = this.list(queryWrapper);
		List<Menu> tmp = new ArrayList<>();
		List<Object> result = new ArrayList<>();
		String coinUser = request.getParameter("coinUserMap");
		System.out.println(coinUser);
        Map<String,Object> userInfo = JsonUtil.str2Obj(coinUser,Map.class);
		List<Integer> roleIds = (List<Integer>) userInfo.get("roleIds");
		List<String> urls = (List<String>) userInfo.get("urls");
		// 筛选条件
		List<Integer> ageList = Arrays.asList(0);
		// 需要验证权限
		tmp = list.stream().filter((Menu menu) -> ageList.contains(menu.getParentId()) && (roleIds.contains(1) || urls.contains(menu.getUrl()))).collect(Collectors.toList());
		tmp.forEach(item->{
			result.add(item);
		});
		// 二级
		result.forEach(item->{
			// 需要验证权限
			List<Menu> tmp2 = list.stream().filter((Menu menu) -> Arrays.asList(((Menu) item).getId()).contains(menu.getParentId()) && (roleIds.contains(1) || urls.contains(menu.getUrl()))).collect(Collectors.toList());
			((Menu) item).setChildren(tmp2);
		});
		// 三级
		result.forEach(item->{
			((Menu) item).getChildren().forEach(subItem -> {
				// 需要验证权限
				List<Menu> tmp3 = list.stream().filter((Menu menu) -> Arrays.asList(subItem.getId()).contains(menu.getParentId()) && (roleIds.contains(1) || urls.contains(menu.getUrl()))).collect(Collectors.toList());
				subItem.setChildren(tmp3);
			});
		});
		return result;
	}

	public Object breadCrumb(String url) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("url",url.substring(1));
		Menu result = this.getOne(queryWrapper);
		List<Menu> list = new ArrayList<>();
		if (result != null){
			list = menuMapper.getAllParent(result.getId());
		}
		return list;
	}
	public Object select() {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("sort","id").select("id","parent_id","name");
		List<Menu> list = this.list(queryWrapper);
		//树形结构数据生成
        List<Map> result = TreeNodeUtils.toSelect(0, list.stream().collect(Collectors.toList()));
		return result;
	}
	//	更新单条
	public Object update(Menu menu) {
		if (menu.getId() == menu.getParentId()){
			return ResultUtil.validator(403, "父级不能选择自己");
		}
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("id",menu.getId());
		updateWrapper.set("parent_id",menu.getParentId());
		updateWrapper.set("name",menu.getName());
		updateWrapper.set("url",menu.getUrl());
		updateWrapper.set("label",menu.getLabel());
		updateWrapper.set("icon",menu.getIcon());
		updateWrapper.set("display",menu.getDisplay());
		updateWrapper.set("sort",menu.getSort());
		Boolean result = this.update(new Menu(),updateWrapper);
		return result;
	}
	//	删除单条,循环删除子菜单
	public Boolean remove(Menu menu) {
		// 查所有菜单出来
		List<Menu> list = this.list(null);
		// 置空所有ids的存放空间
		List<Integer> ids = TreeNodeUtils.getIds(menu.getId(),list.stream().collect(Collectors.toList()));
		ids.add(menu.getId());
		Boolean res = this.removeByIds(ids);
		return res;
	}
	public Boolean sort(Menu menu){
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("id",menu.getId());
		updateWrapper.set("sort",menu.getSort());
		Boolean result = this.update(new Menu(),updateWrapper);
		return result;
	}
	// 递归查所有子菜单
	private List<Integer> getIds(int pid, List<Menu> menus, List<Integer> treeMenu) {
        for (Menu menu : menus) {
            if (pid == menu.getParentId()) {
                treeMenu.add(menu.getId());
                getIds(menu.getId(), menus,treeMenu);
            }
        }
        return treeMenu;
    }

	// 递归树形菜单
	private List<Menu> createTree(int pid, List<Menu> menus) {
        List<Menu> treeMenu = new ArrayList<>();
        for (Menu menu : menus) {
            if (pid == menu.getParentId()) {
				// '_showChildren' => true
                treeMenu.add(menu);
                menu.setChildren(createTree(menu.getId(), menus));
            }
        }
        return treeMenu;
    }
}
