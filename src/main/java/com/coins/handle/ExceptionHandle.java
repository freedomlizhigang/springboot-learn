package com.coins.handle;

import com.coins.utils.CoinException;
import com.coins.utils.CommonResult;
import com.coins.utils.CoinLogUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coins.utils.ResultUtil;

/**
 * 捕获异常的类，返回信息给浏览器，可以自定义返回的code,msg等信息
 */

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult handle(Exception e) {
        CoinLogUtils.error("【系统异常】{}", e.toString());
        // 自定义的异常
        if (e instanceof CoinException) {
            return ResultUtil.validator(((CoinException) e).getCode(),((CoinException) e).getErrMsg());
        }else if (e instanceof BindException) {
            // 处理校验失败异常
            BindException eb = (BindException) e;
            String message = eb.getBindingResult().getFieldError().getDefaultMessage();
            return ResultUtil.validator(400,message);
        }else{
            return ResultUtil.error(500,e.toString());
        }
    }


}