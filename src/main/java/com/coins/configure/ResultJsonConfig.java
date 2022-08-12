package com.coins.configure;

import com.coins.utils.CoinLogUtils;
import com.coins.utils.CommonResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * 统一封装返回值,有问题的直接返回，没问题的封装一次
 */

@Configuration
public class ResultJsonConfig {
    @RestControllerAdvice({"com.coins.home.controller", "com.coins.cms.controller", "com.coins.ums.controller"})
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {

        // 判断是否处理返回
        private boolean isResult(MethodParameter methodParameter, Class aClass) {
            return true;
        }

        //先执行supports判断是否拦截
        @Override
        public boolean supports(MethodParameter methodParameter, Class aClass) {
            /*CoinLogUtils.info("aClass.toString()---------------");
            CoinLogUtils.info(aClass.toString());
            CoinLogUtils.info(methodParameter.toString());*/
            return isResult(methodParameter, aClass);
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (body instanceof CommonResult) {
                return body;
            }
            // 纯字符串需要处理
            else if (body instanceof String) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    return objectMapper.writeValueAsString(new CommonResult<Object>(body));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            //responseObject是否为null
            else if (null == body) {
                return new CommonResult<>("null");
            }
            //该方法返回值类型是否是void
            else if (methodParameter.getMethod().getReturnType().isAssignableFrom(Void.TYPE)) {
                return new CommonResult<>("");
            }
            //该方法返回的媒体类型是否是application/json。若不是，直接返回响应内容
            else if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
                CoinLogUtils.info("不是json " + body);
                return body;
            }
            CommonResult<Object> commonResult = new CommonResult<Object>(body);
            return commonResult;
        }
    }
}
