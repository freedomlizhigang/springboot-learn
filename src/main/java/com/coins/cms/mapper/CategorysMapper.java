package com.coins.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coins.cms.entity.Categorys;

import java.util.List;
import java.util.Map;

public interface CategorysMapper extends BaseMapper<Categorys> {
    // 取所有栏目
    public List<Map> getAll();
    // 单条带父id
    public Categorys getDetail(int id);
}

