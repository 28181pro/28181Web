package com.cethik.irmp.core.service.manager.impl;

import com.cethik.irmp.common.dal.dao.master.SysMenuMapper;
import com.cethik.irmp.common.dal.dao.master.SysRoleMenuMapper;
import com.cethik.irmp.common.dal.dao.master.SysUserMapper;
import com.cethik.irmp.common.dal.entity.SysMenuDO;
import com.cethik.irmp.common.util.constant.SystemConstant;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.common.util.utils.CommonUtils;
import com.cethik.irmp.core.service.manager.SysMenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 * 
 * @author daniel.yu
 * @date 2017年8月10日 上午10:35:24
 */
@Component("sysMenuManager")
public class SysMenuManagerImpl implements SysMenuManager {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Override
	public List<SysMenuDO> listUserMenu(Long userId) {
		List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuDO> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuDO> menuList = listParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuDO> getMenuTreeList(List<SysMenuDO> menuList, List<Long> menuIdList){
		List<SysMenuDO> subMenuList = new ArrayList<SysMenuDO>();
		
		for(SysMenuDO entity : menuList){
			if(entity.getType() == SystemConstant.MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}

	@Override
	public List<SysMenuDO> listParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuDO> menuList = sysMenuMapper.listParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuDO> userMenuList = new ArrayList<>();
		for(SysMenuDO menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuDO> listMenu(Query search) {
		return sysMenuMapper.list(search);
	}

	@Override
	public List<SysMenuDO> listNotButton() {
		return sysMenuMapper.listNotButton();
	}

	@Override
	public int saveMenu(SysMenuDO menu) {
		return sysMenuMapper.save(menu);
	}

	@Override
	public SysMenuDO getMenuById(Long id) {
		return sysMenuMapper.getObjectById(id);
	}

	@Override
	public int updateMenu(SysMenuDO menu) {
		return sysMenuMapper.update(menu);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysMenuMapper.batchRemove(id);
		sysRoleMenuMapper.batchRemoveByMenuId(id);
		return count;
	}

	@Override
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = sysMenuMapper.countMenuChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}

}
