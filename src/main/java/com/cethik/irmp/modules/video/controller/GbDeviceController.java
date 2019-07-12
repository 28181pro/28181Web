package com.cethik.irmp.modules.video.controller;


import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;
import com.cethik.irmp.modules.video.service.GbDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/video/gbdevice")
public class GbDeviceController extends AbstractController {
    @Autowired
    private GbDeviceService gbDeviceService;


    @RequestMapping("/list")
    Page<GbDeviceEntity> list(@RequestBody Map<String, Object> params) {
        try {
            Page<GbDeviceEntity> pages = gbDeviceService.listGbDevice(params);
            return pages;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new Page<GbDeviceEntity>();
        }
    }


    /**
     * 新增国际设备
     *
     * @param gbDeviceEntity
     * @return
     */
    @SysLog("新增国际设备")
    @RequestMapping("/save")
    public R saveChannel(@RequestBody GbDeviceEntity gbDeviceEntity) {
        try {
            return gbDeviceService.saveGbDevice(gbDeviceEntity);
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
            return gbDeviceService.getGbDeviceById(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 修改国际设备
     *
     * @param gbDeviceEntity
     * @return
     */
    @SysLog("修改国际设备")
    @RequestMapping("/update")
    public R updateChannel(@RequestBody GbDeviceEntity gbDeviceEntity) {
        try {
            return gbDeviceService.updateGbDevice(gbDeviceEntity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }

    /**
     * 批量删除国际设备
     *
     * @param id
     * @return
     */
    @SysLog("删除国际设备")
    @RequestMapping("/remove")
    public R batchRemove(@RequestBody Long[] id) {
        try {
            return gbDeviceService.batchRemove(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return CommonUtils.msg("系统异常");
        }
    }


}
