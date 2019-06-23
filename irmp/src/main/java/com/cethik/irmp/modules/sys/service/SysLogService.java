package com.cethik.irmp.modules.sys.service;

import java.util.Map;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysLogService {

	Page<SysLogEntity> listLog(Map<String, Object> params);
	
	R batchRemove(Long[] id);
	
	R batchRemoveAll();
	
}
