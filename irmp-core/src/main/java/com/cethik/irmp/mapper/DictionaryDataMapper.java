/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.DictionaryData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DictionaryData mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
@Mapper
public interface DictionaryDataMapper extends BaseMapper<DictionaryData> {
    List<DictionaryData> selectAll();
}
