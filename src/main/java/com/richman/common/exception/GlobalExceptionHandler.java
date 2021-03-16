package com.richman.common.exception;

import com.alibaba.fastjson.JSON;
import com.richman.common.bean.ExceptionLog;
import com.richman.common.bean.ResultCode;
import com.richman.common.bean.ResultJson;
import com.richman.common.cache.redis.RedisUtil;
import com.richman.common.cache.redis.config.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private RedisUtil cache;

    /**
     * 切面拦截所有异常 返回状态码1 记录异常到redis中
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultJson handleException(HttpServletRequest request, Exception e) {

        e.printStackTrace();

        String param = JSON.toJSONString(request.getParameterMap());

        String url = request.getRequestURI();

        String message = e.getMessage();

        String cause = e.getCause() == null ? "" : e.getCause().toString();

        String stack = JSON.toJSONString(e.getStackTrace());

        long time = System.currentTimeMillis();

        ExceptionLog exceptionLog = new ExceptionLog(param, url, message, cause, stack, time);

        this.cache.setex("ERROR_TEST" + time, JSON.toJSONString(exceptionLog), 3600);

        return new ResultJson(ResultCode.ERROR.getCode(), null);

    }


}
