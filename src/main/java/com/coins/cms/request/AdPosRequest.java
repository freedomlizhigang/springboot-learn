package com.coins.cms.request;

import com.coins.utils.CommonRequest;
import lombok.Data;

/**
 * @description:
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/8/9
 **/
@Data
public class AdPosRequest extends CommonRequest {
    private static final long serialVersionUID = 1L;
	//	关键字
	public String key = "";
}
