package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.base.vo.ChartsVO;
import com.cethik.irmp.modules.base.vo.MapVO;
import com.cethik.irmp.modules.base.vo.StatisticVO;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.entity.ServersEntity;
import com.cethik.irmp.modules.video.manager.ChannelManager;
import com.cethik.irmp.modules.video.manager.ServersManager;
import com.cethik.irmp.modules.video.service.ChannelService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ServersManager serversManager;

    @Override
    public Page<ChannelEntity> listChannel(Map<String, Object> params) {
        Query form = new Query(params);
        Page<ChannelEntity> page = new Page<>(form);
        channelManager.listChannel(page, form);
        for (ChannelEntity channelEntity : page.getRows()) {
            //根据StreamServerId获取流媒体服务器名称
            if (channelEntity.getStreamServerId() != null) {
                ServersEntity streamServerEntity = serversManager.getServersById(channelEntity.getStreamServerId().longValue());
                if (null != streamServerEntity) {
                    channelEntity.setStreamServerName(streamServerEntity.getName());
                }
            }
            //根据registerServerId获取注册服务器名称
            if (channelEntity.getRegisterServerId() != null) {
                ServersEntity registerServerEntity = serversManager.getServersById(channelEntity.getRegisterServerId().longValue());
                if (null != registerServerEntity) {
                    channelEntity.setRegisterServerName(registerServerEntity.getName());
                }
            }
        }
        return page;
    }

    @Override
    public R getByChannelCode(Long id) {

        ChannelEntity channel = channelManager.getByChannelCode(id);
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
        //根据StreamServerId获取流媒体服务器名称
        Integer strid =  channelEntity.getStreamServerId();
        String streamServerName = "";
        if(  strid != null )
             streamServerName = serversManager.getServersById(strid.longValue()).getName();

        //根据registerServerId获取注册服务器名称
        String registerServerName = "";
        Integer regid = channelEntity.getRegisterServerId();
        if(  regid != null )
            registerServerName = serversManager.getServersById(strid.longValue()).getName();

        channelEntity.setStreamServerName(streamServerName);
        channelEntity.setRegisterServerName(registerServerName);
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

    @Override
    public R getStatisticChannelStatus() {
        List<StatisticVO> statisticVOList = channelManager.getStatisticChannelStatus();
        List<ChartsVO> chartsVOList = new ArrayList<>();
        for (StatisticVO statisticVO : statisticVOList) {
            ChartsVO a = new ChartsVO(statisticVO.getNum(), StringUtils.equals(statisticVO.getName(), "1") ? "在线" : "离线");
            chartsVOList.add(a);
        }

        return CommonUtils.msgNotCheckNull(chartsVOList);
    }

    @Override
    public R getStatisticOnlineNumByGbdevices() {
        List<StatisticVO> statisticVOList = channelManager.getStatisticOnlineNumByGbdevices();
        List<ChartsVO> chartsVOList = new ArrayList<>();
        for (StatisticVO statisticVO : statisticVOList) {
            ChartsVO a = new ChartsVO(statisticVO.getNum(), statisticVO.getName());
            chartsVOList.add(a);
        }
        ChartsVO a2 = new ChartsVO(100, "台区A");
        ChartsVO a3 = new ChartsVO(10, "台区B");
        chartsVOList.add(a2);
        chartsVOList.add(a3);
        return CommonUtils.msgNotCheckNull(chartsVOList);

    }

    @Override
    public R listChannelQuery(Map<String, Object> params) {
        Query query = new Query(params);
        List<ChannelEntity> channelEntityList = channelManager.listChannel(query);
        List<MapVO> mapVOList = new ArrayList<>();
        if (!channelEntityList.isEmpty()) {
            for (ChannelEntity channelEntity : channelEntityList) {
                MapVO a = new MapVO(StringUtils.isBlank(channelEntity.getLongitude()) ? 0 : Double.valueOf(channelEntity.getLongitude()),
                        StringUtils.isBlank(channelEntity.getLatitude()) ? 0 : Double.valueOf(channelEntity.getLatitude()),
                        channelEntity.getName(), channelEntity.getIp(), channelEntity.getChannelCode());
                mapVOList.add(a);
            }
        }
        return CommonUtils.msgNotCheckNull(mapVOList);
    }


}
