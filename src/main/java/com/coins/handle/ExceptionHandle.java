package com.coins.handle;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coins.utils.CommonResult;
import com.coins.utils.ResultUtil;

import ch.qos.logback.classic.Logger;

/**
 * 捕获异常的类，返回信息给浏览器，可以自定义返回的code,msg等信息
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult handle(Exception e) {
        if (e instanceof CoinException) {   //判断异常是否是我们定义的异常
        	CoinException coinException = (CoinException) e;
            return ResultUtil.error(coinException.getCode(), coinException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
//            return ResultUtil.error(-1, "未知错误");
            return ResultUtil.error(-1,e.toString());
        }
    }
}