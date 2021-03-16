package com.richman.common.annotaion;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义多数据源事务注解
 * 负责多个数据源的事务统一处理问题
 * @author Neo
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleTransaction {

    String[] value() default {};

}
