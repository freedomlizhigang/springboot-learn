package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.AdPos;
import com.coins.cms.mapper.AdPosMapper;
import com.coins.cms.request.AdPosRequest;
import com.coins.cms.service.AdPosService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (AdPos)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class AdPosServiceImpl extends ServiceImpl<AdPosMapper, AdPos> implements AdPosService {
    // 获取列表
    public Map<String, Object> getList(AdPosRequest request) {
        QueryWrapper<AdPos> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(request.key != "", "name", request.key).eq("del_flag",0);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + request.offset + "," + request.pageSize).orderByDesc("id");
        List<AdPos> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
}

