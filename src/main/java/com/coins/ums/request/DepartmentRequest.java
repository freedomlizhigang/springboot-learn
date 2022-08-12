package com.coins.ums.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import com.coins.utils.CommonRequest;
import lombok.Data;

@Data
public class DepartmentRequest extends CommonRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	关键字
	public String key = "";

	@NotNull(groups = removeAdmin.class,message = "用户ID必填")
	@Min(value = 1,groups = {removeAdmin.class},message = "ID不能小于1")
	public Integer adminId;


	public interface  removeAdmin{}
}
