package com.richman.common.interceptor;

import com.richman.common.annotaion.GlobalLock;
import com.richman.common.cache.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class GlobalLockAop {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.richman.common.annotaion.GlobalLock)")
    public void GlobalLock() {

    }

    @Around("GlobalLock() && @annotation(annotation)")
    public Object mtAround(ProceedingJoinPoint pjp, GlobalLock annotation) throws Throwable {

        String lock = annotation.value();

        String uuid = UUID.randomUUID().toString();

        try {

            if (StringUtils.isEmpty(lock)) {

                throw new RuntimeException("未设置全局锁变量");

            }

            int i = 5000;

            while (!"OK".equals(redisUtil.set(lock, uuid, "NX", "PX", 5000))) {

                if (i < 0) {

                    throw new RuntimeException("全局锁设置失败，请检查redis服务");

                }

                i = i - 100;

                Thread.sleep(100);

            }

            Object ret = pjp.proceed();

            return ret;

        }finally {

            if (uuid.equals(redisUtil.get(lock))) {

                redisUtil.del(lock);

            }

        }

    }
}
