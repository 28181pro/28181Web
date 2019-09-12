package com.cethik.irmp.modules.video.service;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.video.entity.ChannelEntity;

import java.util.List;
import java.util.Map;

/**
 * 通道管理服务
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/7/10 20:20
 */
public interface ChannelService {
    Page<ChannelEntity> listChannel(Map<String, Object> params);

    R getByChannelCode(Long id);

    R saveChannel(ChannelEntity channelEntity);

    R getChannelById(Long id);

    R updateChannel(ChannelEntity channelEntity);

    R batchRemove(Long[] id);

    R updateChannelEnable(Long[] id);

    R updateChannelDisable(Long[] id);

    R  getStatisticChannelStatus();

    R  getStatisticOnlineNumByGbdevices();

    R  listChannelQuery(Map<String, Object> params);

    List<ChannelEntity> listChannelbylocation(Long id);
}
