package com.cethik.irmp.modules.video.service;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.video.entity.ChannelEntity;

import java.util.Map;

public interface ChannelService {
    Page<ChannelEntity> listChannel(Map<String, Object> params);

    ChannelEntity getByChannelCode(String channelcode);
}
