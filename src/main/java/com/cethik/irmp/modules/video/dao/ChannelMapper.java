package com.cethik.irmp.modules.video.dao;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.dao.BaseMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper extends BaseMapper<ChannelEntity> {
    public ChannelEntity getByChannelCode(Long id);

    int updateChannelStatus(Query query);
}
