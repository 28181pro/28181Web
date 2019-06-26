package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Caseinfo;
import com.cethik.irmp.model.GBDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GBDeviceMapper extends BaseMapper<GBDevice> {

    List<GBDevice> selectAll();
    List QueryDictionDataList();

}
