package com.cethik.irmp.controller;

import com.cethik.irmp.config.AuthRealm;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.UserInfo;
import com.cethik.irmp.service.UserService;
import com.cethik.irmp.util.DataTablesResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 直接返回内容
 */
@RestController//注意与@Controller的区别
@RequestMapping(value = "user")
@RequiresPermissions("user")//整个类具有user权限才能访问
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    AuthRealm authRealm;

    /**
     * 查询所有
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/all")
    @RequiresPermissions("user/index")
    public BaseResponse<?> all() {
        return BaseResponse.buildSuccessResponse(userService.selectAll());
    }


    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/selectPage")
    @RequiresPermissions("user/index")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        String sEcho = request.getParameter("sEcho");
        Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始索引
        Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
        System.out.println("iDisplayStart:" + iDisplayStart);
        System.out.println("iDisplayLength:" + iDisplayLength);
        Integer pagenum = 1 + iDisplayStart / iDisplayLength;

        PageHelper.startPage(pagenum, iDisplayLength);
        List<UserInfo> users = userService.selectAll();
        PageInfo pageResult = new PageInfo(users);

        DataTablesResult<UserInfo> result = new DataTablesResult();
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
    @RequiresPermissions("user/create")
    public BaseResponse<?> create(UserInfo user) {
        try {

            user.setUsertype(1);
            userService.create(user);
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
    @RequiresPermissions("user/edit")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model) {
        UserInfo userInfo = userService.detail(id);
        model.addAttribute("userinfo", userInfo);
        return new ModelAndView("user/edit");
    }

    /**
     * 编辑提交 submit
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/update")
    @RequiresPermissions("user/edit")
    public BaseResponse<?> update(@Validated UserInfo user) {
//        beanValidate(bindingResult);
        userService.edit(user);
        return BaseResponse.buildSuccessResponse("修改成功");
    }

    /**
     * 获取明细信息
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/query")
    @RequiresPermissions("user/detail")
    public BaseResponse<?> query(@RequestParam(value = "id") Integer id) {
        return BaseResponse.buildSuccessResponse(userService.detail(id));
    }

    /**
     * 明细页面 model view
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping("/detail/{id}")
    @RequiresPermissions("user/detail")
    public ModelAndView detail(@PathVariable("id") Integer id, Model model) {
        UserInfo userInfo = userService.detail(id);
        model.addAttribute("userinfo", userInfo);
        return new ModelAndView("user/detail");
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user/delete")
    public BaseResponse<?> delete(@RequestParam(value = "ids") String ids) {
        userService.deleteBatch(ids);
        return BaseResponse.buildSuccessResponse("删除成功");
    }

    @RequestMapping("/name")
    public BaseResponse<?> name(HttpServletRequest requst,HttpSession session) {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return BaseResponse.buildSuccessResponse(user);
    }

    /**
     * 查询权限信息,显示对应菜单
     * @return
     */
    @RequestMapping("/queryAuth")
    public BaseResponse<?> queryAuth(){
        Subject subject= SecurityUtils.getSubject();
        AuthorizationInfo authorizationInfo = authRealm.doGetAuthorizationInfo(subject.getPrincipals());
        return BaseResponse.buildSuccessResponse(authorizationInfo);
    }

}
