package com.example.myfirststringmvc;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Appinit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //nơi khai báo beans dành cho services/data/...
        return new Class[]{};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() { //nơi khai báo beans dành cho web layer(controllers,view
        return new Class[]{WebConfig.class,ThymeleafConfig.class};
    }
    @Override
    protected String[] getServletMappings() {//đường dẫn gốc
        return new String[]{"/"};
    }

    //web.xml : tomcat đọc để cấu hình server

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // cấu hình thông tin file được upload
        //location (lưu vao thư mucj trên web server
        String location = System.getProperty("java.io.tmpdir"); //thư mục tạm của java
        //max file size
        long maxFileSize =5* 1024 * 1024; //1mb * 5
        //max request size
        long maxRequestSize =50* 1024 * 1024; //10 file
        //file size threshold ~ cached ???=0 ; ghi ra đĩa
        int fileSizeThreshold =0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,maxFileSize,maxRequestSize,fileSizeThreshold);
    registration.setMultipartConfig(multipartConfigElement);
    }
}
