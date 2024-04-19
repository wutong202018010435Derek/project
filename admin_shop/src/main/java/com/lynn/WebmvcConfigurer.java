package com.lynn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;
import java.io.File;

@Configuration
public class WebmvcConfigurer implements WebMvcConfigurer {

    @Value("${spring.file.dir}")
    private String localFileDir;

    @Value("${spring.file.path}")
    private String localFilePath;

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {

        File file = new File(localFileDir);
        if (file.exists() && file.isFile()) {
            throw new RuntimeException("本地路径已被占用：" + localFileDir);
        }

        if (!file.exists()) {
            file.mkdirs();
        }

        registry.addResourceHandler(localFilePath + "/**").addResourceLocations("file:" + localFileDir);
    }
}

