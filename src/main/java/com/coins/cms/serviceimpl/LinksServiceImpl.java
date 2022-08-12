package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Links;
import com.coins.cms.mapper.LinksMapper;
import com.coins.cms.request.LinkRequest;
import com.coins.cms.service.LinksService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Links)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class LinksServiceImpl extends ServiceImpl<LinksMapper, Links> implements LinksService {
    // 获取列表
    public Map<String, Object> getList(LinkRequest request) {
        QueryWrapper<Links> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(request.key != "", "title", request.key).eq("del_flag",0);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + request.offset + "," + request.pageSize).orderByDesc("sort","id");
        List<Links> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
}

