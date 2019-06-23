package com.cethik.irmp.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;

/**
 * 系统菜单
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface SysMenuService {
	
	R listUserMenu(Long userId);
	
	List<SysMenuEntity> listMenu(Map<String, Object> params);
	
	R listNotButton();
	
	R saveMenu(SysMenuEntity menu);

	R getMenuById(Long id);
	
	R updateMenu(SysMenuEntity menu);
	
	R batchRemove(Long[] id);

}
