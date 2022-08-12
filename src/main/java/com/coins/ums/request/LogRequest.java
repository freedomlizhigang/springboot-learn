package com.coins.ums.request;

import com.coins.utils.CommonRequest;
import lombok.Data;

/**
 * @description: 日志请求方法
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/31
 **/
@Data
public class LogRequest extends CommonRequest {
	private static final long serialVersionUID = 1L;

	//	用户id
	public String adminId;
}
