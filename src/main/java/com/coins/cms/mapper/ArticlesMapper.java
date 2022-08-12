package com.coins.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coins.cms.entity.Articles;
import com.coins.cms.request.ArticleRequest;

import java.util.List;
import java.util.Map;

public interface ArticlesMapper extends BaseMapper<Articles> {
    // 管理中心文章列表
    public List<Map> getList(ArticleRequest request);
}

