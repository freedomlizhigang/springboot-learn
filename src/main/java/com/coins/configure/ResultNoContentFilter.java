package com.coins.configure;

import com.coins.utils.CoinLogUtils;
import com.coins.utils.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 过渡返回值为null或者void的控制器
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/31
 **/
@WebFilter(filterName = "NullFilter")
@Order(0)
public class ResultNoContentFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        if (httpServletResponse.getContentType() == null || httpServletResponse.getContentType().equals("")) {
            PrintWriter out = httpServletResponse.getWriter();
            CoinLogUtils.info("返回值为空的处理");
            httpServletResponse.setHeader("Authorization", "newToken");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            out.append(objectMapper.writeValueAsString(new CommonResult<Object>(403,"NoFound")));
        }
    }
}