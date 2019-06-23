package com.cethik.irmp.core.service.manager;

import com.cethik.irmp.common.dal.entity.SysMenuDO;
import com.cethik.irmp.common.util.entity.Query;

import java.util.List;

/**
 * 系统菜单
 *
 * @author daniel.yu
 * @date 2017年8月10日 上午10:34:59
 */
public interface SysMenuManager {
	
	List<SysMenuDO> listUserMenu(Long userId);
	
	List<SysMenuDO> listParentId(Long parentId, List<Long> menuIdList);
	
	List<SysMenuDO> listMenu(Query search);
	
	List<SysMenuDO> listNotButton();
	
	int saveMenu(SysMenuDO menu);

	SysMenuDO getMenuById(Long id);
	
	int updateMenu(SysMenuDO menu);
	
	int batchRemove(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
