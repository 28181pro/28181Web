package com.cethik.irmp.web.controller;

import com.cethik.irmp.common.util.entity.R;
import com.cethik.irmp.common.util.utils.MD5Utils;
import com.cethik.irmp.core.model.SysUser;
import com.cethik.irmp.core.model.SysUserToken;
import com.cethik.irmp.core.service.manager.SysUserManager;
import com.cethik.irmp.web.common.annotation.OperateLog;
import com.cethik.irmp.web.common.support.oauth2.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 用户controller
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    /**
     * token过期时间，12小时
     */
    private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;

    @Autowired
    private SysUserManager sysUserManager;

    /**
     * 登录
     */
    @OperateLog("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(String username, String password) throws IOException {
//		SysUser user = sysUserService.getByUserName(username);
//		password = MD5Utils.encrypt(username, password);
//
//		if(user == null || !user.getPassword().equals(password)) {
//			return R.error("用户名或密码错误");
//		}
//
//		if(user.getStatus() == 0) {
//			return R.error("账号已被锁定,请联系管理员");
//		}
//
//		return sysUserService.saveUserToken(user.getUserId());


//		//当前时间
//		Date now = new Date();
//		Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
//		R r = R.ok().put("token", token).put("expire", gmtExpire);
//		return r;

        try {
            SysUser sysUser = sysUserManager.getByUserName(username);
            password = MD5Utils.encrypt(username, password);

            if (sysUser == null || !sysUser.getPassword().equals(password)) {
                return R.error("用户名或密码错误");
            }

            if (sysUser.getStatus() == 0) {
                return R.error("账号已被锁定,请联系管理员");
            }

            //生成token
            String token = TokenGenerator.generateValue();
            SysUserToken sysUserToken = sysUserManager.saveUserToken(sysUser.getUserId(), token);
            R r = R.ok().put("token", sysUserToken.getToken()).put("expire", sysUserToken.getGmtExpire());
            logger.info(sysUser.getUsername()+"登录成功");
            return r;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }

    }


}
