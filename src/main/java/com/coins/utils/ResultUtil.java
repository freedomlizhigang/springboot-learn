package com.coins.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
	public static CommonResult<?> success(Object data) {
		CommonResult<?> result = new CommonResult(data);
        return result;
    }

    public static CommonResult validator(Integer code, String msg) {
    	CommonResult<?> result = new CommonResult(code,msg);
        return result;
    }

    public static CommonResult error(Integer code,String resultMsg){
        Object msg = resultMsg;
        CommonResult<?> result = new CommonResult(code,msg);
        return result;
    }
}
