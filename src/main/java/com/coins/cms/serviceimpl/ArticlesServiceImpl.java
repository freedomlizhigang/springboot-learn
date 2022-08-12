package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Articles;
import com.coins.cms.mapper.ArticlesMapper;
import com.coins.cms.request.ArticleRequest;
import com.coins.cms.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Articles)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {
    @Autowired
    private ArticlesMapper articlesMapper;
    // 获取列表
    public Map<String, Object> getList(ArticleRequest request) {
        QueryWrapper<Articles> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(request.key != "", "title", request.key).eq(request.cateid > 0,"cate_id",request.cateid).between(request.startAt != "" && request.endAt != "","publish_at",request.startAt,request.endAt).eq("del_flag", 0);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + request.offset + "," + request.pageSize).orderByDesc("sort", "id");
        List<Map> all = articlesMapper.getList(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
}

