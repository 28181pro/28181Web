package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.video.dao.ChannelMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.manager.ChannelManager;
import com.cethik.irmp.modules.video.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
    private final static Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private ChannelManager channelManager;

    @Override
    public Page<ChannelEntity> listChannel( Map<String, Object> params){
        Query form = new Query(params);
        Page<ChannelEntity> page = new Page<>(form);
        try {
            channelManager.listChannel(page, form);
        }catch ( Exception ex){
            ex.printStackTrace();
        }
        return page;
    }

    @Override
    public ChannelEntity getByChannelCode(String channelcode) {
        return channelManager.getByChannelCode(channelcode);
    }
/*
    @CetcLog(type= OPERATE.QUERY,info="查询部份的列表2222")
    public Channel selectByCode(String channelcode) {

        return mapper.selectByCode(channelcode);
    }

 */

}
