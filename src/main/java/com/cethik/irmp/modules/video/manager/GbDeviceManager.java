package com.cethik.irmp.modules.video.manager;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;

import java.util.List;

/**
 * 国际设备管理
 *
 * @author daniel.yu
 * @Date 2019/7/11 19:20
 */
public interface GbDeviceManager {
    List<GbDeviceEntity> listGbDevice(Page<GbDeviceEntity> page, Query search);

    List<GbDeviceEntity> listGbDevice();

    int saveGbDevice(GbDeviceEntity gbDeviceEntity);

    GbDeviceEntity getGbDeviceById(Long id);

    int updateGbDevice(GbDeviceEntity gbDeviceEntity);

    int batchRemove(Long[] id);


}
