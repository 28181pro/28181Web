package com.cethik.irmp.controller;

import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Prog;
import com.cethik.irmp.model.Role;
import com.cethik.irmp.model.UserInfo;
import com.cethik.irmp.service.ProgService;
import com.cethik.irmp.service.RoleService;
import com.cethik.irmp.service.UserService;
import com.cethik.irmp.util.ShiroUtils;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by zhongping on 2018/09/04.
 */
@RequestMapping(value = "role", produces = APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST, RequestMethod.GET})
@RestController
public class RoleController extends BaseController {

    private static final Logger logger = LoggerFactory
            .getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProgService progService;

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public BaseResponse<?> getRoleList(Integer pid) {
        return BaseResponse.buildSuccessResponse(roleService.selectAll());
    }

    @RequestMapping(value = "/getProgList", method = RequestMethod.GET)
    public BaseResponse<?> getProgList(Integer roleid) {
        List<Prog> authProgs = progService.selectByRoleId(roleid);
        List<String> listProg = new ArrayList<>();
        for (int i = 0; i < authProgs.size(); i++) {
            listProg.add(authProgs.get(i).getId().toString());
        }
        return BaseResponse.buildSuccessResponse(String.join(",", listProg));
    }

    /**
     * 新增提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/save")
    @RequiresPermissions("user/create")
    public BaseResponse<?> create(Role role) {
        try {
            role.setPid(-1);
            role.setRoleparentid(ShiroUtils.getUser().getRoleid());
            roleService.create(role);
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
    @RequiresPermissions("role/edit")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.detail(id);
        model.addAttribute("role", role);
        return new ModelAndView("role/edit");
    }

    /**
     * 编辑提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/update")
    @RequiresPermissions("role/edit")
    public BaseResponse<?> update(@Validated Role role) {
//        beanValidate(bindingResult);
        roleService.edit(role);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("role/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.detail(id);
        model.addAttribute("role", role);

        List<UserInfo> authUsers = userService.selectByRoleId(id);
        model.addAttribute("usersize", authUsers.size());

        List<Prog> authProgs = progService.selectByRoleId(id);
        model.addAttribute("progsize", authProgs.size());
        return new ModelAndView("role/detail");
    }

    @RequestMapping("/roleprog/{id}")
    @RequiresPermissions("role/roleprog")
    public ModelAndView RoleProg(@PathVariable("id") Integer id,  Model model)
    {
        Role role = roleService.detail(id);
        model.addAttribute("role", role);

        List<Prog> authProgs = progService.selectByRoleId(id);
        List<String> listProg = new ArrayList<>();
        for (int i = 0; i < authProgs.size(); i++) {
            listProg.add(authProgs.get(i).getId().toString());
        }
        model.addAttribute("progids", String.join(",", listProg));

        List<Prog> userProgs= ShiroUtils.getUser().getMenus();
        List<String> listUserProgs = new ArrayList<>();

        for (int i = 0; i < userProgs.size(); i++) {
            listUserProgs.add(userProgs.get(i).getId().toString());
        }
        model.addAttribute("userprogids", String.join(",", listUserProgs));

        return new ModelAndView("role/roleprog");
    }

    @RequestMapping("/roleprogpreview/{id}")
    @RequiresPermissions("role/roleprog")
    public ModelAndView RoleProgPreview(@PathVariable("id") Integer id,  Model model)
    {
        Role role = roleService.detail(id);
        model.addAttribute("role", role);

        List<Prog> authProgs = progService.selectByRoleId(id);
        List<String> listProg = new ArrayList<>();
        for (int i = 0; i < authProgs.size(); i++) {
            listProg.add(authProgs.get(i).getId().toString());
        }
        model.addAttribute("progids", String.join(",", listProg));

        List<Prog> userProgs= ShiroUtils.getUser().getMenus();
        List<String> listUserProgs = new ArrayList<>();

        for (int i = 0; i < userProgs.size(); i++) {
            listUserProgs.add(userProgs.get(i).getId().toString());
        }
        model.addAttribute("userprogids", String.join(",", listUserProgs));

        return new ModelAndView("role/roleprogpreview");
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("role/delete")
    public BaseResponse<?> delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return BaseResponse.buildSuccessResponse("删除成功");
    }

}
