package com.coins.utils;

import lombok.Data;

/**
 * @description: TODO 类描述 
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/15
 **/
@Data
public class CommonResult<T> {
    /** 错误码. */
    private Integer code = 200;

    /** 提示信息. */
    private String message = "success";

    /** 具体的内容. */
    private Object result;

    public CommonResult(Integer code,String msg) {
        this.code = code;
        this.message = msg;
    }
    public CommonResult(Integer code,Object result) {
        this.code = code;
        this.message = "服务器忙";
        this.result = result;
    }
    public CommonResult(Object data) {
        this.result = data;
    }
}
