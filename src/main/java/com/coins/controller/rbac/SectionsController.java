package com.coins.controller.rbac;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.rbac.Sections;
import com.coins.mapper.rbac.SectionsMapper;
import com.coins.request.rbac.SectionListRequest;
import com.coins.utils.CommonResult;
import com.coins.utils.ResultUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/sections")
public class SectionsController {
	@Autowired
	private SectionsMapper sectionMapper;
	
	@GetMapping("/all")
	public CommonResult getAll(SectionListRequest section) {
		List<Sections> all = sectionMapper.selectList(null);
		return ResultUtil.success(200, "success", all);
	}

	/*
	 * 分组验证 Validated(Sections.updateStatus.class)，对于没有标记分组的不起作用
	 */
	@GetMapping("/list")
	public CommonResult getList(@Validated(Sections.updateStatus.class) Sections section, BindingResult result,SectionListRequest sectionreq) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		List<Sections> all = sectionMapper.selectList(null);
		return ResultUtil.success(200, "success", all);
	}
}

