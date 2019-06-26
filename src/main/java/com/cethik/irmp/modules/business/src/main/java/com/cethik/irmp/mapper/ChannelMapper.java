package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Channel;
import com.cethik.irmp.model.GBDevice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper  extends BaseMapper<Channel>{

     Channel selectByCode(String channelcode);

}
