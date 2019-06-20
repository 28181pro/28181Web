package com.cethik.irmp.web.controller;

import com.cethik.irmp.common.dal.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 **/
public class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        SysUserEntity sysUserEntity=new SysUserEntity();
        sysUserEntity.setUserId(1L);
        sysUserEntity.setOrgId(1L);
        sysUserEntity.setUsername("admin");
        sysUserEntity.setStatus(1);
      //  return ShiroUtils.getUserEntity();
        return  sysUserEntity;
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
