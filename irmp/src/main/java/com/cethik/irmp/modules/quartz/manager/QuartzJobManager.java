package com.cethik.irmp.modules.quartz.manager;

import java.util.List;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.entity.QuartzJobEntity;

/**
 * 定时任务
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public interface QuartzJobManager {

	List<QuartzJobEntity> listForPage(Page<QuartzJobEntity> page, Query query);
	
	List<QuartzJobEntity> listNormalJob();
	
	int saveQuartzJob(QuartzJobEntity job);
	
	QuartzJobEntity getQuartzJobById(Long jobId);
	
	int updateQuartzJob(QuartzJobEntity job);
	
	int batchRemoveQuartzJob(Long[] id);
	
	int batchUpdate(Long[] jobId, Integer status);
	
}
