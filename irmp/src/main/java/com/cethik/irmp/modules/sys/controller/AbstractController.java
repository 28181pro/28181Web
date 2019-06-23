package com.cethik.irmp.modules.sys.controller;

import com.cethik.irmp.common.utils.ShiroUtils;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cethik.irmp.common.utils.ShiroUtils;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;

/**
 * Controller公共组件
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
	
}
