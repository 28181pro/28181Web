package com.cethik.irmp.modules.video.controller;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import com.cethik.irmp.common.constant.SystemConstant;
import com.cethik.irmp.common.entity.Page;
//import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.common.entity.R;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.sys.controller.AbstractController;
import com.cethik.irmp.modules.sys.entity.SysUserEntity;
import com.cethik.irmp.modules.video.entity.ChannelEntity;
import com.cethik.irmp.modules.video.service.ChannelService;
//import com.cethik.irmp.util.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/video/channel")
public class ChannelController  extends AbstractController {

    @Autowired
    ChannelService  channelService;


    @RequestMapping("/list")
    Page<ChannelEntity> list(@RequestBody Map<String, Object> params){
        if(getUserId() != SystemConstant.SUPER_ADMIN) {
            params.put("userIdCreate", getUserId());
        }
        Page<ChannelEntity>  pages = channelService.listChannel(params);
        return  pages ;//channelService.listChannel(params);
    }

    /*
    @RequestMapping("/play/{deviceid}")
    ModelAndView play(@PathVariable("deviceid") String deviceid , Model model){

        ChannelEntity channel = channelService.getByChannelCode( deviceid );

        //String result = JSON.toJSONString( channel );
        model.addAttribute("channel", channel);
        //model.addAttribute("channel", "124.91.150.149");
        return new ModelAndView("Video/play");

    }
*/

    /**
     * 根据id查询详情
     * @param channelCode
     * @return
     */

    @RequestMapping("/getChannelInfo")
    public R getChannelInfo(@RequestBody String channelCode) {

        return channelService.getByChannelCode( channelCode );
    }
/*
    @RequestMapping("/Video/index/{deviceid}")
    public ModelAndView play( @PathVariable("deviceid") String deviceid , Model model)
     {

         Channel channel = channelService.selectByCode( deviceid );

         //String result = JSON.toJSONString( channel );
         model.addAttribute("channel", channel);
         //model.addAttribute("channel", "124.91.150.149");
        return new ModelAndView("Video/play");

    }

     */
}
