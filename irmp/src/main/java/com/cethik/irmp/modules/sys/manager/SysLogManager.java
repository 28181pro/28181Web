package com.cethik.irmp.modules.sys.manager;

import java.util.List;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysLogEntity;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysLogManager {

	void saveLog(SysLogEntity log);
	
	List<SysLogEntity> listLog(Page<SysLogEntity> page, Query query);
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();
	
}
