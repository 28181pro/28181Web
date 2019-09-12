package com.cethik.irmp.modules.video.manager.impl;

import com.cethik.irmp.common.constant.SystemConstant;
import com.cethik.irmp.common.constant.SystemConstant.MenuType;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.dao.SysMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysRoleMenuMapper;
import com.cethik.irmp.modules.sys.dao.SysUserMapper;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.modules.sys.manager.SysMenuManager;
import com.cethik.irmp.modules.video.dao.ChannelMapper;
import com.cethik.irmp.modules.video.dao.LocationMapper;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.entity.GbDeviceEntity;
import com.cethik.irmp.modules.video.entity.LocationEntity;
import com.cethik.irmp.modules.video.manager.LocationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 * 
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
@Component("locationManager")
public class LocationManagerImpl implements LocationManager {


	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private ChannelMapper channelMapper;

	@Override
	public List<LocationEntity> listUserLocation(Long userId) {
		List<Long> locationuIdList = locationMapper.listAllLocationId(userId);
		return getAllLocationList(locationuIdList);
	}

	/**locationuList
	 * 获取所有菜单列表
	 */
	private List<LocationEntity> getAllLocationList(List<Long> menuIdList){
		//查询根菜单列表
		List<LocationEntity> menuList = listParentId(0L, menuIdList);
		//递归获取子菜单
		getLocationTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<LocationEntity> getLocationTreeList(List<LocationEntity> menuList, List<Long> menuIdList){
		List<LocationEntity> subMenuList = new ArrayList<LocationEntity>();

		for(LocationEntity entity : menuList){
			if(entity.getType() != SystemConstant.LocationType.LINE.getValue()){//目录
				entity.setList(getLocationTreeList(listParentId(entity.getLocationId(), menuIdList), menuIdList));
			}
			else
			{
				List<ChannelEntity>   channellist = channelMapper.listChannelbylocation( entity.getLocationId() );
				entity.setList( channellist);

			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}

	@Override
	public List<LocationEntity> listParentId(Long parentId, List<Long> menuIdList) {
		List<LocationEntity> menuList = locationMapper.listParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<LocationEntity> userMenuList = new ArrayList<>();
		for(LocationEntity menu : menuList){
			if(menuIdList.contains(menu.getLocationId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<LocationEntity> listLocation( Query search) {

		return locationMapper.list( search);
	}

	@Override
	public List<LocationEntity> listNotLine() {
		return locationMapper.listNotLine();
	}

	@Override
	public int saveLocation(LocationEntity menu) {
		return locationMapper.save(menu);
	}

	@Override
	public LocationEntity getLocationById(Long id) {
		return locationMapper.getObjectById(id);
	}

	@Override
	public int updateLocation(LocationEntity menu) {
		return locationMapper.update(menu);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = locationMapper.batchRemove(id);
		locationMapper.batchRemove(id);
		return count;
	}

	@Override
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = locationMapper.countLocationChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}

}
