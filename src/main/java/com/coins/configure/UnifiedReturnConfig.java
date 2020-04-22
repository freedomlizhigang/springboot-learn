package com.coins.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.coins.utils.CommonResult;

/*
 * 统一封装返回值,有问题的直接返回，没问题的封装一次
 */

@Configuration
public class UnifiedReturnConfig {
	@RestControllerAdvice("com.coins.controller")
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object>{
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (body instanceof CommonResult){
                return body;
            }
            return new CommonResult<Object>(200,"success",body);
        }
    }
}
