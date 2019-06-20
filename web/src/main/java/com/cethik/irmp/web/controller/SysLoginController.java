package com.cethik.irmp.web.controller;

import com.cethik.irmp.web.common.annotation.OperateLog;
import com.cethik.irmp.web.common.support.oauth2.TokenGenerator;
import com.cethik.irmp.common.util.entity.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

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

//	@Autowired
//	private SysUserService sysUserService;
	
	/**
	 * 登录
	 */
	@OperateLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password)throws IOException {
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
       //生成token
		String token = TokenGenerator.generateValue();
		//当前时间
		Date now = new Date();
		Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
		R r = R.ok().put("token", token).put("expire", gmtExpire);
		return r;
	}
	

	
}
