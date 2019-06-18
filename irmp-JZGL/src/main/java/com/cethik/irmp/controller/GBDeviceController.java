package com.cethik.irmp.controller;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Caseinfo;
import com.cethik.irmp.model.GBDevice;
import com.cethik.irmp.service.GBDeviceService;
import com.cethik.irmp.util.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GBDeviceController extends BaseController {

    @Autowired
    GBDeviceService  GbdeviceService;

    @CetcLog(type = OPERATE.QUERY,info = "controller查询")
    @RequestMapping(value = "/GBDevice/selectPage")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        String sEcho = request.getParameter("sEcho");
        int pageNum=getPageNum(request);
        int iDisplayLength=getiDisplayLength(request);
        List<GBDevice> list = GbdeviceService.selectListByPage(pageNum,iDisplayLength);
        DataTablesResult<Caseinfo> result = new DataTablesResult(list);
        result.setsEcho(sEcho);
        return BaseResponse.buildSuccessResponse(result);
    }

    @RequestMapping(value = "/GBDevice/QueryGBDeviceList")
    public BaseResponse<?> QueryGBDeviceList(HttpServletRequest request) {
        List<GBDevice> list = GbdeviceService.QueryGBDeviceList();
        DataTablesResult<GBDevice> result = new DataTablesResult(list);
        return BaseResponse.buildSuccessResponse(result);
    }
}
