/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Corp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * corp mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
@Mapper
public interface CorpMapper extends BaseMapper<Corp> {
    List<Corp> selectAll();
}
