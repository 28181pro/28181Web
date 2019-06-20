package com.cethik.irmp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统页面视图
 *
 * @Auther daniel.yu
 * @Date 2018/9/16 16:25
 */
@Controller
public class SysPageController {

	/**
	 * 页面跳转
	 * @param module
	 * @param function
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{function}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("function") String function,
			@PathVariable("url") String url) {
		return module + "/" + function + "/" + url;
	}
	
	/**
	 * 页面跳转
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
		return module + "/" + url;
	}

//	@RequestMapping("/testlist")
//	public String  testlist(Model model) {
//		List<String> list = new ArrayList<String>();
//		list.add("aa");
//		list.add("bb");
//		list.add("cc");
//		model.addAttribute("items", list);
//		model.addAttribute("name","seawater666");
//		return "base/log/testlist";
//
//	}


}
