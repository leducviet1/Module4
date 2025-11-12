package com.example.casestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDir = Path.of(System.getProperty("user.dir"),"uploadCaseStudy").toString();
        //hãy chuyển toới thư mục upload để lấy hình ảnh/file tĩnh
        registry.addResourceHandler("/uploadCaseStudy/**") //bất kì url nào bắt đầu bang chữ "uploads"
                .addResourceLocations("file:"+uploadDir+"/");
    }
}
