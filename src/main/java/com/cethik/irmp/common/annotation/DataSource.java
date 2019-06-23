package com.cethik.irmp.common.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * @author daniel.yu
 * @date 2019/6/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";

}
