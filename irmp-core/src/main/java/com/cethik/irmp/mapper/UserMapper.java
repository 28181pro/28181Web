/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.UserInfo;
import com.cethik.irmp.vmodel.CurrentUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * user mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
    void deleteByAccount(String account);
    List<UserInfo> selectAll();
    List<UserInfo> selectByRoleId(Integer roleId);
    List<UserInfo> findByUsernameAndPassword(UserInfo user);
    UserInfo selectByAccount(String account);
    CurrentUser loginByAccount(String account);

}
