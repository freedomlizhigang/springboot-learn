package com.coins.aspect;

import com.coins.utils.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 过滤器
 *
 * @Author: 判断token，并把user数据放进请求中
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/28
 */
@WebFilter(filterName = "UmsFilter", urlPatterns = "/c-api/*")
public class UmsJwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Map m = new HashMap<String, String[]>(httpRequest.getParameterMap());
//        CoinLogUtils.info("---------------UmsFilter---------------");
        String actionName = httpRequest.getRequestURI();
        actionName = actionName.replace("/c-api/", "");
//        CoinLogUtils.info(actionName);
        String userId = null;
        if (actionName.equals("login") == false) {
            // 开始解析用户token
            String token = httpRequest.getHeader("Authorization");
            // 验证token有效性
            System.out.println("token ----------------------------");
            System.out.println(token);
            userId = JWTUtil.verify(token);
            if (userId.equals("false")) {
                returnJson(response,401,"登录已经失效，重新登录后再操作");
            }
            Map<String, Object> userMap = (Map<String, Object>) redisUtils.get("c-token:" + userId);
            System.out.println(userMap);
//            CoinLogUtils.info(userMap.toString());
            if (userMap == null) {
                returnJson(response,401,"登录已经失效，重新登录后再操作");
            }
            // 改造入参，给需要用户信息的追加用户信息
            List<Integer> roleIds = (List<Integer>) userMap.get("roleIds");
            List<String> urls = (List<String>) userMap.get("urls");
            // 不是创建者，没有角色 || 角色不是超级管理员同时没有url权限
            if(!userId.equalsIgnoreCase("1") && (roleIds == null || (roleIds.contains(1) == false && urls.contains(actionName) == false))){
                returnJson(response,402,"没有权限");
            }
            m.put("coinUserMap", JsonUtil.obj2Str(userMap));
        }
        CoinRequestWrapper parameterRequestWrapper = new CoinRequestWrapper((HttpServletRequest) request, m);
        //继续向后传递修改后的request,同时修改返回值的header token,拦截器不能实现。
        String newToken = null;
        try {
            newToken = JWTUtil.sign(userId, 7 * 24, "c-token");
        } catch (Exception e) {
            throw new RuntimeException("Token 生成失败，" + e.toString());
        }
        ((HttpServletResponse) response).setHeader("Authorization", newToken);
        chain.doFilter(parameterRequestWrapper, response);
    }

    @Override
    public void destroy() {
    }

    // 统一返回
    private void returnJson(ServletResponse response,Integer code,String message) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            Map<String,Object> result = new HashMap();
            result.put("code",code);
            result.put("message",message);
            result.put("result","");
            writer.print(JsonUtil.obj2Str(result));
        } catch (IOException e) {
            CoinLogUtils.error("拦截器输出流异常" + e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}