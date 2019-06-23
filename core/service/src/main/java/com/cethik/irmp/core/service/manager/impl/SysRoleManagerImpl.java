package com.cethik.irmp.core.service.manager.impl;

import com.cethik.irmp.common.dal.dao.master.SysRoleMapper;
import com.cethik.irmp.common.dal.dao.master.SysRoleMenuMapper;
import com.cethik.irmp.common.dal.dao.master.SysRoleOrgMapper;
import com.cethik.irmp.common.dal.dao.master.SysUserRoleMapper;
import com.cethik.irmp.common.dal.entity.SysRoleDO;
//import Page;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.core.service.manager.SysRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色
 *
 * @author daniel.yu
 * @date 2017年8月12日 上午12:39:48
 */
@Component("sysRoleManager")
public class SysRoleManagerImpl implements SysRoleManager {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysRoleOrgMapper sysRoleOrgMapper;

//	@Override
//	public List<SysRoleDO> listRole(Page<SysRoleDO> page, Query search) {
//		return sysRoleMapper.listForPage(page, search);
//	}

	@Override
	public int saveRole(SysRoleDO role) {
		return sysRoleMapper.save(role);
	}

	@Override
	public SysRoleDO getRoleById(Long id) {
		SysRoleDO role = sysRoleMapper.getObjectById(id);
		List<Long> menuId = sysRoleMenuMapper.listMenuId(id);
		List<Long> orgId = sysRoleOrgMapper.listOrgId(id);
		role.setMenuIdList(menuId);
		role.setOrgIdList(orgId);
		return role;
	}

	@Override
	public int updateRole(SysRoleDO role) {
		return sysRoleMapper.update(role);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysRoleMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByRoleId(id);
		sysRoleMenuMapper.batchRemoveByRoleId(id);
		sysRoleOrgMapper.batchRemoveByRoleId(id);
		return count;
	}

	@Override
	public List<SysRoleDO> listRole() {
		return sysRoleMapper.list();
	}

	@Override
	public int updateRoleOptAuthorization(SysRoleDO role) {
		Long roleId = role.getRoleId();
		int count = sysRoleMenuMapper.remove(roleId);
		Query query = new Query();
		query.put("roleId", roleId);
		List<Long> menuId = role.getMenuIdList();
		if(menuId.size() > 0) {
			query.put("menuIdList", role.getMenuIdList());
			count = sysRoleMenuMapper.save(query);
		}
		return count;
	}
	
	@Override
	public int updateRoleDataAuthorization(SysRoleDO role) {
		Long roleId = role.getRoleId();
		int count = sysRoleOrgMapper.remove(roleId);
		Query query = new Query();
		query.put("roleId", roleId);
		List<Long> orgId = role.getOrgIdList();
		if(orgId.size() > 0) {
			query.put("orgIdList", role.getOrgIdList());
			count = sysRoleOrgMapper.save(query);
		}
		return count;
	}
	
}
