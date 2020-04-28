package com.coins.rbac.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.coins.rbac.entity.Sections;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SectionListRequest extends Sections {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	查询用的ID
	@NotNull(groups = showDetail.class,message = "ID必填")
	@Min(value = 1,groups = showDetail.class,message = "ID不能小于1")
	public Integer detailId;
	//	分页大小
	public Integer pageSize = 10;
	//	页码
	public Integer page = 0;
	//	关键字
	public String key;
	
	public interface showDetail{
    }
}
