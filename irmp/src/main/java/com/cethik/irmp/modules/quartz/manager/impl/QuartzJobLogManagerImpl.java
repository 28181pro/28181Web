package com.cethik.irmp.modules.quartz.manager.impl;

import java.util.List;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.dao.QuartzJobLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.dao.QuartzJobLogMapper;
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;
import com.cethik.irmp.modules.quartz.manager.QuartzJobLogManager;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Component("quartzJobLogManager")
public class QuartzJobLogManagerImpl implements QuartzJobLogManager {

	@Autowired
	private QuartzJobLogMapper quartzLobLogMapper;
	
	@Override
	public List<QuartzJobLogEntity> listForPage(Page<QuartzJobLogEntity> page, Query query) {
		return quartzLobLogMapper.listForPage(page, query);
	}

	@Override
	public int saveQuartzJobLog(QuartzJobLogEntity log) {
		return quartzLobLogMapper.save(log);
	}

	@Override
	public int batchRemove(Long[] id) {
		return quartzLobLogMapper.batchRemove(id);
	}

	@Override
	public int batchRemoveAll() {
		return quartzLobLogMapper.batchRemoveAll();
	}

}
