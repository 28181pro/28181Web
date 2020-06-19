package com.cethik.irmp.modules.video.controller;

import com.cethik.irmp.common.annotation.SysLog;
import com.cethik.irmp.common.entity.Page;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.video.entity.LocationEntity;
import com.cethik.irmp.modules.video.service.LocationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单controller
 *
 * @author daniel.yu
 * @date 2019年6月17日 上午12:23:44
 */
@RestController
@RequestMapping("/video/location")
public class  LocationController extends AbstractController {

	@Resource
	private LocationService locationService;
	
	/**
	 * 用户菜单
	 * @return
	 */
	@RequestMapping("/user")
	public R user(){
		return locationService.listUserLocation(getUserId());
	}
	
	/**
	 * 菜单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public List<LocationEntity> listLocation(@RequestParam Map<String, Object> params) {
		try {
			List<LocationEntity> pages = locationService.listLocation(params);
			return pages;
		}catch (Exception ex){
			logger.error(ex.getMessage());

			return new LinkedList<LocationEntity>();
		}
	}
	
	/**
	 * 选择菜单(添加、修改)
	 * @return
	 */
	@RequestMapping("/select")
	public R select() {
		return locationService.listNotLine();
	}

	/**
	 * 选择菜单(添加、修改)
	 * @return
	 */
	@RequestMapping("/selectline")
 	public R selectline() {
		return locationService.listLine();
	}
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@SysLog("新增菜单")
	@RequestMapping("/save")
	public R save(@RequestBody LocationEntity menu) {
		return locationService.saveLocation(menu);
	}

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long id) {
		return locationService.getLocationById(id);
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	public R update(@RequestBody LocationEntity menu) {
		return locationService.updateLocation(menu);
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@SysLog("删除菜单")
	@RequestMapping("/remove")
	public R remove(@RequestBody Long[] id) {
		return locationService.batchRemove(id);
	}
	
}
