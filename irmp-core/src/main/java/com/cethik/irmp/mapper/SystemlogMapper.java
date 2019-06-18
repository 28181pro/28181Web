package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Systemlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * systemlog mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */


@Mapper
public interface SystemlogMapper extends BaseMapper<Systemlog> {
    List<Systemlog> selectAll();
}