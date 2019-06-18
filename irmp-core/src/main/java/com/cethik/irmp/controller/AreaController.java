package com.cethik.irmp.controller;

import com.cethik.irmp.config.AuthRealm;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.helper.LogHelper;
import com.cethik.irmp.model.Area;
import com.cethik.irmp.service.AreaService;
import com.cethik.irmp.util.DataTablesResult;
import com.cethik.irmp.util.ShiroUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by zhongping on 2018/09/25.
 */

/**
 * 直接返回内容
 */
@RestController
@RequestMapping(value = "area")
public class AreaController extends BaseController {

    @Autowired
    AreaService areaService;

    @Autowired
    AuthRealm authRealm;

    /**
     * 查询所有
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/queryList")
    @RequiresPermissions("area/index")
    public BaseResponse<?> queryAll() {
        try {
            return BaseResponse.buildSuccessResponse(areaService.selectAll());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            LogHelper.log(ex);
        }
        return BaseResponse.buildFailResponse(203, "异常!");
    }


    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/selectPage")
    @RequiresPermissions("area/index")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        try {

            String sEcho = request.getParameter("sEcho");
            Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始索引
            Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
            System.out.println("iDisplayStart:" + iDisplayStart);
            System.out.println("iDisplayLength:" + iDisplayLength);
            Integer pagenum = 1 + iDisplayStart / iDisplayLength;

            PageHelper.startPage(pagenum, iDisplayLength);
            List<Area> areas = areaService.selectAll();
            PageInfo pageResult = new PageInfo(areas);

            DataTablesResult<Area> result = new DataTablesResult();
            result.setsEcho(sEcho);
            result.setAaData(pageResult.getList());
            result.setiTotalDisplayRecords(pageResult.getTotal());

            return BaseResponse.buildSuccessResponse(result);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            LogHelper.log(ex);
        }

        return BaseResponse.buildFailResponse(203, "异常!");
    }

    /**
     * 新增提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/save")
    @RequiresPermissions("area/create")
    public BaseResponse<?> create(Area area) {
        try {

            area.setState(1);
            area.setCreateTime(new Date());
            area.setCreator(ShiroUtils.getUserId());
            areaService.create(area);
            return BaseResponse.buildSuccessResponse("添加成功");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return BaseResponse.buildFailResponse(203, "SQL异常!");
    }


    /**
     * 编辑页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("area/edit")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model) {
        Area area = areaService.detail(id);
        model.addAttribute("area", area);
        return new ModelAndView("area/edit");
    }

    /**
     * 编辑提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/update")
    @RequiresPermissions("area/edit")
    public BaseResponse<?> update(@Validated Area model) {
//        beanValidate(bindingResult);
        areaService.edit(model);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    /**
     * 获取明细信息
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/query")
    @RequiresPermissions("area/detail")
    public BaseResponse<?> query(@RequestParam(value = "id") Integer id) {
        return BaseResponse.buildSuccessResponse(areaService.detail(id));
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("area/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        Area area = areaService.detail(id);
        model.addAttribute("area", area);
        return new ModelAndView("area/detail");
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("area/delete")
    public BaseResponse<?> delete(@PathVariable("id") Integer id) {
        areaService.delete(id);
        return BaseResponse.buildSuccessResponse("删除成功");
    }
}
