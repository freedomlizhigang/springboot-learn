package com.coins.test.utils;

import com.coins.utils.CommonResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/30
 **/
class ResultUtilTest {

    @Test
    void success() {
        String data = "fds";
        CommonResult<Object> result = new CommonResult(data);
        System.out.println(result);
    }
}