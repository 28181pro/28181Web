package com.cethik.irmp.modules.sys.dao;

import java.util.List;

import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import com.cethik.irmp.modules.sys.entity.SysMenuEntity;

/**
 * 系统菜单dao
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	
	List<SysMenuEntity> listParentId(Long parentId);
	
	List<SysMenuEntity> listNotButton();
	
	List<String> listUserPerms(Long userId);
	
	int countMenuChildren(Long parentId);

}
