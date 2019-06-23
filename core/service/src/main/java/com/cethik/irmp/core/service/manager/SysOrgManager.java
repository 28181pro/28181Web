package com.cethik.irmp.core.service.manager;

import com.cethik.irmp.common.dal.entity.SysOrgDO;

import java.util.List;

/**
 * 组织机构
 *
 * @author daniel.yu
 * @date 2017年8月17日 上午11:31:59
 */
public interface SysOrgManager {

	List<SysOrgDO> listOrg();
	
	int saveOrg(SysOrgDO org);
	
	SysOrgDO getOrg(Long orgId);
	
	int updateOrg(SysOrgDO org);
	
	int bactchRemoveOrg(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
