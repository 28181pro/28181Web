package com.cethik.irmp.modules.sys.dao;

import java.util.List;

import com.cethik.irmp.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.modules.sys.entity.SysRoleEntity;

/**
 * 系统角色
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
	
	List<String> listUserRoles(Long userId);
	
}
