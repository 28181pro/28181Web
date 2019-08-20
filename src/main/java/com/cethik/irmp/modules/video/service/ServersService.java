package com.cethik.irmp.modules.video.service;

import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.video.entity.ServersEntity;

import java.util.List;

/**
 * 服务器管理服务
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/6/20 20:20
 */
public interface ServersService {

	R selectServers();

	List<ServersEntity> listServers();
	
	List<ServersEntity> listRegisterServerTree();

	List<ServersEntity> listStreamServerTree();
	
	R saveServers(ServersEntity serversEntity);
	
	R getServersById(Long serverId);
	
	R updateServers(ServersEntity serversEntity);
	

	
}
