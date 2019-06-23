package com.cethik.irmp.modules.sys.dao;

import java.util.List;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;

/**
 * 系统用户dao
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	SysUserEntity getByUserName(String username);
	
	List<Long> listAllMenuId(Long userId);
	
	List<Long> listAllOrgId(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserStatus(Query query);
	
	int updatePswd(SysUserEntity user);
	
}
