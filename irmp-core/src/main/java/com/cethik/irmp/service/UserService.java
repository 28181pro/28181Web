/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.UserMapper;
import com.cethik.irmp.model.UserInfo;
import com.cethik.irmp.vmodel.CurrentUser;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Service
public class UserService extends BaseService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper mapper;

    public void create(UserInfo model) throws Exception {
        try {
            log.debug("create model: {}", model.getName());
            Integer newid = getTableSequence("User", "");
            model.setId(newid);
            mapper.create(model);
        }catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(UserInfo model) {
        log.debug("edit model: {}", model.getName());
        mapper.edit(model);
    }

    public UserInfo detail(Integer id) {
        log.debug("detail user: {}", id);
        return mapper.detail(id);
    }

    public void delete(String account) {
        log.debug("delete user: {}", account);
        mapper.deleteByAccount(account);
    }

    public void deleteBatch(String ids) {
        String[] arrID = ids.split(",");
        for (String id : arrID) {
            mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }


    public List<UserInfo> selectAll() {
        return mapper.selectAll();
    }

    public List<UserInfo> selectByRoleId(Integer roleId) {
        return mapper.selectByRoleId(roleId);
    }

    public boolean verifyLogin(UserInfo user) {
        List<UserInfo> userList = mapper.findByUsernameAndPassword(user);
        return userList.size() > 0;
    }

    public UserInfo selectByAccount(String account) {
        return mapper.selectByAccount(account);
    }

    public CurrentUser loginByAccount(String account) {
        CurrentUser currentUser = mapper.loginByAccount(account);
        return currentUser;
    }

    public List<UserInfo> selectListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<UserInfo> users = mapper.selectAll();
        return users;
    }
}
