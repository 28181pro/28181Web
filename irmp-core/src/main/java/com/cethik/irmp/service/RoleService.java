/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.RoleMapper;
import com.cethik.irmp.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Role service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Service
public class RoleService extends BaseService  {
    private final static Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleMapper mapper;

    public void create(Role model) throws Exception {
        try {
            log.debug("create model: {}", model.getName());
            Integer newid = getTableSequence(model);
            model.setId(newid);
            mapper.create(model);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Role model) {
        log.debug("edit model: {}", model.getName());
        mapper.edit(model);
    }


    public Role detail(Integer id) {
        log.debug("detail role: {}", id);
        return mapper.detail(id);
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(String ids) {
        String[] arrID = ids.split(",");
        for (String id : arrID) {
            mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    public List<Role> selectAll(){
        return mapper.selectAll();
    }

    public List<Role> roleList(String account){
        return mapper.selectByUserAccount(account);
    }

    public List<Role> selectByParent(Integer pid){
        return mapper.selectByParent(pid);
    }

    public List<Role> selectByProgId(Integer progid){
        return mapper.selectByProgId(progid);
    }
}
