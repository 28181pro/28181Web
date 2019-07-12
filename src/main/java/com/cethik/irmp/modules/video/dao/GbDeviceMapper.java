package com.cethik.irmp.modules.video.dao;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.dao.BaseMapper;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GbDeviceMapper extends BaseMapper<GbDeviceEntity> {
  //  int updateGbDeviceStatus(Query query);
}
