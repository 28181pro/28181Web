package com.cethik.irmp.modules.sys.dao;

import java.util.List;

import com.cethik.irmp.modules.sys.entity.SysRoleOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import com.cethik.irmp.modules.sys.entity.SysRoleOrgEntity;

/**
 * 角色与机构的关系
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	List<Long> listOrgId(Long roleId);
	
	int batchRemoveByOrgId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
