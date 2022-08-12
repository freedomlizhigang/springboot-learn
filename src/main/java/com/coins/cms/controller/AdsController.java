package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.Ads;
import com.coins.cms.request.AdRequest;
import com.coins.cms.request.LinkTypeRequest;
import com.coins.cms.service.AdsService;
import com.coins.cms.serviceimpl.AdsServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (Ads)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:42
 */
@UmsApiRestController("/ad")
public class AdsController{
   @Autowired
    private AdsServiceImpl adsServiceImpl;
   @Autowired
    private AdsService adsService;
   @GetMapping("/list")
    public Object getAll(AdRequest request) {
        return adsServiceImpl.getList(request);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(AdRequest.showDetail.class) AdRequest request) {
        return adsService.getById(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(Ads.create.class) Ads request) {
        return adsService.save(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(Ads.update.class) Ads request) {
        UpdateWrapper<Ads> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("pos_id",request.getPosId());
        updateWrapper.set("title",request.getTitle());
        updateWrapper.set("thumb",request.getThumb());
        updateWrapper.set("url",request.getUrl());
        updateWrapper.set("starttime",request.getStarttime());
        updateWrapper.set("endtime",request.getEndtime());
        updateWrapper.set("status",request.getStatus());
        updateWrapper.set("sort",request.getSort());
        return adsService.update(updateWrapper);
    }

    @PostMapping("/remove")
    @Transactional
    public Object postRemove(@Validated(CommonRequest.removeMultiple.class) CommonRequest request) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", request.detailIds);
        updateWrapper.set("del_flag", 1);
        return adsService.update(updateWrapper);
    }

    @PostMapping("/sort")
    public Object postSort(@Validated(Ads.sort.class) Ads request) {
        UpdateWrapper<Ads> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("sort",request.getSort());
        return adsService.update(updateWrapper);
    }
}

