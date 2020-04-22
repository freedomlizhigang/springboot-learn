package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.Sections;
import com.coins.mapper.rbac.SectionsMapper;
import com.coins.request.rbac.SectionListRequest;
import com.coins.service.rbac.ISectionsService;
import com.coins.utils.CommonResult;
import com.coins.utils.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class SectionsServiceImpl extends ServiceImpl<SectionsMapper, Sections> implements ISectionsService {
	@Autowired
	private SectionsMapper sectionMapper;
	// 获取列表
	public Map getList(SectionListRequest sectionlist) {
		QueryWrapper<Sections> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().like(sectionlist.key != null,Sections::getName, sectionlist.key).last("limit " + sectionlist.page * sectionlist.pageSize + "," + sectionlist.pageSize);
		List<Sections> all = sectionMapper.selectList(queryWrapper);
		Integer count = sectionMapper.selectCount(queryWrapper);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", all);
		return map;
	}
//	获取单条
	public Sections getDetail(SectionListRequest section) {
		QueryWrapper<Sections> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",section.detailId);
		Sections detail = sectionMapper.selectOne(queryWrapper);
		return detail;
	}
//	插入单条
	public Integer create(Sections section) {
		int detail = sectionMapper.insert(section);
		return detail;
	}
//	更新单条名称
	public Integer updatename(Sections section) {
		int detail = sectionMapper.updateById(section);
		return detail;
	}
//	更新单条状态
	public Integer updatestatus(Sections section) {
		int detail = sectionMapper.updateById(section);
		return detail;
	}
//	删除单条
	public Integer removeSection(SectionListRequest section) {
		int res = sectionMapper.deleteById(section.detailId);
		return res;
	}
}
