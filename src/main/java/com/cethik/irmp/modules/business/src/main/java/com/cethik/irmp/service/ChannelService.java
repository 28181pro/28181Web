package com.cethik.irmp.service;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.mapper.ChannelMapper;
import com.cethik.irmp.mapper.GBDeviceMapper;
import com.cethik.irmp.model.Channel;
import com.cethik.irmp.model.GBDevice;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChannelService extends BaseService  {
    private final static Logger log = LoggerFactory.getLogger(CaseinfoService.class);

    @Autowired
    private ChannelMapper mapper;

    @CetcLog(type= OPERATE.QUERY,info="查询部份的列表2222")
    public Channel selectByCode(String channelcode) {

        return mapper.selectByCode(channelcode);
    }

}
