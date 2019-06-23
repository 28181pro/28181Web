package com.cethik.irmp.modules.sys.controller;

import java.io.IOException;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.MD5Utils;
import com.cethik.irmp.common.utils.ShiroUtils;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.common.utils.MD5Utils;
import com.cethik.irmp.common.utils.ShiroUtils;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.sys.service.SysUserService;

/**
 * 用户controller
 *
 * @author daniel.yu
 * @date 2019年6月18日 下午2:48:50
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 登录
	 */
	@SysLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password)throws IOException {
		SysUserEntity user = sysUserService.getByUserName(username);
		password = MD5Utils.encrypt(username, password);
		
		if(user == null || !user.getPassword().equals(password)) {
			return R.error("用户名或密码错误");
		}
		
		if(user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}
	    
		return sysUserService.saveUserToken(user.getUserId());
	}
	
	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public R logout() {
		R r = sysUserService.updateUserToken(getUserId());
		ShiroUtils.logout();
		return r;
	}
	
}
