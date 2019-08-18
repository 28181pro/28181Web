package com.cethik.irmp.modules.quartz.service.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;
import com.cethik.irmp.modules.quartz.manager.QuartzJobLogManager;
import com.cethik.irmp.modules.quartz.service.QuartzJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月21日 上午11:18:22
 */
@Service("quartzJobLogService")
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

    @Autowired
    private QuartzJobLogManager quartzJobLogManager;

    @Override
    public Page<QuartzJobLogEntity> listForPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<QuartzJobLogEntity> page = new Page<>(query);
        quartzJobLogManager.listForPage(page, query);
        return page;
    }

    @Override
    public R batchRemove(Long[] id) {
        int count = quartzJobLogManager.batchRemove(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public R batchRemoveAll() {
        int count = quartzJobLogManager.batchRemoveAll();
        return CommonUtils.msg(count);
    }


}
