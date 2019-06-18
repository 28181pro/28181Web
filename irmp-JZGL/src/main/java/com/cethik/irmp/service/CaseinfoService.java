/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.mapper.CaseinfoMapper;
import com.cethik.irmp.model.Caseinfo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Caseinfo service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Service
@Transactional
public class CaseinfoService extends BaseService {
    private final static Logger log = LoggerFactory.getLogger(CaseinfoService.class);

    @Autowired
    private CaseinfoMapper mapper;

    public void create(Caseinfo model) throws Exception {
        try {
            log.debug("create model: {}", model.getCasename());
            Integer newid = getTableSequence(model);
            model.setId(newid);
            mapper.create(model);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Caseinfo model) {
        log.debug("edit model: {}", model.getCasename());
        mapper.edit(model);
    }

    public Caseinfo detail(Integer id) {
        log.debug("detail model: {}", id);
        return mapper.detail(id);
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(String ids) {
        String[] arrID = ids.split(",");
        for (String id : arrID) {
            mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }
    @CetcLog(type= OPERATE.QUERY,info="查询部份的列表")
    public List<Caseinfo> selectAll() {
        return mapper.selectAll();
    }

    @CetcLog(type= OPERATE.QUERY,info="查询部份的列表2222")
    public List<Caseinfo> selectListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<Caseinfo> list = mapper.selectAll();
        return list;
    }
    @CetcLog(type= OPERATE.QUERY,info="查询所有的列表")
    public List QueryDictionDataList(){
        List<Caseinfo> list = mapper.selectAll();
        return list;
    }
}
