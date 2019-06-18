package com.cethik.irmp.controller;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.model.Caseinfo;
import com.cethik.irmp.service.CaseinfoService;
import com.cethik.irmp.util.DataTablesResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhongping on 2018/09/25.
 */

/**
 * 直接返回内容
 */
@RestController//注意与@Controller的区别
public class CaseController extends BaseController {

    @Autowired
    CaseinfoService caseinfoService;

    //@RequiresPermissions("CaseReadWriteTag/Detail")
    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */

    @CetcLog(type = OPERATE.QUERY,info = "controller查询")
    @RequestMapping(value = "/CaseReadWriteTag/selectPage")
    public BaseResponse<?> selectPage(HttpServletRequest request) {
        String sEcho = request.getParameter("sEcho");
        int pageNum=getPageNum(request);
        int iDisplayLength=getiDisplayLength(request);
        List<Caseinfo> list = caseinfoService.selectListByPage(pageNum,iDisplayLength);
        DataTablesResult<Caseinfo> result = new DataTablesResult(list);
        result.setsEcho(sEcho);
        return BaseResponse.buildSuccessResponse(result);
    }

    @RequestMapping(value = "/CaseReadWriteTag/QueryDictionDataList")
    public BaseResponse<?> QueryDictionDataList(HttpServletRequest request) {
        List<Caseinfo> list = caseinfoService.QueryDictionDataList();
        DataTablesResult<Caseinfo> result = new DataTablesResult(list);
        return BaseResponse.buildSuccessResponse(result);
    }
}
