package com.cethik.irmp.web.controller;

import com.cethik.irmp.common.dal.entity.SysUserDO;
import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.web.common.utils.ShiroUtils;
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

//    protected SysUserDO getUser() {
//        SysUserDO sysUserDO =new SysUserDO();
//        sysUserDO.setUserId(1L);
//        sysUserDO.setOrgId(1L);
//        sysUserDO.setUsername("admin");
//        sysUserDO.setStatus(1);
//      //  return ShiroUtils.getUserEntity();
//        return sysUserDO;
//    }

//    protected SysUserDO getUser() {
//        try {
//            SysUser sysUser=ShiroUtils.getUserEntity();
//            return ShiroUtils.getUserEntity();
//        }catch (Exception ex){
//            logger.error(ex.getMessage());
//            return null;
//        }
//    }

    protected SysUser getUser() {
        try {
            SysUser sysUser=ShiroUtils.getUserEntity();
            return ShiroUtils.getUserEntity();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
