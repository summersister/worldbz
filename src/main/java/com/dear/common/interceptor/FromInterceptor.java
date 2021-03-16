package com.dear.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于扫描请求接口
 */
@Slf4j
@Component
public class FromInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String path = request.getServletPath();
        String from = request.getHeader("from");

        System.out.println("拦截器工作开始 调用接口路径 path=" + path + ", from=" + from + "  " +
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        return true;
    }
}