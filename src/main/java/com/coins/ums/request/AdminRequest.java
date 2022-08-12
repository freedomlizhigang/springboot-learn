package com.coins.ums.request;

import com.coins.utils.CommonRequest;

import lombok.Data;

import java.util.Map;

@Data
public class AdminRequest extends CommonRequest {
	private static final long serialVersionUID = 1L;

	//	关键字
	public String key = "";

	// 用户信息
	public Map<String,Object> coin_user;

}
