package com.cethik.irmp.controller;

import com.cethik.irmp.config.AuthRealm;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Systemlog;
import com.cethik.irmp.service.SystemlogService;
import com.cethik.irmp.util.DataTablesResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 直接返回内容
 */
@RestController//注意与@Controller的区别
@RequestMapping(value = "systemlog")
@RequiresPermissions("systemlog")//整个类具有user权限才能访问
public class SystemlogController extends BaseController {

    @Autowired
    SystemlogService systemlogService;

    @Autowired
    AuthRealm authRealm;

    /**
     * 查询所有
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/all")
    @RequiresPermissions("systemlog/index")
    public BaseResponse<?> all() {
        return BaseResponse.buildSuccessResponse(systemlogService.selectAll());
    }


    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/selectPage")
    @RequiresPermissions("systemlog/index")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        String sEcho = request.getParameter("sEcho");
        Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始索引
        Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
        System.out.println("iDisplayStart:" + iDisplayStart);
        System.out.println("iDisplayLength:" + iDisplayLength);
        Integer pagenum = 1 + iDisplayStart / iDisplayLength;

        PageHelper.startPage(pagenum, iDisplayLength);
        List<Systemlog> systemlogs = systemlogService.selectAll();
        PageInfo pageResult = new PageInfo(systemlogs);

        DataTablesResult<Systemlog> result = new DataTablesResult();
        result.setsEcho(sEcho);
        result.setAaData(pageResult.getList());
        result.setiTotalDisplayRecords(pageResult.getTotal());

        return BaseResponse.buildSuccessResponse(result);
    }

    /**
     * 获取明细信息
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/query")
    @RequiresPermissions("systemlog/detail")
    public BaseResponse<?> query(@RequestParam(value = "id") Integer id) {
        return BaseResponse.buildSuccessResponse(systemlogService.detail(id));
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("systemlog/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        Systemlog systemlog = systemlogService.detail(id);
        model.addAttribute("systemlog", systemlog);
        return new ModelAndView("Systemlog/detail");
    }
}
