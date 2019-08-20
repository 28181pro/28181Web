package com.cethik.irmp.modules.video.service.impl;

import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.CommonUtils;
import com.cethik.irmp.modules.video.entity.ServersEntity;
import com.cethik.irmp.modules.video.manager.ServersManager;
import com.cethik.irmp.modules.video.service.ServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务器管理服务
 *
 * @author daniel.yu
 *
 *
 * @Date 2019/8/20 20:20
 */
@Service("serversService")
public class ServersServiceImpl implements ServersService {

	@Autowired
	private ServersManager serversManager;


	@Override
	public List<ServersEntity> listServers() {
		return serversManager.listServers();
	}

	@Override
	public List<ServersEntity> listRegisterServerTree() {
		return serversManager.listServers();
	}

	@Override
	public List<ServersEntity> listStreamServerTree() {
		return serversManager.listServers();
	}

	@Override
	public R saveServers(ServersEntity serversEntity) {
		int count = serversManager.saveServers(serversEntity);
		return CommonUtils.msg(count);
	}

	@Override
	public R getServersById(Long serverId) {
		ServersEntity serversEntity = serversManager.getServersById(serverId);
		return CommonUtils.msg(serversEntity);
	}

	@Override
	public R updateServers(ServersEntity serversEntity) {
		int count = serversManager.updateServers(serversEntity);
		return CommonUtils.msg(count);
	}
}