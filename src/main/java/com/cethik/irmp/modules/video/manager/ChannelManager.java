package com.cethik.irmp.modules.video.manager;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.base.vo.ChannelStatusVO;
import com.cethik.irmp.modules.base.vo.StatisticVO;
import com.cethik.irmp.modules.video.entity.ChannelEntity;

import java.util.List;
/**
 * 通道管理
 *
 * @author daniel.yu
 *
 * @Date 2019/7/10 20:20
 */
public interface ChannelManager {
    public List<ChannelEntity> listChannel(Page<ChannelEntity> page, Query search);
    public ChannelEntity getByChannelCode(Long id);

    int saveChannel(ChannelEntity channelEntity);

    ChannelEntity getChannelById(Long id);

    int updateChannel(ChannelEntity channelEntity);

    int batchRemove(Long[] id);

    int updateChannelEnable(Long[] id);

    int updateChannelDisable(Long[] id);

    List<StatisticVO> getStatisticChannelStatus();

    List<StatisticVO> getStatisticOnlineNumByGbdevices();

    List<ChannelEntity> listChannel(Query search);

    List<ChannelEntity> listChannelbylocation(Long locationId);
}
