package com.cethik.irmp.web.common.support.oauth2;

import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.core.model.SysUserToken;
import com.cethik.irmp.core.service.manager.SysUserManager;
import com.cethik.irmp.web.common.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 认证
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserManager sysUserManager;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        try {
            Long userId = ShiroUtils.getUserId();
            //用户角色
            Set<String> rolesSet = sysUserManager.listUserRoles(userId);
            //用户权限
            Set<String> permsSet = sysUserManager.listUserPerms(userId);
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(rolesSet);
            info.setStringPermissions(permsSet);
            return info;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            String accessToken = (String) token.getPrincipal();

            //根据accessToken，查询用户信息
            SysUserToken sysUserToken = sysUserManager.getByToken(accessToken);
            //token失效
            if (sysUserToken == null || sysUserToken.getGmtExpire().getTime() < System.currentTimeMillis()) {
                throw new IncorrectCredentialsException("token失效，请重新登录");
            }

            //查询用户信息
            SysUser sysUser = sysUserManager.getById(sysUserToken.getUserId());
            //账号锁定
            if (sysUser.getStatus() == 0) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, accessToken, getName());
            return info;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
}
