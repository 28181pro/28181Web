package com.cethik.irmp.modules.video.controller;


import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.constant.SystemConstant;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/video/channel")
public class ChannelController extends AbstractController {
    @Autowired
    ChannelService channelService;


    @RequestMapping("/list")
    Page<ChannelEntity> list(@RequestBody Map<String, Object> params) {
        try {
            if (getUserId() != SystemConstant.SUPER_ADMIN) {
                params.put("userIdCreate", getUserId());
            }
            Page<ChannelEntity> pages  = channelService.listChannel(params);
            return pages;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new Page<ChannelEntity>();
        }
    }

    /**
     * 根据id查询详情
     *
     * @param channelCode
     * @return
     */

    @RequestMapping("/getChannelInfo")
    public R getChannelInfo(@RequestBody String channelCode) {
        try {
            return channelService.getByChannelCode(channelCode);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }

    }


    /**
     * 新增通道
     *
     * @param channelEntity
     * @return
     */
    @SysLog("新增通道")
    @RequestMapping("/save")
    public R saveChannel(@RequestBody ChannelEntity channelEntity) {
        try {
            return channelService.saveChannel(channelEntity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/info")
    public R getChannelById(@RequestBody Long id) {
        try {
            return channelService.getChannelById(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 修改通道
     *
     * @param channelEntity
     * @return
     */
    @SysLog("修改通道")
    @RequestMapping("/update")
    public R updateChannel(@RequestBody ChannelEntity channelEntity) {
        try {
            return channelService.updateChannel(channelEntity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 批量删除通道
     *
     * @param id
     * @return
     */
    @SysLog("删除通道")
    @RequestMapping("/remove")
    public R batchRemove(@RequestBody Long[] id) {
        try {
            return channelService.batchRemove(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 启用通道
     * @param id
     * @return
     */
    @SysLog("启用通道")
    @RequestMapping("/enable")
    public R updateChannelEnable(@RequestBody Long[] id) {
        try {
            return channelService.updateChannelEnable(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 禁用通道
     * @param id
     * @return
     */
    @SysLog("禁用通道")
    @RequestMapping("/disable")
    public R updateChannelDisable(@RequestBody Long[] id) {
        try {
            return channelService.updateChannelDisable(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

}
