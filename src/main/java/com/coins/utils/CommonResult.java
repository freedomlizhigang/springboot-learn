package com.coins.utils;

import lombok.Data;

@Data
public class CommonResult<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private Object data;
    
    public CommonResult(Integer code,String msg) {
    	this.code = code;
    	this.msg = msg;
    }
    
    public CommonResult(Integer code,String msg,Object data) {
    	this.code = code;
    	this.msg = msg;
        this.data = data;
    }

	public void setMsg(String msg2) {
		// TODO Auto-generated method stub
		
	}

	public void setCode(Integer code2) {
		// TODO Auto-generated method stub
		
	}

	public void setData(Object data2) {
		// TODO Auto-generated method stub
		
	}

}
