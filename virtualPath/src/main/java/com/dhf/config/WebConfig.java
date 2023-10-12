package com.dhf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/7/9/0009 11:02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 虚拟路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 只能访问具体文件，不能查看文件夹
        // ex1: http://localhost:8080/path/02.jpg
        // ex2: http://localhost:8080/path/java/README.md
        registry.addResourceHandler("/path/**")
                .addResourceLocations("file:F:/");
    }
}
