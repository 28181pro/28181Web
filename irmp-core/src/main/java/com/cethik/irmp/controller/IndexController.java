package com.cethik.irmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.cethik.irmp.helper.LogHelper;
import com.cethik.irmp.util.ShiroUtils;
import com.cethik.irmp.vmodel.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhongping on 2018/09/05.
 */

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    private static final Logger logger = LoggerFactory
            .getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("username", ShiroUtils.getUsername());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        LogHelper.logPage();
        return "login";
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @RequestMapping("/{page}")
    public String toPage(@PathVariable("page") String page) {
        logger.debug("-------------toindex------------" + page);
        return page;
    }

    @RequestMapping("/{page}/index")
    public String toPageIndex(@PathVariable("page") String page) {
        logger.debug("-------------to-page-index------------" + page);
        return page + "/index";
    }

    @RequestMapping({"/{page}/create", "/{page}/create/{id}"})
    public String toPageCreate(@PathVariable("page") String page, @PathVariable(value = "id",  required = false) Integer id, Model model) {
        logger.debug("-------------to-page-create------------" + page);
        model.addAttribute("pid", id);
        return page + "/create";
    }

    @RequestMapping({"/{page}/choose", "/{page}/choose/{id}"})
    public String choose(@PathVariable("page") String page) {
        logger.debug("-------------to-page-choose------------" + page);
        return page + "/choose";
    }

//	@RequestMapping("/{page}/edit/{id}")
//	public String toPageEdit(@PathVariable("page") String page, @PathVariable("id") Integer id, Model model) {
//		logger.debug("-------------to-page-edit------------" + page + ";id: " + id);
//		model.addAttribute("ssid", id);
//		return page + "/edit";
//	}

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResponseEntity<?> doLogin(String account, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        JSONObject jsonObject = new JSONObject();
        try {
            subject.login(token);
            CurrentUser user = (CurrentUser) subject.getPrincipal();
            subject.getSession().setAttribute("sessionId", user);
            jsonObject.put("username", account);
            jsonObject.put("msg", "登录成功");
            LogHelper.logOn();
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
            return ResponseEntity.status(400).body(jsonObject);
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在" + e.getMessage());
            return ResponseEntity.ok(jsonObject);
        }
        return ResponseEntity.ok(jsonObject);
    }
}
