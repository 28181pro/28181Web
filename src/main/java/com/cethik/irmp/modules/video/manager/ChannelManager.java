package com.cethik.irmp.modules.video.manager;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.video.entity.ChannelEntity;

import java.util.List;

public interface ChannelManager {
    public List<ChannelEntity> listChannel(Page<ChannelEntity> page, Query search);
    public ChannelEntity getByChannelCode(String channelcode);

}
