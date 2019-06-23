package com.cethik.irmp.modules.quartz.service;

import java.util.Map;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.quartz.entity.QuartzJobEntity;

/**
 * 定时任务
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public interface QuartzJobService {
	
	Page<QuartzJobEntity> list(Map<String, Object> params);
	
	R saveQuartzJob(QuartzJobEntity job);
	
	R getQuartzJobById(Long jobId);
	
	R updateQuartzJob(QuartzJobEntity job);
	
	R batchRemoveQuartzJob(Long[] id);
	
	R run(Long[] id);
	
	R pause(Long[] id);
	
	R resume(Long[] id);
	
}
