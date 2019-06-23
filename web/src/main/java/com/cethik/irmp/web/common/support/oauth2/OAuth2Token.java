package com.cethik.irmp.web.common.support.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @Auther daniel.yu
 * @Date 2019/6/20 17:54
 */
public class OAuth2Token implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
