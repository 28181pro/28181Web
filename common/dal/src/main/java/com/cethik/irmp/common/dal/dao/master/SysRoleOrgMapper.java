package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysRoleOrgDO;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与机构的关系
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgDO> {

	List<Long> listOrgId(Long roleId);
	
	int batchRemoveByOrgId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
