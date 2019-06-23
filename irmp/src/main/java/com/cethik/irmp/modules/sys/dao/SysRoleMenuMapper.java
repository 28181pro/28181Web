package com.cethik.irmp.modules.sys.dao;

import java.util.List;

import com.cethik.irmp.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.modules.sys.entity.SysRoleMenuEntity;

/**
 * 系统角色与菜单关系
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

	int batchRemoveByMenuId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
	List<Long> listMenuId(Long id);
	
}
