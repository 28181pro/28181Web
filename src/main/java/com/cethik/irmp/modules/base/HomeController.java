package com.cethik.irmp.modules.base;


import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.service.ChannelService;
import com.cethik.irmp.modules.video.service.GbDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/base/home")
public class HomeController extends AbstractController {
    @Autowired
    private ChannelService channelService;

    @Autowired
    private GbDeviceService gbDeviceService;


    @RequestMapping("/listChannelQuery")
    public R listChannelQuery() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("onlineStatus", 1);
            return channelService.listChannelQuery(params);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * 获取国际设备列表
     *
     * @return
     */
    @RequestMapping("/selectGbDevice")
    public R selectGbDevice() {
        try {
            return gbDeviceService.listGbDevice();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 获取统计渠道在线/离线状态数
     *
     * @return
     */
    @RequestMapping("/getStatisticChannelStatus")
    public R getStatisticChannelStatus() {
        try {
            return channelService.getStatisticChannelStatus();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 获取台区通道在线情况
     *
     * @return
     */
    @RequestMapping("/getStatisticOnlineNumByGbdevices")
    public R getStatisticOnlineNumByGbdevices() {
        try {
            return channelService.getStatisticOnlineNumByGbdevices();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

}
