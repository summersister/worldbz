package com.dear.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 测试环境适用拦截器  用于检测
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private FromInterceptor fromInterceptor;

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${fileSetting.savePath}")
    private String savePath;

    @Value("${fileSetting.accessPath}")
    private String accessPath;


    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //文件读取拦截修改
        registry.addResourceHandler(accessPath + "/**").addResourceLocations("file:" + savePath + "/");


        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        /*放行swagger*/
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        if(active.equalsIgnoreCase("dev")
                || active.equalsIgnoreCase("test")) {

            registry.addInterceptor(fromInterceptor).addPathPatterns("/**")
                    .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        }

        super.addInterceptors(registry);
    }
}