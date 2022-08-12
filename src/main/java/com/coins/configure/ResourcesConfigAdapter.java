package com.coins.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description: 静态资源映射
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/30
 **/
@Configuration
public class ResourcesConfigAdapter extends WebMvcConfigurerAdapter {
    @Value("${upload.dir}")
    private String resourceDir;

    @Value("${upload.urlpath}")
    private String resourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations("file:"+resourceDir);
        super.addResourceHandlers(registry);
    }
}
