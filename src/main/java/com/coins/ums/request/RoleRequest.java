package com.coins.ums.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

import com.coins.utils.CommonRequest;
import lombok.Data;

@Data
public class RoleRequest extends CommonRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	关键字
	public String key = "";
	// 权限数组
	public List<Integer> menuId;

	@NotNull(groups = {removeDetail.class},message = "用户ID必填")
	@Min(value = 1,groups = {removeDetail.class},message = "ID不能小于1")
	public Integer adminId;

}
