package com.cethik.irmp.modules.video.manager;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.video.entity.LocationEntity;

import java.util.List;

/**
 * 系统菜单
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface LocationManager {
	
	List<LocationEntity> listUserLocation(Long userId);
	
	List<LocationEntity> listParentId(Long parentId, List<Long> locationIdList);
	
	List<LocationEntity> listLocation( Query search);

	List<LocationEntity> listNotLine();

	List<LocationEntity> listLine();

	int saveLocation(LocationEntity menu);

	LocationEntity getLocationById(Long id);
	
	int updateLocation(LocationEntity menu);
	
	int batchRemove(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
