package com.coins.utils;

/**
 * @description: 自定义异常，抛出问题
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/27
 **/
public class CoinException extends RuntimeException {
    private int code;

    private String errMsg;

    public  CoinException(Integer code,String msg){
        this.code = code;
        this.errMsg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
