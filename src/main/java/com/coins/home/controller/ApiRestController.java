/*
 * @Author: lzg2021 854378082@qq.com
 * @Date: 2022-07-15 09:16:48
 * @LastEditors: lzg2021 854378082@qq.com
 * @LastEditTime: 2022-07-15 09:53:49
 * @FilePath: \jizhiguangfu\src\main\java\com\coins\home\controller\ApiRestController.java
 * @Description:
 */
package com.coins.home.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface ApiRestController {
    /**
     * Alias for {@link RequestMapping#name}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    /**
     * Alias for {@link RequestMapping#value}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    /**
     * Alias for {@link RequestMapping#path}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
