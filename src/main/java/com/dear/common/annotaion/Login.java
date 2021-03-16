package com.dear.common.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定权限校验注解
 * 负责解析公钥 AccessKey
 * 从中解析出 项目方id
 * @author peter
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    String value() default "";

}
