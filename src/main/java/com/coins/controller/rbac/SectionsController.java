package com.coins.controller.rbac;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.rbac.Sections;
import com.coins.request.rbac.SectionListRequest;
import com.coins.service.lmpl.rbac.SectionsServiceImpl;
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
	private SectionsServiceImpl sectionImpl;
	
	@GetMapping("/list")
	public CommonResult getAll(SectionListRequest sectionList) {
		return sectionImpl.getList(sectionList);
	}
	
	@GetMapping("/detail")
	public CommonResult getDetail(@Validated(SectionListRequest.showDetail.class) SectionListRequest section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.getDetail(section);
	}

	@PostMapping("/create")
	public CommonResult postCreate(@Validated(Sections.createSection.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		sectionImpl.create(section);
		return ResultUtil.success(200, "success");
	}
	
	@PostMapping("/update")
	public CommonResult postUpdate(@Validated(Sections.updateName.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		sectionImpl.updatename(section);
		return ResultUtil.success(200, "success");
	}
	
	@PostMapping("/updatestatus")
	public CommonResult postUpdateStatus(@Validated(Sections.updateStatus.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		sectionImpl.updatestatus(section);
		return ResultUtil.success(200, "success");
	}
	
	@PostMapping("/remove")
	public CommonResult postRemove(@Validated(SectionListRequest.showDetail.class) SectionListRequest section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		sectionImpl.removeSection(section);
		return ResultUtil.success(200, "success");
	}
}

