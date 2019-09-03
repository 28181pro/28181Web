package com.cethik.irmp.modules.video.controller;


import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.entity.ServersEntity;
import com.cethik.irmp.modules.video.service.ServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/video/servers")
public class ServersController extends AbstractController {
    @Autowired
    private ServersService serversService;



    @RequestMapping("/listpage")
    Page<ServersEntity> listpage(@RequestBody Map<String, Object> params) {
        try {
            Page<ServersEntity> pages = serversService.listServersPage(params);
            return pages;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new Page<ServersEntity>();
        }
    }
    /**
     * 服务器列表
     * @return
     */
    @RequestMapping("/list")
    public List<ServersEntity> list() {
        try {
            List<ServersEntity> serversEntityList=serversService.listServers();
            return serversEntityList;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ArrayList<>();
        }

    }


    /**
     * 服务器列表
     * @return
     */
    @RequestMapping("/select")
    public R select() {
        return serversService.selectServers();
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

    /**
     * 批量删除服务
     *
     * @param id
     * @return
     */
    @SysLog("删除服务")
    @RequestMapping("/remove")
    public R batchRemove(@RequestBody Long[] id) {
        try {
            return serversService.batchRemove(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }


}
