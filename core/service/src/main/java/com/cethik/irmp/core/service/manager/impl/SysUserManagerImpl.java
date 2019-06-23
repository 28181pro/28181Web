package com.cethik.irmp.core.service.manager.impl;

import com.cethik.irmp.common.dal.dao.master.*;
import com.cethik.irmp.common.dal.entity.SysUserDO;
import com.cethik.irmp.common.dal.entity.SysUserTokenDO;
import com.cethik.irmp.common.util.constant.SystemConstant;
//import Page;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.core.Converter;
import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.core.model.SysUserToken;
import com.cethik.irmp.core.service.manager.SysUserManager;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 系统用户
 *
 * @author daniel.yu
 * @date 2017年8月11日 上午11:44:21
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {

	/**
	 * token过期时间，12小时
	 */
	private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysUserTokenMapper sysUserTokenMapper;
	
	@Override
	public List<SysUserDO> listUser(Page<SysUserDO> page, Query search) {
		return sysUserMapper.listForPage(page, search);
	}

	@Override
	public SysUser getByUserName(String username) {
		try{
			return Converter.ToSysUserDTO(sysUserMapper.getByUserName(username));
		}catch (Exception e){
			String s=e.getMessage();
			return null;
		}

	}

	@Override
	public int saveUser(SysUserDO user) {
		int count = sysUserMapper.save(user);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return count;
	}

	@Override
	public SysUser getById(Long userId) {
		SysUser user =Converter.ToSysUserDTO(sysUserMapper.getObjectById(userId));
		user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
		return user;
	}

	@Override
	public int updateUser(SysUserDO user) {
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return count;
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return count;
	}

	@Override
	public Set<String> listUserPerms(Long userId) {
		List<String> perms = sysMenuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for(String perm : perms) {
			if(StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public Set<String> listUserRoles(Long userId) {
		List<String> roles = sysRoleMapper.listUserRoles(userId);
		Set<String> rolesSet = new HashSet<>();
		for(String role : roles) {
			if(StringUtils.isNotBlank(role)) {
				rolesSet.addAll(Arrays.asList(role.trim().split(",")));
			}
		}
		return rolesSet;
	}

	@Override
	public int updatePswdByUser(Query query) {
		return sysUserMapper.updatePswdByUser(query);
	}

	@Override
	public int updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updatePswd(SysUserDO user) {
		return sysUserMapper.updatePswd(user);
	}

	@Override
	public SysUserDO getUserById(Long userId) {//不包含角色信息
		return sysUserMapper.getObjectById(userId);
	}

	@Override
	public SysUserToken getByToken(String token) {
		return Converter.ToSysUserTokenDTO(sysUserTokenMapper.getByToken(token));
	}

	@Override
	public SysUserToken saveUserToken(Long userId,String token) {
		//当前时间
		Date now = new Date();
		Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
		SysUserTokenDO userToken = sysUserTokenMapper.getByUserId(userId);
		if(userToken == null) {
			userToken = new SysUserTokenDO();
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			sysUserTokenMapper.save(userToken);
		} else {
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			sysUserTokenMapper.update(userToken);
		}
		SysUserToken sysUserToken=new SysUserToken();
		BeanUtils.copyProperties(userToken,sysUserToken);
		return sysUserToken;
	}

	@Override
	public int updateUserToken(Long userId) {
//		String token = TokenGenerator.generateValue();
//		SysUserTokenDO userToken = new SysUserTokenDO();
//		userToken.setUserId(userId);
//		userToken.setToken(token);
//		return sysUserTokenMapper.update(userToken);
		return  1;
	}

}
