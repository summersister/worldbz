//package com.dear.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//
////使用WebMvcConfigurerAdapter可以来扩展springmvc的功能
//@Configuration
////@EnableWebMvc 不要接管Springmvc
//public class MyMvcConfig extends WebMvcConfigurerAdapter {
//
//    //所有的WebMvcConfigurer组件都会一起起作用
//    //@Bean 将组件注册在容器中
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        WebMvcConfigurer adapter = new WebMvcConfigurerAdapter(){
//            public void addViewControllers(ViewControllerRegistry registry){
//                registry.addViewController("/").setViewName("index");
//                registry.addViewController("/index.html").setViewName("index");
//            }
//        };
//        return adapter;
//    }
//
//}