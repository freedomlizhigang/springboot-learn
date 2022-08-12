package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.LinkTypes;
import com.coins.cms.mapper.LinkTypesMapper;
import com.coins.cms.request.LinkTypeRequest;
import com.coins.cms.service.LinkTypesService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (LinkTypes)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class LinkTypesServiceImpl extends ServiceImpl<LinkTypesMapper, LinkTypes> implements LinkTypesService {
    // 获取列表
    public Map<String, Object> getList(LinkTypeRequest request) {
        QueryWrapper<LinkTypes> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(request.key != "", "name", request.key).eq("del_flag",0);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + request.offset + "," + request.pageSize).orderByDesc("id");
        List<LinkTypes> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
}

