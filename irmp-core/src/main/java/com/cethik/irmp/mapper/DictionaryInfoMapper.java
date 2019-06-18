/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.DictionaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DictionaryInfo mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
@Mapper
public interface DictionaryInfoMapper extends BaseMapper<DictionaryInfo> {
    List<DictionaryInfo> selectAll();
    List<DictionaryInfo> selectByCode(String code);
}
