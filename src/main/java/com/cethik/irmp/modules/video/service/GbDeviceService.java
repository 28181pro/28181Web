package com.cethik.irmp.modules.video.service;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;

import java.util.Map;

/**
 * 国际设备服务
 *
 * @author daniel.yu
 * @Date 2019/7/11 21:20
 */
public interface GbDeviceService {
    Page<GbDeviceEntity> listGbDevice(Map<String, Object> params);

    R saveGbDevice(GbDeviceEntity gbDeviceEntity);

    R getGbDeviceById(Long id);

    R updateGbDevice(GbDeviceEntity gbDeviceEntity);

    R batchRemove(Long[] id);

    R listGbDevice();
}
