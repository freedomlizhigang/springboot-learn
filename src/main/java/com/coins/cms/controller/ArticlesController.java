package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.Articles;
import com.coins.cms.request.ArticleRequest;
import com.coins.cms.service.ArticlesService;
import com.coins.cms.serviceimpl.ArticlesServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (Articles)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:42
 */
@UmsApiRestController("/article")
public class ArticlesController{
   @Autowired
    private ArticlesService articlesService;
   @Autowired
    private ArticlesServiceImpl articlesServiceImpl;
   @GetMapping("/list")
    public Object getAll(ArticleRequest request) {
        return articlesServiceImpl.getList(request);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(ArticleRequest.showDetail.class) ArticleRequest request) {
        return articlesService.getById(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(Articles.create.class) Articles request) {
        return articlesService.save(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(Articles.update.class) Articles request) {
        request.setDelFlag(0);
        return articlesService.updateById(request);
    }

    @PostMapping("/remove")
    public Object postRemove(@Validated(CommonRequest.removeMultiple.class) CommonRequest request) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", request.detailIds);
        updateWrapper.set("del_flag", 1);
        return articlesService.update(updateWrapper);
    }

}

