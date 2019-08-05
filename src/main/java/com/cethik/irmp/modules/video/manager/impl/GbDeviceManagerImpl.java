package com.cethik.irmp.modules.video.manager.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.video.dao.GbDeviceMapper;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;
import com.cethik.irmp.modules.video.manager.GbDeviceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 国际设备管理
 *
 * @author daniel.yu
 * @Date 2019/7/11 19:26
 */
@Component("gbDeviceManager")
public class GbDeviceManagerImpl implements GbDeviceManager {

    @Autowired
    private GbDeviceMapper gbDeviceMapper;

    @Override
    public List<GbDeviceEntity> listGbDevice(Page<GbDeviceEntity> page, Query search) {
        return gbDeviceMapper.listForPage(page, search);
    }

    @Override
    public List<GbDeviceEntity> listGbDevice() {
        return gbDeviceMapper.list();
    }

    @Override
    public int saveGbDevice(GbDeviceEntity gbDeviceEntity) {
        return gbDeviceMapper.save(gbDeviceEntity);
    }

    @Override
    public GbDeviceEntity getGbDeviceById(Long id) {
        return gbDeviceMapper.getObjectById(id);
    }

    @Override
    public int updateGbDevice(GbDeviceEntity gbDeviceEntity) {
        return gbDeviceMapper.update(gbDeviceEntity);
    }

    @Override
    public int batchRemove(Long[] id) {
        int count = gbDeviceMapper.batchRemove(id);
        return count;
    }


}
