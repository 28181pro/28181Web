/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */
package com.cethik.irmp.aspect;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.util.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * DeleteModelAdvice
 *
 * @author zhangshuhua@cetiti.com
 * @date 2018-5-15
 */

@Component
@Aspect
public class LogModelAdvice {

    private final String operationType = "查询";
    private final static Logger log  = LoggerFactory.getLogger(LogModelAdvice.class);


    @Value("${operation.log.enable}")
    private boolean logBusinessEnable;


    @Pointcut("execution(* com.cethik.irmp..*(..))")
    public void operationLog() {
        log.info("====query");
    }

    @AfterReturning("operationLog() && @annotation(rl)")
    public void doAfterReturning(JoinPoint jp, CetcLog rl) {
        if (!logBusinessEnable) {
            return;
        }
        if (jp.getArgs().length > 0) {
            log.info(rl.type()+","+rl.info()+"     "+ShiroUtils.getUser()+"   "+ShiroUtils.getUserId()+"    "+ShiroUtils.getUsername());
            // 删除成功
        }
    }

    @AfterThrowing(pointcut = "operationLog()", throwing = "e")
    public void handle(JoinPoint point, Throwable e) {
        log.info("====query failed");
        //删除失败
    }
}
