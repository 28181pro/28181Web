package com.cethik.irmp.modules.sys.service;

import java.util.List;

import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;

/**
 * 组织机构
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysOrgService {

	List<SysOrgEntity> listOrg();
	
	List<SysOrgEntity> listOrgTree();
	
	R saveOrg(SysOrgEntity org);
	
	R getOrg(Long orgId);
	
	R updateOrg(SysOrgEntity org);
	
	R bactchRemoveOrg(Long[] id);
	
}
