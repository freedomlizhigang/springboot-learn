package com.coins.handle;

import com.coins.utils.ResultEnum;

public class CoinException extends RuntimeException {
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
