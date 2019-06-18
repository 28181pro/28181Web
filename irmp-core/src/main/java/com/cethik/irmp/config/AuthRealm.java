package com.cethik.irmp.config;

import com.cethik.irmp.model.Prog;
import com.cethik.irmp.model.Role;
import com.cethik.irmp.service.ProgService;
import com.cethik.irmp.service.RoleService;
import com.cethik.irmp.service.UserService;
import com.cethik.irmp.util.StringUtils;
import com.cethik.irmp.vmodel.CurrentUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 认证和授权逻辑
 *
 * @author zhangshuhua@cetiti.com
 * @date 2018-9-6
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ProgService progService;

    /**
     * 认证信息,登录时调用
     *
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String) token.getPrincipal();
        if (!StringUtils.isNotBlank(account)) {
            return null;
        }

        CurrentUser user = userService.loginByAccount(account);
        user.setMenus(progService.selectByUserId(user.getId()));
        String password = user.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, password, account);
        return authenticationInfo;
    }

    /**
     * 授权信息,进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CurrentUser user = (CurrentUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        List<Role> roles = roleService.roleList(user.getAccount());
        for (Role r : roles) {
            authorizationInfo.addRole(r.getName());
            List<Prog> permissions = progService.selectByRoleId(r.getId());
            for (Prog p : permissions) {
//                authorizationInfo.addStringPermission(p.getId().toString());
                if (StringUtils.isNotBlank(p.getPath())) {
                    authorizationInfo.addStringPermission(p.getPath().toLowerCase());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 清楚授权缓存,修改完用户角色和权限时调用
     */
    public void clearAuthorizationCache() {
        setAuthorizationCache(null);
        String authorizationCacheName = getAuthorizationCacheName();
        getCacheManager().getCache(authorizationCacheName).clear();
    }

}
