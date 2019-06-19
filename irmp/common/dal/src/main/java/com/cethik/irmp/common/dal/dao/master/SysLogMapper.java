package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysLogEntity;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	int batchRemoveAll();
	
}
