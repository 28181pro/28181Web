package com.cethik.irmp.modules.quartz.manager.impl;

import java.util.List;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.dao.QuartzJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.quartz.dao.QuartzJobMapper;
import com.cethik.irmp.modules.quartz.entity.QuartzJobEntity;
import com.cethik.irmp.modules.quartz.manager.QuartzJobManager;

/**
 * 定时任务
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Component("quartzJobManager")
public class QuartzJobManagerImpl implements QuartzJobManager {

	@Autowired
	private QuartzJobMapper quartzJobMapper;
	
	@Override
	public List<QuartzJobEntity> listNormalJob() {
		return quartzJobMapper.list();
	}

	@Override
	public List<QuartzJobEntity> listForPage(Page<QuartzJobEntity> page, Query query) {
		return quartzJobMapper.listForPage(page, query);
	}

	@Override
	public int saveQuartzJob(QuartzJobEntity job) {
		return quartzJobMapper.save(job);
	}

	@Override
	public QuartzJobEntity getQuartzJobById(Long jobId) {
		return quartzJobMapper.getObjectById(jobId);
	}

	@Override
	public int updateQuartzJob(QuartzJobEntity job) {
		return quartzJobMapper.update(job);
	}

	@Override
	public int batchRemoveQuartzJob(Long[] id) {
		return quartzJobMapper.batchRemove(id);
	}

	@Override
	public int batchUpdate(Long[] jobId, Integer status) {
		Query query = new Query();
		query.put("jobIdList", jobId);
		query.put("status", status);
		return quartzJobMapper.batchUpdate(query);
	}

}
