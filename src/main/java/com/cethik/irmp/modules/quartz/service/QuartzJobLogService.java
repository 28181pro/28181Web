package com.cethik.irmp.modules.quartz.service;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public interface QuartzJobLogService {

    Page<QuartzJobLogEntity> listForPage(Map<String, Object> params);

    R batchRemove(Long[] id);

    R batchRemoveAll();

}
