package com.dear.common.interceptor;

import com.dear.common.util.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HttpServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;

        //获取用户授权
        String auth = servletRequest.getHeader("Authorization");

        if (auth != null) {

            //解析用户id
            Claims claims = JwtHelper.parseJWT(auth);

            //是否解析成功
            if (claims != null && claims.get("userId") != null) {

                if(servletRequest.getServletPath().equals("/base/file/v1/upload")) {

                    //跳过body参数解析

                } else {

                    //将解析用户id放入body中
                    request = new ParameterRequestWrapper(servletRequest, claims.get("userId").toString());
                }

                //用于通知 需登录的扫描注解 用户已授权  约定加密串
                request.setAttribute("Authorization", "964bef1455764ac7a3766bde5ef35e60");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}