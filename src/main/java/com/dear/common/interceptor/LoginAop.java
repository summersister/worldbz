package com.dear.common.interceptor;

import com.dear.common.annotaion.Login;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LoginAop {

    @Pointcut("@annotation(com.dear.common.annotaion.Login)")
    public void Authorization() {

    }

    @Around("Authorization() && @annotation(annotation)")
    public Object mtAround(ProceedingJoinPoint pjp, Login annotation) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        MethodSignature pjpMethod = (MethodSignature) pjp.getSignature();

        // 获取访问的接口
        Method method = pjpMethod.getMethod();

        // 如果需要则鉴权
        if (method.isAnnotationPresent(Login.class)) {

            //获取用户鉴权信息
            Object authorization = request.getAttribute("Authorization");

            if(authorization != null) {

                String s = (String) authorization;

                if(s.equals("964bef1455764ac7a3766bde5ef35e60")) {

                    //在过滤器中已经鉴权成功 放行请求
                    return pjp.proceed();
                }
            }

            ResultJson resultJson = new ResultJson();

            resultJson.setCode(ResultCode.NOPERM.getCode());

            return resultJson;
        }

        return pjp.proceed();
    }
}
