package com.cethik.irmp.core.service.manager.impl;

import com.cethik.irmp.common.dal.dao.master.SysOrgMapper;
import com.cethik.irmp.common.dal.dao.master.SysRoleOrgMapper;
import com.cethik.irmp.common.dal.entity.SysOrgDO;
import com.cethik.irmp.common.util.utils.CommonUtils;
import com.cethik.irmp.core.service.manager.SysOrgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 组织架构
 *
 * @author daniel.yu
 * @date 2017年8月17日 上午11:32:15
 */
@Component("sysOrgManager")
public class SysOrgManagerImpl implements SysOrgManager {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Autowired
	private SysRoleOrgMapper sysRoleOrgMapper;
	
	@Override
	public List<SysOrgDO> listOrg() {
		return sysOrgMapper.list();
	}

	@Override
	public int saveOrg(SysOrgDO org) {
		return sysOrgMapper.save(org);
	}

	@Override
	public SysOrgDO getOrg(Long orgId) {
		return sysOrgMapper.getObjectById(orgId);
	}

	@Override
	public int updateOrg(SysOrgDO org) {
		return sysOrgMapper.update(org);
	}

	@Override
	public int bactchRemoveOrg(Long[] id) {
		int count = sysOrgMapper.batchRemove(id);
		sysRoleOrgMapper.batchRemoveByOrgId(id);
		return count;
	}

	@Override
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = sysOrgMapper.countOrgChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}

}
