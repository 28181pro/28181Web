package com.cethik.irmp.modules.quartz.manager;

import java.util.List;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public interface QuartzJobLogManager {

	List<QuartzJobLogEntity> listForPage(Page<QuartzJobLogEntity> page, Query query);
	
	int saveQuartzJobLog(QuartzJobLogEntity log);
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();
	
}
