package com.example.myfirststringmvc;

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
}
