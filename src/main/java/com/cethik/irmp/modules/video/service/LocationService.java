package com.cethik.irmp.modules.video.service;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysMenuEntity;
import com.cethik.irmp.modules.video.entity.LocationEntity;

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
public interface LocationService {
	
	R listUserLocation(Long userId);

	List<LocationEntity> listLocation(Map<String, Object> params);
	
	R listNotLine();
	
	R saveLocation(LocationEntity menu);

	R getLocationById(Long id);
	
	R updateLocation(LocationEntity menu);
	
	R batchRemove(Long[] id);

}
