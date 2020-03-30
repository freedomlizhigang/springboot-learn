package com.coins.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .anyRequest().permitAll()       // 允许所有请求通过
        .and()
        .csrf()
        .disable()                      // 禁用 Spring Security 自带的跨域处理
        .sessionManagement()                        // 定制我们自己的 session 策略
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session
        //使用自定义的 Token过滤器 验证请求的Token是否合法
    	
        http.headers().cacheControl();
    }
}
