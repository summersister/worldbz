package com.richman.common.interceptor;

import com.richman.common.cache.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MapperAspect {

    @Autowired
    private RedisUtil cache;

    /**
     * 监控com.clone.mapper.*.*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.richman.mapper.*.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        long begin = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();

        long time = end - begin;

        if(time > 3000) {

            String s = pjp.getSignature().toString();

            log.info("调用Mapper方法：{}，执行耗时：{}毫秒", s, time);

            this.cache.hset("LONG_SQL_LIST", s, String.valueOf(time));
        }

        return obj;
    }
}