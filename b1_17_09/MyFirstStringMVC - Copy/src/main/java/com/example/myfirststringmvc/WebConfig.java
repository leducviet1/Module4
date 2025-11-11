package com.example.myfirststringmvc;

import com.example.myfirststringmvc.services.DBStudentService;
import com.example.myfirststringmvc.services.IStudentService;
import com.example.myfirststringmvc.services.StudentService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages ="com.example.myfirststringmvc" )
public class WebConfig implements WebMvcConfigurer {
    //bean dành cho viewresolver
//    @Bean
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//
//        //đường dẫn đến view ở đâu
//        viewResolver.setPrefix("/WEB-INF/views/");
//
//        //đuôi file view laf gì (jsp)
//        viewResolver.setSuffix(".jsp");
//
//        //font hiển thị tiếng Việt
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        return viewResolver;
//    }
//    @Bean //tự động khởi tạo khi ứng dụng Spring khởi động --> tạo 1 bean trong spring ioc container
//    public IStudentService getStudentService(){
//        return new StudentService();
//    }
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/firstspringmvc");
//        ds.setUsername("root");
//        ds.setPassword("Ldviet04@");
//        return ds;
//    }

}
