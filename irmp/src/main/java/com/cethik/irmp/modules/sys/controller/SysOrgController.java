package com.cethik.irmp.modules.sys.controller;

import java.util.List;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.entity.SysOrgEntity;
import com.cethik.irmp.modules.sys.service.SysOrgService;

/**
 * 组织机构
 *
 * @author daniel.yu
 * @date 2019年6月22日 上午11:35:00
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends AbstractController {

	@Autowired
	private SysOrgService sysOrgService;
	
	/**
	 * 机构列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysOrgEntity> list() {
		return sysOrgService.listOrg();
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysOrgEntity> select() {
		return sysOrgService.listOrgTree();
	}
	
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("新增部门")
	@RequestMapping("/save")
	public R save(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveOrg(org);
	}
	
	/**
	 * 根据id查询机构详情
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long orgId) {
		return sysOrgService.getOrg(orgId);
	}
	
	/**
	 * 修改部门
	 * @param org
	 * @return
	 */
	@SysLog("修改部门")
	@RequestMapping("/update")
	public R update(@RequestBody SysOrgEntity org) {
		return sysOrgService.updateOrg(org);
	}
	
	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	@SysLog("删除部门")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysOrgService.bactchRemoveOrg(id);
	}
	
}
