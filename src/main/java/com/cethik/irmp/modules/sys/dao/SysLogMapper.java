package com.cethik.irmp.modules.sys.dao;

import com.cethik.irmp.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.modules.sys.entity.SysLogEntity;

/**
 * 系统日志 
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	int batchRemoveAll();
	
}
