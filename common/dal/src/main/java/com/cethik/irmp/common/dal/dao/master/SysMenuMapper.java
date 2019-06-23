package com.cethik.irmp.common.dal.dao.master;

import com.cethik.irmp.common.dal.entity.SysMenuDO;
import com.cethik.irmp.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuDO> {
	
	List<SysMenuDO> listParentId(Long parentId);
	
	List<SysMenuDO> listNotButton();
	
	List<String> listUserPerms(Long userId);
	
	int countMenuChildren(Long parentId);

}
