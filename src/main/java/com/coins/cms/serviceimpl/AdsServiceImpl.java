package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Ads;
import com.coins.cms.mapper.AdsMapper;
import com.coins.cms.request.AdRequest;
import com.coins.cms.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Ads)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class AdsServiceImpl extends ServiceImpl<AdsMapper, Ads> implements AdsService {
    @Autowired
    private AdsMapper adsMapper;
    // 获取列表
    public Map<String, Object> getList(AdRequest request) {
        QueryWrapper<Ads> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(request.key != "", "title", request.key).eq("del_flag",0);
        Long count = this.count(queryWrapper);
//        queryWrapper.last("limit " + request.offset + "," + request.pageSize).orderByDesc("sort","id");
//        List<Ads> all = this.list(queryWrapper);
        List<Map> all = adsMapper.getListAndPos(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
}

