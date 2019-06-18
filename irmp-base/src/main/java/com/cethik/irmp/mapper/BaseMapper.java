/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.Map;

/**
 * base mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-15
 */
@Mapper
public interface BaseMapper<T> {

    /**
     * 新增
     */
    void create(T model);

    /**
     * 修改
     */
    void edit(T model);

    /**
     * 查看明细
     */
    T detail(Integer id);

    /**
     * 删除，主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 调用存储过程，获取序列号
     * @param params={TABLE_NAME=表名,TABLE_PREFIX=前缀}
     * @return params={TABLE_SEQUENCE=序号}
     */
    @Select({ "call SP_GETTABLESEQUENCE(#{TABLE_NAME,mode=IN,jdbcType=VARCHAR},"
            + "#{TABLE_PREFIX,mode=IN,jdbcType=VARCHAR},"
            + "#{TABLE_SEQUENCE,mode=OUT,jdbcType=VARCHAR})" })
    @Options(statementType= StatementType.CALLABLE)
    Integer getTableSequence(Map<String, String> params);

    Integer getTableSequence(T model, String prefix);
}
