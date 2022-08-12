package com.coins.configure;

import com.coins.home.controller.ApiRestController;
import com.coins.ums.controller.UmsApiRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 请求路径添加统一前缀
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(ApiRestController.class))
                .addPathPrefix("/c-api/", c -> c.isAnnotationPresent(UmsApiRestController.class));
    }
}
