package com.cethik.irmp.modules.sys.controller;

import java.util.Map;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysLogEntity;
import com.cethik.irmp.modules.sys.service.SysLogService;

/**
 * 系统日志
 *
 * @author daniel.yu
 * @date 2019年6月19日 下午10:01:36
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {

	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 日志列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
		return sysLogService.listLog(params);
	}
	
	/**
	 * 删除日志
	 * @param id
	 * @return
	 */
	@SysLog("删除日志")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysLogService.batchRemove(id);
	}
	
	/**
	 * 清空日志
	 * @return
	 */
	@SysLog("清空日志")
	@RequestMapping("/clear")
	public R batchRemoveAll() {
		return sysLogService.batchRemoveAll();
	}
	
}
