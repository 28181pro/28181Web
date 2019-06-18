/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Base service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Service
public abstract class BaseService<T> {
    private final static Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private BaseMapper baseMapper;

    public Integer getTableSequence(String tableName, String prefix) throws Exception  {
        Map<String, String> params = new HashMap<String, String>(3);
        params.put("TABLE_NAME", tableName.toUpperCase());
        params.put("TABLE_PREFIX", prefix);
        baseMapper.getTableSequence(params);

        Integer sequence = Integer.parseInt(params.get("TABLE_SEQUENCE"));
        return sequence;
    }

    public Integer getTableSequence(T model) throws Exception {
        String tableName = model.getClass().getSimpleName();
        return getTableSequence(tableName, "");
    }
}
