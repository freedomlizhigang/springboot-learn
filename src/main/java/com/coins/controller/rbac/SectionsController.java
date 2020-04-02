package com.coins.controller.rbac;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.rbac.Sections;
import com.coins.mapper.rbac.SectionsMapper;

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
	public List<Sections> getAll() {
		List<Sections> all = sectionMapper.selectList(null);
		return all;
	}
}

