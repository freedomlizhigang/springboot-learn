package com.coins.handle;

import com.coins.utils.ResultEnum;

public class CoinException extends RuntimeException {
	/**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private Integer code;

    public CoinException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
