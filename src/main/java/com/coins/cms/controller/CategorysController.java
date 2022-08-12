package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.Categorys;
import com.coins.cms.service.CategorysService;
import com.coins.cms.serviceimpl.CategorysServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (Categorys)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:42
 */
@UmsApiRestController("category")
public class CategorysController {
    @Autowired
    private CategorysServiceImpl categorysServiceImpl;
    @Autowired
    private CategorysService categorysService;

    @GetMapping("/list")
    public Object getAll() {
        return categorysServiceImpl.tree();
    }

    @GetMapping("/select")
    public Object getSelect() {
        return categorysServiceImpl.select();
    }

    @GetMapping("/detail")
    public Object getDetail(@Validated(CommonRequest.showDetail.class) CommonRequest request) {
        return categorysServiceImpl.getDetail(request.getDetailId());
    }

    @PostMapping("/create")
    public Object postCreate(@Validated(Categorys.create.class) Categorys request) {
        return categorysServiceImpl.create(request);
    }

    @PostMapping("/update")
    public Object postUpdate(@Validated(Categorys.update.class) Categorys request) {
        return categorysServiceImpl.update(request);
    }

    @PostMapping("/remove")
    public Object postRemove(@Validated(CommonRequest.showDetail.class) CommonRequest request) {
        return categorysServiceImpl.remove(request);
    }

    @PostMapping("/sort")
    public Object postSort(@Validated(Categorys.sort.class) Categorys request) {
        UpdateWrapper<Categorys> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", request.getId());
        updateWrapper.set("sort", request.getSort());
        return categorysService.update(updateWrapper);
    }
}

