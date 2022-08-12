package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Articles;
import com.coins.cms.entity.CateRelation;
import com.coins.cms.entity.Categorys;
import com.coins.cms.mapper.CategorysMapper;
import com.coins.cms.service.ArticlesService;
import com.coins.cms.service.CateRelationService;
import com.coins.cms.service.CategorysService;
import com.coins.utils.CoinException;
import com.coins.utils.CommonRequest;
import com.coins.utils.TreeNodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Categorys)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class CategorysServiceImpl extends ServiceImpl<CategorysMapper, Categorys> implements CategorysService {
    @Autowired
    private CategorysMapper categorysMapper;
    @Autowired
    private CateRelationService cateRelationService;

    @Autowired
    private ArticlesService articlesService;

    // 获取所有
    public Object tree() {
        // 查出所有栏目来
        List<Map> list = categorysMapper.getAll();
        //树形结构数据生成
        List<Object> listObj = list.stream().collect(Collectors.toList());
        List<Map> result = TreeNodeUtils.toTree(0, listObj);
        return result;
    }

    public Object select() {
        // 查出所有栏目来
        List<Map> list = categorysMapper.getAll();
        //树形结构数据生成
        List<Map> result = TreeNodeUtils.toSelect(0, list.stream().collect(Collectors.toList()));
        return result;
    }

    // 查单条带父id
    public Object getDetail(int id) {
        return categorysMapper.getDetail(id);
    }

    @Transactional
    public Object create(Categorys request) {
        Integer parentId = request.getParentId();
        Boolean result = this.save(request);
        Integer id = request.getId();
        // 自引用关系
        this.updateRelation(id,parentId);
        return result;
    }

    @Transactional
    public Object update(Categorys request) {
        Integer parentId = request.getParentId();
        Integer id = request.getId();
        if (id == parentId){
            throw new CoinException(403, "不能选择自己为父级");
        }
        Boolean result = this.updateById(request);
        // 清除父栏目id
        QueryWrapper<CateRelation> relationQueryWrapper = new QueryWrapper<>();
        relationQueryWrapper.eq("descendant", id);
        cateRelationService.remove(relationQueryWrapper);
        // 自引用关系
        this.updateRelation(id,parentId);
        return result;
    }
    private void updateRelation(Integer id,Integer parentId){
        List<CateRelation> insert = new ArrayList<>();
        CateRelation parent1 = new CateRelation();
        parent1.setAncestor(id);
        parent1.setDescendant(id);
        parent1.setDistance(0);
        insert.add(parent1);
        if (parentId != 0) {
            QueryWrapper<CateRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("descendant", parentId);
            List<CateRelation> list = cateRelationService.list(queryWrapper);
            list.forEach(item -> {
                CateRelation tmp = new CateRelation();
                tmp.setAncestor(item.getAncestor());
                tmp.setDescendant(id);
                tmp.setDistance(item.getDistance() + 1);
                insert.add(tmp);
            });
        } else {
            CateRelation parent0 = new CateRelation();
            parent0.setAncestor(0);
            parent0.setDescendant(id);
            parent0.setDistance(1);
            insert.add(parent0);
        }
        // 插入级别关系
        cateRelationService.saveBatch(insert);
    }

    @Transactional
    public Object remove(CommonRequest request) {
        // 查所有子栏目id
        QueryWrapper<CateRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ancestor", request.detailId);
        List<CateRelation> list = cateRelationService.list(queryWrapper);
        List<Integer> ids = list.stream().map((CateRelation c) -> c.getDescendant()).collect(Collectors.toList());
        // 栏目下是否有文章
        QueryWrapper<Articles> articlesQueryWrapper = new QueryWrapper<>();
        articlesQueryWrapper.in("cate_id",ids).eq("del_flag",0);
        if (articlesService.count(articlesQueryWrapper) > 0){
            throw new CoinException(403, "栏目下有文章，不可删除");
        }
        // 删除栏目及关联关系
        Boolean result = this.removeByIds(ids);
        QueryWrapper<CateRelation> removeQueryWrapper = new QueryWrapper<>();
        removeQueryWrapper.in("descendant",ids);
        cateRelationService.remove(removeQueryWrapper);
        return result;
    }
}

