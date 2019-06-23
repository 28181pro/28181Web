package com.cethik.irmp.web.common.utils;

import com.cethik.irmp.common.dal.entity.SysUserDO;
import com.cethik.irmp.core.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description Shiro工具类
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 **/
public class ShiroUtils {
	
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
//	public static SysUserDO getUserEntity() {
//		return (SysUserDO)SecurityUtils.getSubject().getPrincipal();
//	}

	public static SysUser getUserEntity() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

}
