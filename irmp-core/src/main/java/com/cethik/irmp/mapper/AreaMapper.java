package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * area mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {
    List<Area> selectAll();
}
