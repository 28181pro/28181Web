/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * the description of class or interface
 *
 * @author suzhaoyang@cetiti.com
 * @date 2018-08-31
 */
@Component
public class UserTask {
    private final static Logger log  = LoggerFactory.getLogger(UserTask.class);

    @Scheduled(cron = "${task.user.cron:0/10 * * * * ?}")
    public void selectUser() {
        log.info("start user task...............");
    }
}
