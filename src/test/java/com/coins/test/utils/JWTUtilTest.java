package com.coins.test.utils;

import com.coins.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @description: TODO 类描述
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/29
 **/
@SpringBootTest
class JWTUtilTest {

    @Test
    void sign() throws Exception {
        String token = JWTUtil.sign("1",7*24,"c-token");
        System.out.println(token);
//        token = "0F0666B250DD758824A91BEB7CDE8D56527D4548B020AD27BCE170896CB8981B";
        String tokenAsc = JWTUtil.verify(token);
        System.out.println(tokenAsc);
    }

    @Test
    void verify() {
    }
}