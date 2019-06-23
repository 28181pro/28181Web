package com.cethik.irmp.modules.quartz.controller;

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
import com.cethik.irmp.modules.quartz.entity.QuartzJobLogEntity;
import com.cethik.irmp.modules.quartz.service.QuartzJobLogService;

/**
 * 定时任务日志
 *
 * @author daniel.yu
 * @date 2019年6月21日 上午11:48:14
 */
@RestController
@RequestMapping("/quartz/job/log")
public class QuartzJobLogController {

	@Autowired
	private QuartzJobLogService quartzJobLogService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<QuartzJobLogEntity> list(@RequestBody Map<String, Object> params) {
		return quartzJobLogService.listForPage(params);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除定时任务日志")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return quartzJobLogService.batchRemove(id);
	}
	
	/**
	 * 清空
	 * @return
	 */
	@SysLog("清空定时任务日志")
	@RequestMapping("/clear")
	public R batchRemoveAll() {
		return quartzJobLogService.batchRemoveAll();
	}
	
}
