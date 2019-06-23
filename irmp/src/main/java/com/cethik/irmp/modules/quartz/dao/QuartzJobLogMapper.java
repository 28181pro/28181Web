package com.cethik.irmp.modules.quartz.dao;

import com.cethik.irmp.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;
import com.cethik.irmp.modules.sys.dao.BaseMapper;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

	int batchRemoveAll();
	
}
