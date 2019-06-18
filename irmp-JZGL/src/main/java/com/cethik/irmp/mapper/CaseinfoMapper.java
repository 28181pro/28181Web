package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Caseinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * caseinfo mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
@Mapper
public interface CaseinfoMapper extends BaseMapper<Caseinfo> {
    List<Caseinfo> selectAll();

    List QueryDictionDataList();
}