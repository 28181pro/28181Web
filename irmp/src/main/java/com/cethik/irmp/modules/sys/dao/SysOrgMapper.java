package com.cethik.irmp.modules.sys.dao;

import com.cethik.irmp.modules.sys.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;

/**
 * 组织架构
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {

	int countOrgChildren(Long parentId);
	
}
