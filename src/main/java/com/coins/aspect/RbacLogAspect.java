package com.coins.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RbacLogAspect {
	
	/**
     *  定义一个公共的方法，实现服用
     *  拦截*.Controller下面的所有方法
     *  拦截UserController下面的userList方法里的任何参数(..表示拦截任何参数)写法：@Before("execution(public * com.angelo.controller.UserController.userList(..))")
     */
    @Pointcut("execution(public * com.coins.*.controller.*.*(..))")
    public void log() {
    }
    // 取参数
    private Map<String, Object> getMap(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("url", request.getRequestURL()); // 获取请求的url
        params.put("method", request.getMethod()); // 获取请求的方式
        params.put("ip", request.getRemoteAddr()); // 获取请求的ip地址
        params.put("className", joinPoint.getSignature().getDeclaringTypeName()); // 获取类名
        params.put("classMethod", joinPoint.getSignature().getName()); // 获取类方法
        params.put("args", joinPoint.getArgs()); // 请求参数
        return params;
    }
    // 开始前通知
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("###############before");
//    	Map<String, Object> params = getMap(joinPoint);
//      System.out.println(params);
    }
    // 结束后通知
    @After("log()")
    public void doAfter() {
    	System.out.println("###############after");
    }
    /**
     * 获取响应返回值
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint,Object object) {
    	Map<String, Object> params = getMap(joinPoint);
    	System.out.println("###############AfterReturning");
    	System.out.println(params);
        System.out.println(object.toString());
    }
    // 异常情况!
    @AfterThrowing(pointcut = "log()")
    public void doAfterThrowing(JoinPoint joinPoint) {
    	Map<String, Object> params = getMap(joinPoint);
    	System.out.println("########异常情况!");
        System.out.println(params);
    }
}
