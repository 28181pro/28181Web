package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.constant.MsgConstant;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.modules.sys.manager.SysMenuManager;
import com.cethik.irmp.modules.sys.service.SysMenuService;
import com.cethik.irmp.modules.video.entity.LocationEntity;
import com.cethik.irmp.modules.video.manager.LocationManager;
import com.cethik.irmp.modules.video.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationManager locationManager;

	@Override
	public R listUserLocation(Long userId) {
		return R.ok().put("locationList", locationManager.listUserLocation(userId));
	}

	@Override
	public List<LocationEntity> listLocation(Map<String, Object> params) {
		Query query = new Query(params);
		List<LocationEntity> listLocation = locationManager.listLocation(query);
		return listLocation;
	}

	@Override
	public R listNotLine() {
		List<LocationEntity> menuList = locationManager.listNotLine();
		LocationEntity root = new LocationEntity();
		root.setLocationId(0L);
		root.setName("市公司");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return CommonUtils.msgNotCheckNull(menuList);
	}

	@Override
	public R saveLocation(LocationEntity menu) {
		int count = locationManager.saveLocation(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public R getLocationById(Long id) {
		LocationEntity menu = locationManager.getLocationById(id);
		return CommonUtils.msg(menu);
	}

	@Override
	public R updateLocation(LocationEntity menu) {
		int count = locationManager.updateLocation(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		boolean children = locationManager.hasChildren(id);
		if(children) {
			return R.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = locationManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
