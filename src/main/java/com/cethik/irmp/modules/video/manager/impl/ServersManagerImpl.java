package com.cethik.irmp.modules.video.manager.impl;

import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.Query;
import com.cethik.irmp.modules.video.dao.ServersMapper;
import com.cethik.irmp.modules.video.entity.ServersEntity;
import com.cethik.irmp.modules.video.manager.ServersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务器管理
 *
 * @author daniel.yu
 * @Date 2019/8/19 19:26
 */
@Component("serversManager")
public class ServersManagerImpl implements ServersManager {

    @Autowired
    private ServersMapper serversMapper;

    @Override
    public List<ServersEntity> listServersPage(Page<ServersEntity> page, Query search){
        return serversMapper.listForPage(page, search);
    }

    @Override
    public List<ServersEntity> listServers() {
        return serversMapper.list();
    }

    @Override
    public int saveServers(ServersEntity serversEntity) {
        return serversMapper.save(serversEntity);
    }

    @Override
    public ServersEntity getServersById(Long id) {
        return serversMapper.getObjectById(id);
    }

    @Override
    public int updateServers(ServersEntity serversEntity) {
        return serversMapper.update(serversEntity);
    }
    @Override
    public int batchRemove(Long[] id) {
        int count=serversMapper.batchRemove(id);
        return count;
    }
}
