package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysUserRoleDO;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色关系
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleDO> {

	List<Long> listUserRoleId(Long userId);
	
	int batchRemoveByUserId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
