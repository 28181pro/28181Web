package com.cethik.irmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Channel;
import com.cethik.irmp.model.GBDevice;
import com.cethik.irmp.service.ChannelService;
import com.cethik.irmp.service.GBDeviceService;
import com.cethik.irmp.util.DataTablesResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import com.cethik.irmp.model.Servers;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChannelController  extends BaseController {

    @Autowired
    ChannelService  channelService;


    @RequestMapping("/Video/getChannelInfo/{deviceid}")
    public String getChannelInfo( @PathVariable("deviceid") String deviceid) {
        //String sEcho = request.getParameter("sEcho");
        Channel channel = channelService.selectByCode( deviceid );

        JSONObject json = new JSONObject();
        json.put("aData", channel);

        return "124.91.150.149";
    }

    @RequestMapping("/Video/index/{deviceid}")
    public ModelAndView play( @PathVariable("deviceid") String deviceid , Model model)
     {

         Channel channel = channelService.selectByCode( deviceid );

         //String result = JSON.toJSONString( channel );
         model.addAttribute("channel", channel);
         //model.addAttribute("channel", "124.91.150.149");
        return new ModelAndView("Video/play");

    }
}
