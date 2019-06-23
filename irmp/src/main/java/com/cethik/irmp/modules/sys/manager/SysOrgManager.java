package com.cethik.irmp.modules.sys.manager;

import java.util.List;

import com.cethik.irmp.modules.sys.entity.SysOrgEntity;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;

/**
 * 组织机构
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysOrgManager {

	List<SysOrgEntity> listOrg();
	
	int saveOrg(SysOrgEntity org);
	
	SysOrgEntity getOrg(Long orgId);
	
	int updateOrg(SysOrgEntity org);
	
	int bactchRemoveOrg(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
