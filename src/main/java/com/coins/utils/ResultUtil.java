package com.coins.utils;

public class ResultUtil {
	public static CommonResult success(Integer code, String msg,Object object) {
		CommonResult result = new CommonResult(code,msg,object);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static CommonResult error(Integer code, String msg) {
    	CommonResult result = new CommonResult(500,"fail",null);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
