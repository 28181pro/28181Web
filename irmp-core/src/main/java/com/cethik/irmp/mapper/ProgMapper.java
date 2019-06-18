/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Prog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * prog mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Mapper
public interface ProgMapper extends BaseMapper<Prog> {
    void sort(Prog model);
    List<Prog> selectAll();
    List<Prog> selectByRoleId(Integer roleId);
    List<Prog> selectByUserId(Integer userId);
}
