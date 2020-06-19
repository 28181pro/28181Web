package com.cethik.irmp.modules.video.dao;

import com.cethik.irmp.modules.sys.dao.BaseMapper;
import com.cethik.irmp.modules.video.entity.LocationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Mapper
public interface LocationMapper extends BaseMapper<LocationEntity> {
	
	List<LocationEntity> listParentId(Long parentId);
	List<Long> listAllLocationId( Long userid);
	List<LocationEntity> listNotLine();
	List<LocationEntity> listLine();
	List<String> listUserPerms(Long userId);
	
	int countLocationChildren(Long parentId);

}
