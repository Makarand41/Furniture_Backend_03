package com.nanotech.furniture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ✅ Maps local "uploads" directory to URL path "/uploads/**"
        String uploadPath = Paths.get(System.getProperty("user.dir"), "uploads").toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath)
                .setCachePeriod(0); // disable caching for dev (shows latest image)
    }
}
