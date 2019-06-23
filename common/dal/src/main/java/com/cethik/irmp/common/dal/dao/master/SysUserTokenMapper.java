package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysUserTokenDO;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户token
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenDO> {

	SysUserTokenDO getByToken(String token);
	
	SysUserTokenDO getByUserId(Long userId);
	
}
