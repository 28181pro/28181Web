/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.SystemlogMapper;
import com.cethik.irmp.model.Systemlog;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Systemlog service
 *
 * @author zhongping@hikchina.com
 * @date 2018-12-05
 */
@Service
public class SystemlogService extends BaseService {
    private final static Logger log = LoggerFactory.getLogger(SystemlogService.class);

    @Autowired
    private SystemlogMapper mapper;

    public void create(Systemlog model) throws Exception {
        try {
            log.debug("create model: {},{}", model.getController(), model.getAction());
            Long newid = Long.valueOf(getTableSequence(model));
            model.setId(newid);
            mapper.create(model);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Systemlog model) {
        log.debug("edit model: {},{}", model.getController(), model.getAction());
        mapper.edit(model);
    }

    public Systemlog detail(Integer id) {
        log.debug("detail model: {}", id);
        return mapper.detail(id);
    }


    public List<Systemlog> selectAll() {
        return mapper.selectAll();
    }

    public List<Systemlog> selectListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<Systemlog> Systemlog = mapper.selectAll();
        return Systemlog;
    }
}
