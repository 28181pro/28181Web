package com.cethik.irmp.controller;

import com.cethik.irmp.config.AuthRealm;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Corp;
import com.cethik.irmp.service.CorpService;
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
import java.util.List;

/**
 * Created by zhongping on 2018/09/25.
 */

/**
 * 直接返回内容
 */
@RestController//注意与@Controller的区别
@RequestMapping(value = "corp")
public class CorpController extends BaseController {

    @Autowired
    CorpService corpService;

    @Autowired
    AuthRealm authRealm;

    /**
     * 查询所有
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/QueryList")
    @RequiresPermissions("Corp/Index")
    public BaseResponse<?> queryAll() {
        return BaseResponse.buildSuccessResponse(corpService.selectAll());
    }


    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/selectPage")
    @RequiresPermissions("Corp/Index")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        String sEcho = request.getParameter("sEcho");
        Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始索引
        Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
        System.out.println("iDisplayStart:" + iDisplayStart);
        System.out.println("iDisplayLength:" + iDisplayLength);
        Integer pagenum = 1 + iDisplayStart / iDisplayLength;

        PageHelper.startPage(pagenum, iDisplayLength);
        List<Corp> areas = corpService.selectAll();
        PageInfo pageResult = new PageInfo(areas);

        DataTablesResult<Corp> result = new DataTablesResult();
        result.setsEcho(sEcho);
        result.setAaData(pageResult.getList());
        result.setiTotalDisplayRecords(pageResult.getTotal());

        return BaseResponse.buildSuccessResponse(result);
    }

    /**
     * 新增提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/save")
    @RequiresPermissions("corp/create")
    public BaseResponse<?> create(Corp corp) {
        try {
            corp.setState(1);
            corp.setCreator(ShiroUtils.getUserId());
            corpService.create(corp);
            return BaseResponse.buildSuccessResponse("添加成功");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

        return BaseResponse.buildFailResponse(203, "SQL异常!");
    }


    /**
     * 编辑页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("corp/edit")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model) {
        Corp corp = corpService.detail(id);
        model.addAttribute("corp", corp);
        return new ModelAndView("corp/edit");
    }

    /**
     * 编辑提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/update")
    @RequiresPermissions("corp/edit")
    public BaseResponse<?> update(@Validated Corp model) {
//        beanValidate(bindingResult);
        corpService.edit(model);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    /**
     * 获取明细信息
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/query")
    @RequiresPermissions("corp/detail")
    public BaseResponse<?> query(@RequestParam(value = "id") Integer id) {
        return BaseResponse.buildSuccessResponse(corpService.detail(id));
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("corp/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        Corp corp = corpService.detail(id);
        model.addAttribute("corp", corp);
        return new ModelAndView("corp/detail");
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("corp/delete")
    public BaseResponse<?> delete(@PathVariable("id") Integer id) {
        corpService.delete(id);
        return BaseResponse.buildSuccessResponse("删除成功");
    }
}
