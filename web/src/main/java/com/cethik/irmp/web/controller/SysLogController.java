package com.cethik.irmp.web.controller;


//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

//import Page;

import com.cethik.irmp.common.util.entity.PageResult;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.core.service.manager.SysLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

        import java.util.Map;

/**
 * 系统日志
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月14日 下午10:01:36
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {


    @Autowired
    private SysLogManager sysLogManager;


    @RequestMapping("/list")
    public PageResult listLog(@RequestBody Map<String, Object> params) {
        try {
            Query query = new Query(params);
            return sysLogManager.listForPage(query);
        } catch (Exception ex) {
            String s = ex.getMessage();
            System.out.print(ex.getMessage());
            return new PageResult(0, null);
        }
    }




//	/**
//	 * 删除日志
//	 * @param id
//	 * @return
//	 */
//	@SysLog("删除日志")
//	@RequestMapping("/remove")
//	public R batchRemove(@RequestBody Long[] id) {
//		return sysLogService.batchRemove(id);
//	}
//
//	/**
//	 * 清空日志
//	 * @return
//	 */
//	@SysLog("清空日志")
//	@RequestMapping("/clear")
//	public R batchRemoveAll() {
//		return sysLogService.batchRemoveAll();
//	}

}
