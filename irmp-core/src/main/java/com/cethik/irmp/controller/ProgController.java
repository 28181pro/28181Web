package com.cethik.irmp.controller;

import com.alibaba.fastjson.JSON;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Prog;
import com.cethik.irmp.model.Role;
import com.cethik.irmp.service.ProgService;
import com.cethik.irmp.service.RoleService;
import com.cethik.irmp.util.ShiroUtils;
import com.cethik.irmp.vmodel.Nestable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongping on 2018/09/04.
 */
@RestController
@RequestMapping(value = "prog")
//@RequiresPermissions("user")//整个类具有user权限才能访问
public class ProgController extends BaseController {

    private static final Logger logger = LoggerFactory
            .getLogger(ProgController.class);


    @Autowired
    private ProgService progService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public BaseResponse<?> getMenu() throws Throwable {
        List<Prog> userMenu = ShiroUtils.getUser().getMenus();
        return BaseResponse.buildSuccessResponse(userMenu);
    }

    @RequestMapping("/querylist")
    @RequiresPermissions("prog/index")
    public BaseResponse<?> queryAll() {
        return BaseResponse.buildSuccessResponse(progService.selectAll());
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("prog/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        Prog prog = progService.detail(id);
        model.addAttribute("prog", prog);

        List<Role> allRoles = roleService.selectByParent(-1);
        List<Role> authRoles = roleService.selectByProgId(id);
        List<Role> notAuthRoles = new ArrayList<>();

        for(Role r : allRoles){
            if(!myListContains(authRoles, r)){
                notAuthRoles.add(r);
            }
        }

        model.addAttribute("authRoles", authRoles);
        model.addAttribute("notAuthRoles", notAuthRoles);

        return new ModelAndView("prog/detail");
    }

    /**
     * 移动菜单项
     * Created by zhongping on 2018/09/14.
     */
    @RequestMapping("/Reset")
    @RequiresPermissions("prog/edit")
    public BaseResponse<?> reset(@Validated String nestables) {
//        beanValidate(bindingResult);
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        List<Nestable> nest = JSON.parseArray(nestables, Nestable.class);
        progService.sort(nest, -1);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    /**
     * 新增提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/save")
    @RequiresPermissions("prog/create")
    public BaseResponse<?> create(Prog prog) {
        try {
            progService.create(prog);
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
    @RequiresPermissions("prog/edit")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model) {
        Prog prog = progService.detail(id);
        model.addAttribute("prog", prog);
        return new ModelAndView("prog/edit");
    }

    /**
     * 编辑提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/update")
    @RequiresPermissions("prog/edit")
    public BaseResponse<?> update(@Validated Prog prog) {
//        beanValidate(bindingResult);
        progService.edit(prog);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("prog/delete")
    public BaseResponse<?> delete(@PathVariable("id") Integer id) {
        progService.delete(id);
        return BaseResponse.buildSuccessResponse("删除成功");
    }

    private boolean myListContains(List<Role> sourceList, Role element) {
        if (sourceList == null || element == null){
            return false;
        }
        if (sourceList.isEmpty()){
            return false;
        }
        for (Role tip : sourceList){
            if(element.getId().equals(tip.getId())){
                return true;
            }
        }
        return false;
    }
}
