package com.cethik.irmp.modules.video.manager;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;

import com.cethik.irmp.modules.video.entity.ServersEntity;

import java.util.List;

/**
 * 服务器管理
 *
 * @author daniel.yu
 * @Date 2019/8/11 19:20
 */
public interface ServersManager {
    // List<ServersEntity> listServers(Page<ServersEntity> page, Query search);
    List<ServersEntity> listServersPage(Page<ServersEntity> page, Query search);

    List<ServersEntity> listServers();

    int saveServers(ServersEntity serversEntity);

    ServersEntity getServersById(Long id);

    int updateServers(ServersEntity serversEntity);

    int batchRemove(Long[] id);
}
