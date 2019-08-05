package com.cethik.irmp.modules.video.dao;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.base.vo.ChannelStatusVO;
import com.cethik.irmp.modules.base.vo.StatisticVO;
import com.cethik.irmp.modules.sys.dao.BaseMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelMapper extends BaseMapper<ChannelEntity> {
    public ChannelEntity getByChannelCode(Long id);

    int updateChannelStatus(Query query);

    List<StatisticVO> getStatisticChannelStatus();

    List<StatisticVO> getStatisticOnlineNumByGbdevices();

}
