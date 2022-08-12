package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.AdPos;
import com.coins.cms.request.AdPosRequest;
import com.coins.cms.service.AdPosService;
import com.coins.cms.serviceimpl.AdPosServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (AdPos)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:42
 */
@UmsApiRestController("/adpos")
public class AdPosController{
   @Autowired
    private AdPosServiceImpl adPosServiceImpl;

	@Autowired
	private AdPosService adPosService;

    @GetMapping("/list")
    public Object getAll(AdPosRequest request) {
        return adPosServiceImpl.getList(request);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(AdPosRequest.showDetail.class) AdPosRequest request) {
        return adPosService.getById(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(AdPos.create.class) AdPos request) {
        return adPosService.save(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(AdPos.update.class) AdPos request) {
        UpdateWrapper<AdPos> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("name",request.getName());
        updateWrapper.set("is_mobile",request.getIsMobile());
        return adPosService.update(updateWrapper);
    }

    @PostMapping("/remove")
    @Transactional
    public Object postRemove(@Validated(CommonRequest.removeMultiple.class) CommonRequest request) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", request.detailIds);
        updateWrapper.set("del_flag", 1);
        return adPosService.update(updateWrapper);
    }
}

