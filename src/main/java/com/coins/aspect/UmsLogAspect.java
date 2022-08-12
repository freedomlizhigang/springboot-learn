package com.coins.aspect;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.coins.ums.entity.Log;
import com.coins.ums.serviceimpl.LogServiceImpl;
import com.coins.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @description: UMS管理中心权限验证，日志
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/28
 **/
@Aspect
@Component
@Order(2)
public class UmsLogAspect {
    /**
     * 定义一个公共的方法，实现服用
     * 拦截*.Controller下面的所有方法
     * 拦截UserController下面的userList方法里的任何参数(..表示拦截任何参数)写法：@Before("execution(public * com.angelo.controller.UserController.userList(..))")
     */
    @Pointcut("execution(public * com.coins.ums.controller.*.*(..))")
    public void umsLog() {}
    // 开始前通知
    /*@Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("###############before");
    }*/

    // 结束后通知
    /*@After("umsLog()")
    public void doAfter() {
        System.out.println("###############after 2");
    }*/
     // 存入数据库
    @Autowired
    private LogServiceImpl logService;
    @AfterReturning(returning = "object", pointcut = "umsLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object object) throws JsonProcessingException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("--------AfterReturning");
        String method = request.getMethod();
        if (!"GET".equals(method)) {
            String actionName = request.getRequestURI();
            Object[] args = joinPoint.getArgs();
            Map<String, Object> params = new HashMap<String, Object>();
            //  params.put("ip", request.getRemoteAddr()); // 获取请求的ip地址
            String data = "";
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                if ("GET".equals(method)) {
                    data = request.getQueryString();
                }else{
                    for (Integer i = 0; i < args.length; i++) {
                        data += String.valueOf(args[i]);
                    }
                }
            }
            String userString = request.getParameter("coinUserMap");
            Map<String,Object> userMap = JsonUtil.str2Obj(userString,Map.class);
            // 直接存入数据库
            Log log = new Log();
            log.setMethod(method);
            if(userMap != null){
                log.setAdminId((Integer) userMap.get("id"));
                log.setUser((String) userMap.get("name"));
            }
            log.setUrl(String.valueOf(request.getRequestURL()));
            log.setActionName(actionName);
            log.setData(data);
            log.setResData(object.toString());
            log.setCreatedAt(LocalDateTime.now());
            logService.save(log);
        }
        // 消息队列
        // redisService.saveQueue("CLog-key", params.toString());
    }

    // 异常情况!
    @AfterThrowing(pointcut = "umsLog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("######## throwing!");
    }
}
