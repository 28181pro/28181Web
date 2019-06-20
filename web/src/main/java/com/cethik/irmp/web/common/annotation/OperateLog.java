package com.cethik.irmp.web.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @Auther daniel.yu
 * @Date 2018/9/27 10:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

	String value() default "";
}
