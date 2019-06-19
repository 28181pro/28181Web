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

//    @RequestMapping("/list")
//    public Page<SysLogEntity> listLog(@RequestBody Map<String, Object> params, Model model) {
//        try {
//            Query query = new Query(params);
//            Page<SysLogEntity> page = new Page<>(query);
//        List<SysLogEntity> sysLogEntityList=    sysLogManager.listLog(page, query);
//
//            model.addAttribute("rows", sysLogEntityList);
//            return page;
//        }catch (Exception ex){
//            String s1=ex.getMessage();
//            System.out.print(ex.getMessage());
//            return  null;
//        }
//
//    }

    /**
     * 日志列表
     *
     * @param params
     * @return
     */
//	@RequestMapping("/list")
//	public Page<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
//		try{
//			Query query = new Query(params);
//			Page<SysLogEntity> page = new Page<>(query);
//			sysLogManager.listLog(page, query);
//			return page;
//		}catch (Exception ex){
//			String s=ex.getMessage();
//			System.out.print(ex.getMessage());
//			return new Page<SysLogEntity>();
//		}
//
//	}

//	@RequestMapping("/list")
//	public List<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
//		try{
//			Query query = new Query(params);
//			//Page<SysLogEntity> page = new Page<>(query);
//			//sysLogManager.listLog(page, query);
//			sysLogManager.listLog(query);
//			return null;
//		}catch (Exception ex){
//			String s=ex.getMessage();
//			System.out.print(ex.getMessage());
//			return null;
//		}
//	}

//    @RequestMapping("/list")
//    public PageInfo<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
//        try{
//            Query query = new Query(params);
//            PageInfo2<SysLogEntity> page = new PageInfo2<>(query);
//            List<SysLogEntity> sysLogEntityList= sysLogManager.listLog(page, query);
//            PageInfo<SysLogEntity> pageInfo=new PageInfo(sysLogEntityList);
//          //  model.addAttribute("name","7777seawater888");
//            return pageInfo;
//        }catch (Exception ex){
//            String s=ex.getMessage();
//            System.out.print(ex.getMessage());
//            return new PageInfo<SysLogEntity>();
//        }
//    }
    @RequestMapping("/list")
    public PageResult listLog(@RequestBody Map<String, Object> params) {
        try {
            Query query = new Query(params);
////            PageInfo2<SysLogEntity> page = new PageInfo2<>(query);
////            List<SysLogEntity> sysLogEntityList= sysLogManager.listLog(page, query);
////            PageInfo<SysLogEntity> pageInfo=new PageInfo(sysLogEntityList);
////            //  model.addAttribute("name","7777seawater888");
////            return pageInfo;
////
//         //   Page<Object> page = PageHelper.offsetPage(0, 10);
//            int pageNum = query.getAsInt("pageNumber");
//            int pageSize = query.getAsInt("pageSize");
//            Page<Object> page=  PageHelper.startPage(pageNum,pageSize);
//            List<SysLogEntity> sysLogEntityList= sysLogManager.listLog(new PageInfo2<SysLogEntity>(), query);
//           // List<Per> pers = perService.getPers(name, age);
//
//            return new PageResult(page.getTotal(), sysLogEntityList);
            return sysLogManager.listForPage(query);
        } catch (Exception ex) {
            String s = ex.getMessage();
            System.out.print(ex.getMessage());
            return new PageResult(0, null);
        }
    }


//    @RequestMapping("/list")
//    public PageInfo2<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
//        try{
//            Query query = new Query(params);
//            PageInfo2<SysLogEntity> page = new PageInfo2<>(query);
//           sysLogManager.listLog(page, query);
//            return page;
//        }catch (Exception ex){
//            String s=ex.getMessage();
//            System.out.print(ex.getMessage());
//            return new PageInfo2<SysLogEntity>();
//        }
//    }

//    @RequestMapping("/list")
//    public List<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
//        try{
//            Query query = new Query(params);
//            PageInfo2<SysLogEntity> page = new PageInfo2<>(query);
//            List<SysLogEntity> sysLogEntityList= sysLogManager.listLog(page, query);
//            PageInfo<SysLogEntity> pageInfo=new PageInfo(sysLogEntityList);
//            return sysLogEntityList;
//        }catch (Exception ex){
//            String s=ex.getMessage();
//            System.out.print(ex.getMessage());
//            return null;
//        }
//
//    }

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
