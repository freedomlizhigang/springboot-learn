package com.coins.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coins.cms.entity.Ads;
import com.coins.cms.request.AdRequest;

import java.util.List;
import java.util.Map;

public interface AdsMapper extends BaseMapper<Ads> {
    // 取列表
    public List<Map> getListAndPos(AdRequest request);
}

