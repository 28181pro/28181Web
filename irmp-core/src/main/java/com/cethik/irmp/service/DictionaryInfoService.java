/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.DictionaryInfoMapper;
import com.cethik.irmp.model.DictionaryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DictionaryInfo service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Service
public class DictionaryInfoService extends BaseService {
    private final static Logger log = LoggerFactory.getLogger(DictionaryInfoService.class);

    @Autowired
    private DictionaryInfoMapper mapper;

    public List<DictionaryInfo> selectAll() {
        return mapper.selectAll();
    }

    public List<DictionaryInfo> selectByCode(String code) {
        return mapper.selectByCode(code);
    }
}
