package com.dear.common.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseUtils {

    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 如果count < 1 抛出运行时异常 用于回滚事务 框架捕获异常
     *
     * @param count
     * @param message
     * @throws RuntimeException
     */
    public void isException(int count, String message) throws RuntimeException{

        if(count < 1) throw new RuntimeException(message);
    }
}
