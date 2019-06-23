package com.cethik.irmp.web.controller;

import com.cethik.irmp.common.dal.dao.master.SysMenuMapper;
import com.cethik.irmp.common.dal.dao.master.SysUserMapper;
import com.cethik.irmp.common.dal.entity.SysMenuDO;
import com.cethik.irmp.common.util.constant.SystemConstant;
import com.cethik.irmp.common.util.entity.R;
import com.cethik.irmp.web.common.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单controller
 *
 * @Auther daniel.yu
 * @Date 2018/9/13 20:39
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    //	@Resource
//	private SysMenuService sysMenuService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 用户菜单
     *
     * @return
     */
    @OperateLog("查询用户菜单")
    @RequestMapping("/user")
    public R user() {
        try {


//查询根菜单列表
            long userId = 1;
            List<SysMenuDO> menuList = null;

            List<Long> menuIdList = sysUserMapper.listAllMenuId(getUserId());
            menuList = getAllMenuList(menuIdList);

            return R.ok().put("menuList", menuList);
//		return sysMenuService.listUserMenu(getUserId());
            //return R.ok();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return R.error(ex.getMessage());
        }
    }

    /**
     * 获取所有菜单列表
     */
    @OperateLog("获取所有菜单列表")
    private List<SysMenuDO> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuDO> menuList = listParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuDO> getMenuTreeList(List<SysMenuDO> menuList, List<Long> menuIdList) {
        List<SysMenuDO> subMenuList = new ArrayList<SysMenuDO>();

        for (SysMenuDO entity : menuList) {
            if (entity.getType() == SystemConstant.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    public List<SysMenuDO> listParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuDO> menuList = sysMenuMapper.listParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuDO> userMenuList = new ArrayList<>();
        for (SysMenuDO menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

//
//	/**
//	 * 菜单列表
//	 * @param params
//	 * @return
//	 */
//	@RequestMapping("/list")
//	public List<SysMenu> listMenu(@RequestParam Map<String, Object> params) {
//		return sysMenuService.listMenu(params);
//	}
//
//	/**
//	 * 选择菜单(添加、修改)
//	 * @return
//	 */
//	@RequestMapping("/select")
//	public R select() {
//		return sysMenuService.listNotButton();
//	}
//
//	/**
//	 * 新增菜单
//	 * @param menu
//	 * @return
//	 */
//	@OperateLog("新增菜单")
//	@RequestMapping("/save")
//	public R save(@RequestBody SysMenu menu) {
//		return sysMenuService.saveMenu(menu);
//	}
//
//	/**
//	 * 查询详情
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/info")
//	public R info(@RequestBody Long id) {
//		return sysMenuService.getMenuById(id);
//	}
//
//	/**
//	 * 修改菜单
//	 * @param menu
//	 * @return
//	 */
//	@OperateLog("修改菜单")
//	@RequestMapping("/update")
//	public R update(@RequestBody SysMenu menu) {
//		return sysMenuService.updateMenu(menu);
//	}
//
//	/**
//	 * 删除菜单
//	 * @param id
//	 * @return
//	 */
//	@OperateLog("删除菜单")
//	@RequestMapping("/remove")
//	public R remove(@RequestBody Long[] id) {
//		return sysMenuService.batchRemove(id);
//	}

}
