package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.Links;
import com.coins.cms.request.LinkRequest;
import com.coins.cms.request.LinkTypeRequest;
import com.coins.cms.service.LinksService;
import com.coins.cms.serviceimpl.LinksServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (Links)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:45
 */
@UmsApiRestController("/link")
public class LinksController{
   @Autowired
    private LinksService linksService;
   @Autowired
    private LinksServiceImpl linksServiceImpl;
   @GetMapping("/list")
    public Object getAll(LinkRequest request) {
        return linksServiceImpl.getList(request);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(LinkRequest.showDetail.class) LinkRequest request) {
        return linksService.getById(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(Links.create.class) Links request) {
        return linksService.save(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(Links.update.class) Links request) {
        return linksService.updateById(request);
    }

    @PostMapping("/remove")
    @Transactional
    public Object postRemove(@Validated(CommonRequest.removeMultiple.class) CommonRequest request) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", request.detailIds);
        updateWrapper.set("del_flag", 1);
        return linksService.update(updateWrapper);
    }

    @PostMapping("/sort")
    public Object postSort(@Validated(Links.sort.class) Links request) {
        UpdateWrapper<Links> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("sort",request.getSort());
        return linksService.update(updateWrapper);
    }
}

