package com.cethik.irmp.annotition;

import com.cethik.irmp.aspect.OPERATE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface CetcLog {
    String info() default "默认日志";
    OPERATE type() default OPERATE.QUERY;
}
