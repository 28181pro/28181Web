/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.mapper;

import com.cethik.irmp.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * role mapper-JZGL
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-04
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectAll();
    List<Role> selectByUserAccount(String account);
    List<Role> selectByParent(Integer pid);
    List<Role> selectByProgId(Integer progid);
}
