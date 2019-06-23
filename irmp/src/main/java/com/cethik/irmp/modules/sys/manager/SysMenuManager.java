package com.cethik.irmp.modules.sys.manager;

import java.util.List;

import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;

/**
 * 系统菜单
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysMenuManager {
	
	List<SysMenuEntity> listUserMenu(Long userId);
	
	List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList);
	
	List<SysMenuEntity> listMenu(Query search);
	
	List<SysMenuEntity> listNotButton();
	
	int saveMenu(SysMenuEntity menu);

	SysMenuEntity getMenuById(Long id);
	
	int updateMenu(SysMenuEntity menu);
	
	int batchRemove(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
