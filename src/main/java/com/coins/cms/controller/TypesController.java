package com.coins.cms.controller;


import com.coins.cms.entity.Types;
import com.coins.cms.service.TypesService;
import com.coins.cms.serviceimpl.TypesServiceImpl;
import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CommonRequest;
import com.coins.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (Types)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:45
 */
@UmsApiRestController("/type")
public class TypesController {
    @Autowired
    private TypesServiceImpl typesServiceImpl;
    @Autowired
    private TypesService typesService;

    @GetMapping("/list")
    public Object getList() {
        return typesServiceImpl.tree();
    }
    @GetMapping("/detail")
	public Object getDetail(@Validated(CommonRequest.showDetail.class) CommonRequest request) {
		return typesService.getById(request.detailId);
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(Types.createType.class) Types type) {
		return typesService.save(type);
	}

	@PostMapping("/update")
	public Object postUpdate(@Validated(Types.updateType.class) Types type) {
		if (type.getId() == type.getParentId()){
			return ResultUtil.validator(403, "父级不能选择自己");
		}
		return typesService.updateById(type);
	}

	@PostMapping("/sort")
	public Object postSort(@Validated(Types.sortType.class) Types type) {
		return typesServiceImpl.sort(type);
	}
    @PostMapping("/remove")
	public Object postRemove(@Validated(CommonRequest.showDetail.class) CommonRequest request) {
		return typesServiceImpl.remove(request);
	}
}

