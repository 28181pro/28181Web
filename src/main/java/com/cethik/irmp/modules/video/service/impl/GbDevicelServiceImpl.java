package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;
import com.cethik.irmp.modules.video.manager.GbDeviceManager;
import com.cethik.irmp.modules.video.service.GbDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 国际设备服务
 *
 * @author daniel.yu
 * @Date 2019/7/11 22:02
 */
@Service("gbDeviceService")
public class GbDevicelServiceImpl implements GbDeviceService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GbDeviceManager gbDeviceManager;


    public void statisticsChannelNum() {
        try {
            logger.info("任务开始-统计设备通道数");
            //TODO
            logger.info("任务结束-统计设备通道数");
        } catch (Exception ex) {
            logger.error("任务异常"+ex.getMessage());
        }

    }

    @Override
    public Page<GbDeviceEntity> listGbDevice(Map<String, Object> params) {
        Query form = new Query(params);
        Page<GbDeviceEntity> page = new Page<>(form);
        gbDeviceManager.listGbDevice(page, form);
        return page;
    }

    @Override
    public R saveGbDevice(GbDeviceEntity gbDeviceEntity) {
        int count = gbDeviceManager.saveGbDevice(gbDeviceEntity);
        return CommonUtils.msg(count);
    }

    @Override
    public R getGbDeviceById(Long id) {
        GbDeviceEntity gbDeviceEntity = gbDeviceManager.getGbDeviceById(id);
        return CommonUtils.msg(gbDeviceEntity);
    }

    @Override
    public R updateGbDevice(GbDeviceEntity gbDeviceEntity) {
        int count = gbDeviceManager.updateGbDevice(gbDeviceEntity);
        return CommonUtils.msg(count);
    }

    @Override
    public R batchRemove(Long[] id) {
        int count = gbDeviceManager.batchRemove(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public R listGbDevice() {
        List<GbDeviceEntity> gbDeviceEntityList = gbDeviceManager.listGbDevice();

//TODO BEGIN
        GbDeviceEntity gbDeviceEntity1 = new GbDeviceEntity();
        gbDeviceEntity1.setName("设备一");
        gbDeviceEntity1.setChannelSum(20);
        GbDeviceEntity gbDeviceEntity2 = new GbDeviceEntity();
        gbDeviceEntity2.setName("设备二");
        gbDeviceEntity2.setChannelSum(150);
        gbDeviceEntityList.add(gbDeviceEntity1);
        gbDeviceEntityList.add(gbDeviceEntity2);
//TODO END

        return CommonUtils.msgNotCheckNull(gbDeviceEntityList);
    }


}
