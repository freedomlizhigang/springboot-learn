package com.coins.rbac.service.lmpl;

import com.coins.rbac.entity.Menus;
import com.coins.rbac.mapper.MenusMapper;
import com.coins.rbac.service.IMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus> implements IMenusService {
	@Autowired
	private MenusMapper menuMapper;
	public Object tree() {
		// 查出所有菜单来
		List<Menus> list = menuMapper.selectList(null);
		//树形结构数据生成
		List<Menus> result = createTree(0,list);
		return result;
	}
	// 递归树形菜单
	private List<Menus> createTree(int pid, List<Menus> menus) {
        List<Menus> treeMenu = new ArrayList<>();
        for (Menus menu : menus) {
            if (pid == menu.getParentid()) {
                treeMenu.add(menu);
                menu.setChildren(createTree(menu.getId(), menus));
            }
        }
        return treeMenu;
    }
	public Object select() {
		// 查出所有菜单来
		List<Menus> list = menuMapper.selectList(null);
		//树形结构数据生成
		List<Menus> tmp = new ArrayList<>();
		List<Object> result = new ArrayList<>();
		// 筛选条件
		List<Integer> ageList = Arrays.asList(0);
		tmp = list.stream().filter((Menus menu) -> ageList.contains(menu.getParentid())).collect(Collectors.toList());
		tmp.forEach(item->{
			Map<String,Object> tmpMap = new HashMap<>();
			tmpMap.put("label",item.getName());
			tmpMap.put("value",item.getId());
			result.add(tmpMap);
			// 二级
			List<Menus> tmp2 = new ArrayList<>();
			tmp2 = list.stream().filter((Menus menu) -> Arrays.asList(item.getId()).contains(menu.getParentid())).collect(Collectors.toList());
			tmp2.forEach(item2->{
				Map<String,Object> tmpMap2 = new HashMap<>();
				tmpMap2.put("label","|- " + item2.getName());
				tmpMap2.put("value",item2.getId());
				result.add(tmpMap2);
				// 三级
				List<Menus> tmp3 = new ArrayList<>();
				tmp3 = list.stream().filter((Menus menu) -> Arrays.asList(item2.getId()).contains(menu.getParentid())).collect(Collectors.toList());
				tmp3.forEach(item3->{
					Map<String,Object> tmpMap3 = new HashMap<>();
					tmpMap3.put("label","||- " + item3.getName());
					tmpMap3.put("value",item3.getId());
					result.add(tmpMap3);
				});
			});
		});
		return result;
	}
	// 新建
	public Menus detail(Menus menu) {
		Menus detail = menuMapper.selectById(menu.getId());
		return detail;
	}
	// 新建
	public Integer create(Menus menu) {
		int detail = menuMapper.insert(menu);
		return detail;
	}
	//	更新单条
	public Integer update(Menus menu) {
		int detail = menuMapper.updateById(menu);
		return detail;
	}
	//	删除单条,循环删除子菜单
	public Integer remove(Menus menu) {
		// 查所有菜单出来
		List<Menus> list = menuMapper.selectList(null);
		// 置空所有ids的存放空间
		List<Integer> treeMenu = new ArrayList<>();
		List<Integer> ids = getIds(menu.getId(),list,treeMenu);
		ids.add(menu.getId());
		Integer res = menuMapper.deleteBatchIds(ids);
		return res;
	}
	// 递归查所有子菜单
	private List<Integer> getIds(int pid, List<Menus> menus,List<Integer> treeMenu) {
        for (Menus menu : menus) {
            if (pid == menu.getParentid()) {
                treeMenu.add(menu.getId());
                getIds(menu.getId(), menus,treeMenu);
            }
        }
        return treeMenu;
    }
}
