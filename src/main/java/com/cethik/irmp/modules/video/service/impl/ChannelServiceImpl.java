package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.manager.ChannelManager;
import com.cethik.irmp.modules.video.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通道管理服务
 *
 * @author daniel.yu
 * @Date 2019/7/10 20:20
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
    private final static Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private ChannelManager channelManager;

    @Override
    public Page<ChannelEntity> listChannel(Map<String, Object> params) {
        Query form = new Query(params);
        Page<ChannelEntity> page = new Page<>(form);
        channelManager.listChannel(page, form);
        return page;
    }

    @Override
    public R getByChannelCode(String channelCode) {

        ChannelEntity channel = channelManager.getByChannelCode(channelCode);
        return CommonUtils.msg(channel);
    }

    @Override
    public R saveChannel(ChannelEntity channelEntity) {
        int count = channelManager.saveChannel(channelEntity);
        return CommonUtils.msg(count);
    }

    @Override
    public R getChannelById(Long id) {
        ChannelEntity channelEntity = channelManager.getChannelById(id);
        return CommonUtils.msg(channelEntity);
    }

    @Override
    public R updateChannel(ChannelEntity channelEntity) {
        int count = channelManager.updateChannel(channelEntity);
        return CommonUtils.msg(count);
    }

    @Override
    public R batchRemove(Long[] id) {
        int count = channelManager.batchRemove(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public R updateChannelEnable(Long[] id) {
        int count = channelManager.updateChannelEnable(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public R updateChannelDisable(Long[] id) {
        int count = channelManager.updateChannelDisable(id);
        return CommonUtils.msg(id, count);
    }


}
