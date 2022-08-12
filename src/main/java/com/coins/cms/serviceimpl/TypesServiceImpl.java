package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Types;
import com.coins.cms.mapper.TypesMapper;
import com.coins.cms.service.TypesService;
import com.coins.utils.CommonRequest;
import com.coins.utils.TreeNodeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Types)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types> implements TypesService {

    // 获取所有
    public Object tree() {
        // 查出所有菜单来
        QueryWrapper<Types> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort","id");
        List<Types> list = this.list(queryWrapper);
        //树形结构数据生成
		List<Object> listObj = list.stream().collect(Collectors.toList());
		List<Map> result = TreeNodeUtils.toTree(0, listObj);
        return result;
    }

    //	排序
    public Object sort(Types type) {
        UpdateWrapper<Types> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",type.getId());
        updateWrapper.set("sort",type.getSort());
        Boolean result = this.update(updateWrapper);
        return result;
    }

    //	删除单条
    public Object remove(CommonRequest type) {
        // 查所有菜单出来
        List<Types> list = this.list(null);
        // 置空所有ids的存放空间
        List<Integer> ids = TreeNodeUtils.getIds(type.detailId,list.stream().collect(Collectors.toList()));
        ids.add(type.detailId);
        Boolean result = this.removeByIds(ids);
        return result;
    }
}

