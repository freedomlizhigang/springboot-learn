package com.coins.cms.request;

import com.coins.utils.CommonRequest;
import lombok.Data;

import java.time.LocalDate;

/**
 * @description:
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/8/12
 **/
@Data
public class ArticleRequest extends CommonRequest {
    private static final long serialVersionUID = 1L;
    //	关键字
    public String key = "";
    //	栏目id
    public Integer cateid = 0;
	// 时间范围
	public String startAt = "";
	public String endAt = "";
}
