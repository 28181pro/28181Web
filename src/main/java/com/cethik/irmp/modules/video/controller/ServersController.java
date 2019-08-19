package com.cethik.irmp.modules.video.controller;


import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.entity.ServersEntity;
import com.cethik.irmp.modules.video.service.ServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/video/servers")
public class ServersController extends AbstractController {
    @Autowired
    private ServersService serversService;



    /**
     * 服务器列表
     * @return
     */
    @RequestMapping("/list")
    public List<ServersEntity> list() {
        return serversService.listServers();
    }


    /**
     * 新增服务器
     *
     * @param serversEntity
     * @return
     */
    @SysLog("新增服务器")
    @RequestMapping("/save")
    public R saveChannel(@RequestBody ServersEntity serversEntity) {
        try {
            return serversService.saveServers(serversEntity);
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
            return serversService.getServersById(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 修改服务器
     *
     * @param serversEntity
     * @return
     */
    @SysLog("修改服务器")
    @RequestMapping("/update")
    public R updateChannel(@RequestBody ServersEntity serversEntity) {
        try {
            return serversService.updateServers(serversEntity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }




}
