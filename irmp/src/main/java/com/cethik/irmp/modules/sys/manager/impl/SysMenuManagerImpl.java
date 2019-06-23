package com.cethik.irmp.modules.sys.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.cethik.irmp.common.constant.SystemConstant;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.dao.SysMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysRoleMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysUserMapper;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cethik.irmp.common.constant.SystemConstant.MenuType;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.dao.SysMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysRoleMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysUserMapper;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.modules.sys.manager.SysMenuManager;

/**
 * 系统菜单
 * 
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
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
	public List<SysMenuEntity> listUserMenu(Long userId) {
		List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = listParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			if(entity.getType() == SystemConstant.MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}

	@Override
	public List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = sysMenuMapper.listParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> listMenu(Query search) {
		return sysMenuMapper.list(search);
	}

	@Override
	public List<SysMenuEntity> listNotButton() {
		return sysMenuMapper.listNotButton();
	}

	@Override
	public int saveMenu(SysMenuEntity menu) {
		return sysMenuMapper.save(menu);
	}

	@Override
	public SysMenuEntity getMenuById(Long id) {
		return sysMenuMapper.getObjectById(id);
	}

	@Override
	public int updateMenu(SysMenuEntity menu) {
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
