package com.cethik.irmp.core.service.manager;

import com.cethik.irmp.common.dal.entity.SysUserDO;
import com.cethik.irmp.common.dal.entity.SysUserTokenDO;
//import Page;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.core.model.SysUserToken;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author daniel.yu
 * @date 2017年8月11日 上午11:43:01
 */
public interface SysUserManager {

	//SysUserDO getByUserName(String username);
	SysUser getByUserName(String username);
	List<SysUserDO> listUser(Page<SysUserDO> page, Query search);
	
	int saveUser(SysUserDO user);
	
	SysUser getById(Long userId);
	
	int updateUser(SysUserDO user);
	
	int batchRemove(Long[] id);
	
	Set<String> listUserPerms(Long userId);
	
	Set<String> listUserRoles(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserEnable(Long[] id);
	
	int updateUserDisable(Long[] id);
	
	int updatePswd(SysUserDO user);
	
	SysUserDO getUserById(Long userId);
	
	SysUserToken getByToken(String token);
	
	SysUserToken saveUserToken(Long userId,String token);
	
	int updateUserToken(Long userId);
	
}
