package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysRoleDO;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {
	
	List<String> listUserRoles(Long userId);
	
}
