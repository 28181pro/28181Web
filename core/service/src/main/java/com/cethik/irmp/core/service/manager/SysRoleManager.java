package com.cethik.irmp.core.service.manager;

import com.cethik.irmp.common.dal.entity.SysRoleDO;
//import Page;

import java.util.List;

/**
 * 系统角色
 *
 * @author daniel.yu
 * @date 2017年8月12日 上午12:39:07
 */
public interface SysRoleManager {

//	List<SysRoleDO> listRole(Page<SysRoleDO> page, Query search);
	
	int saveRole(SysRoleDO role);
	
	SysRoleDO getRoleById(Long id);
	
	int updateRole(SysRoleDO role);
	
	int batchRemove(Long[] id);
	
	List<SysRoleDO> listRole();
	
	int updateRoleOptAuthorization(SysRoleDO role);

	int updateRoleDataAuthorization(SysRoleDO role);
	
}
