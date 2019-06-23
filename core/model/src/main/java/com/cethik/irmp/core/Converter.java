package com.cethik.irmp.core;

import com.cethik.irmp.common.dal.entity.SysUserDO;
import com.cethik.irmp.common.dal.entity.SysUserTokenDO;
import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.core.model.SysUserToken;
import org.springframework.beans.BeanUtils;

/**
 * @Description
 * @Auther daniel.yu
 * @Date 2019/6/20 19:10
 **/
public class Converter {

    public static SysUser ToSysUserDTO(SysUserDO sysUserDO) {
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(sysUserDO,sysUser);
        return sysUser;
    }

    public static SysUserDO ToSysUserDO(SysUser sysUser) {
        SysUserDO sysUserDO =new SysUserDO();
        BeanUtils.copyProperties(sysUser, sysUserDO);
        return sysUserDO;
    }


    public static SysUserToken ToSysUserTokenDTO(SysUserTokenDO sysUserTokenDO) {
        SysUserToken sysUserToken =new SysUserToken();
        BeanUtils.copyProperties(sysUserTokenDO,sysUserToken);
        return sysUserToken;
    }

    public static SysUserTokenDO ToSysUserTokenDO(SysUserToken sysUserToken) {
        SysUserTokenDO sysUserTokenDO =new SysUserTokenDO();
        BeanUtils.copyProperties(sysUserToken, sysUserTokenDO);
        return sysUserTokenDO;
    }

//    public static SysUser ToSysUserDTO(SysUserDO sysUserDO) {
//        SysUser sysUser=new SysUser();
//        BeanUtils.copyProperties(sysUserDO,sysUser);
//        return sysUser;
//    }
//
//    public static SysUserDO ToSysUserDO(SysUser sysUser) {
//        SysUserDO sysUserDO =new SysUserDO();
//        BeanUtils.copyProperties(sysUser, sysUserDO);
//        return sysUserDO;
//    }


}
