package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.LinkTypes;
import com.coins.cms.request.LinkTypeRequest;
import com.coins.cms.service.LinkTypesService;
import com.coins.cms.serviceimpl.LinkTypesServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (LinkTypes)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:45
 */
@UmsApiRestController("/linktype")
public class LinkTypesController {
    @Autowired
    private LinkTypesServiceImpl linkTypesServiceImpl;

	@Autowired
	private LinkTypesService linkTypesService;

    @GetMapping("/list")
    public Object getAll(LinkTypeRequest request) {
        return linkTypesServiceImpl.getList(request);
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(LinkTypeRequest.showDetail.class) LinkTypeRequest request) {
        return linkTypesService.getById(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(LinkTypes.create.class) LinkTypes request) {
        return linkTypesService.save(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(LinkTypes.update.class) LinkTypes request) {
        UpdateWrapper<LinkTypes> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("name",request.getName());
        return linkTypesService.update(updateWrapper);
    }

    @PostMapping("/remove")
    @Transactional
    public Object postRemove(@Validated(CommonRequest.removeMultiple.class) CommonRequest request) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", request.detailIds);
        updateWrapper.set("del_flag", 1);
        return linkTypesService.update(updateWrapper);
    }
}

