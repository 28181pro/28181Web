package com.cethik.irmp.modules.video.manager.impl;

import com.cethik.irmp.common.constant.SystemConstant;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.base.vo.ChannelStatusVO;
import com.cethik.irmp.modules.base.vo.StatisticVO;
import com.cethik.irmp.modules.video.dao.ChannelMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.manager.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 通道管理
 *
 * @author daniel.yu
 *
 * @Date 2019/7/10 20:20
 */
@Component("channelManager")
public class ChannelManagerImpl implements ChannelManager {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<ChannelEntity> listChannel(Page<ChannelEntity> page, Query search) {
         return channelMapper.listForPage(page, search);
    }

    @Override
    public ChannelEntity getByChannelCode(Long id){
        return channelMapper.getByChannelCode( id );
    }

    @Override
    public int saveChannel(ChannelEntity channelEntity) {
        return channelMapper.save(channelEntity);
    }

    @Override
    public ChannelEntity getChannelById(Long id) {
        return channelMapper.getObjectById(id);
    }

    @Override
    public int updateChannel(ChannelEntity channelEntity) {
        return channelMapper.update(channelEntity);
    }

    @Override
    public int batchRemove(Long[] id) {
        int count=channelMapper.batchRemove(id);
        return count;
    }

    @Override
    public int updateChannelEnable(Long[] id) {
        Query query = new Query();
        query.put("onlineStatus", SystemConstant.StatusType.ENABLE.getValue());
        query.put("id", id);
        int count = channelMapper.updateChannelStatus(query);
        return count;

    }

    @Override
    public int updateChannelDisable(Long[] id) {
        Query query = new Query();
        query.put("onlineStatus", SystemConstant.StatusType.DISABLE.getValue());
        query.put("id", id);
        int count = channelMapper.updateChannelStatus(query);
        return count;
    }

    @Override
    public List<StatisticVO> getStatisticChannelStatus() {
        return channelMapper.getStatisticChannelStatus();
    }

    @Override
    public List<StatisticVO> getStatisticOnlineNumByGbdevices() {
        return channelMapper.getStatisticOnlineNumByGbdevices();
    }

    @Override
    public List<ChannelEntity> listChannel(Query search) {
        return channelMapper.list(search);
    }
    @Override
    public List<ChannelEntity> listChannelbylocation(Long locationId) {
        return channelMapper.listChannelbylocation(locationId);
    }

}
