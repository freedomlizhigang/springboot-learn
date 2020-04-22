package com.coins.utils;

public class ResultUtil {
	public static CommonResult<?> success(Integer code, String msg) {
		CommonResult<?> result = new CommonResult(code,msg);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
	
	public static CommonResult<Object> success(Integer code, String msg,Object object) {
		CommonResult<Object> result = new CommonResult<Object>(code,msg,object);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static CommonResult<?> error(Integer code, String msg) {
    	CommonResult<?> result = new CommonResult(500,"fail");
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
