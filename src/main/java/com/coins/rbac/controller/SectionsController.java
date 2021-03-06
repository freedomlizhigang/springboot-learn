package com.coins.rbac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coins.rbac.entity.Sections;
import com.coins.rbac.request.SectionListRequest;
import com.coins.rbac.service.lmpl.SectionsServiceImpl;
import com.coins.utils.ResultUtil;

/**
 * <p>
 *  用户部门管理
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
	public Object getAll(SectionListRequest sectionList) {
		return sectionImpl.getList(sectionList);
	}
	
	@GetMapping("/detail")
	public Object getDetail(@Validated(SectionListRequest.showDetail.class) SectionListRequest section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.getDetail(section);
	}

	@PostMapping("/create")
	public Object postCreate(@Validated(Sections.createSection.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.create(section);
	}
	
	@PostMapping("/update")
	public Object postUpdate(@Validated(Sections.updateName.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.updatename(section);
	}
	
	@PostMapping("/updatestatus")
	public Object postUpdateStatus(@Validated(Sections.updateStatus.class) Sections section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.updatestatus(section);
	}
	
	@PostMapping("/remove")
	public Object postRemove(@Validated(SectionListRequest.showDetail.class) SectionListRequest section, BindingResult result) {
		if (result.hasErrors()) {
			return ResultUtil.error(400, result.getAllErrors().get(0).getDefaultMessage());
		}
		return sectionImpl.removeSection(section);
	}
}

