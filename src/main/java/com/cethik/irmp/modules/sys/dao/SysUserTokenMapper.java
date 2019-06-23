package com.cethik.irmp.modules.sys.dao;

import com.cethik.irmp.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户token
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

	SysUserTokenEntity getByToken(String token);
	
	SysUserTokenEntity getByUserId(Long userId);
	
}
