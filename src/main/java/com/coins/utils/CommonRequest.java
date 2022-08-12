package com.coins.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @description: 封装一下公用的请求参数，主要是分页用
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommonRequest {
	private static final long serialVersionUID = 1L;
	//	分页大小
	public Integer pageSize = 10;
	//	页码
	public Integer page = 1;
	//	偏移量
	public Integer offset = 0;

	//	查询用的ID
    @NotNull(groups = {showDetail.class,removeDetail.class},message = "ID必填")
	@Min(value = 1,groups = {showDetail.class,removeDetail.class},message = "ID不能小于1")
	public Integer detailId;

    // 批量删除
	@NotNull(groups = {removeMultiple.class},message = "ID必填")
	public List<Integer> detailIds;

    //	详情
	public interface showDetail{}
	public interface removeDetail{}
	public interface removeMultiple{}

	// 计算分页的起始位置
    public Integer getOffset() {
        return ((page == null || page < 1 ? 1 : page) - 1) * (pageSize == null ? 10 : pageSize);
    }
}
