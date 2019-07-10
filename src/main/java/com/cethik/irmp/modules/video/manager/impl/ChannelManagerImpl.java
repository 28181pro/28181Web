package com.cethik.irmp.modules.video.manager.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.video.dao.ChannelMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.manager.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("channelManager")
public class ChannelManagerImpl implements ChannelManager {

    @Autowired
    private ChannelMapper mapper;

    @Override
    public List<ChannelEntity> listChannel(Page<ChannelEntity> page, Query search) {
         return mapper.listForPage( page, search );
    }

    @Override
    public ChannelEntity getByChannelCode(String channelCode){

        return mapper.getByChannelCode( channelCode );
    }

}
