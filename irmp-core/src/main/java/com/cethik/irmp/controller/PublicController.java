package com.cethik.irmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.cethik.irmp.config.AuthRealm;
import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.helper.SelectDataHelper;
import com.cethik.irmp.model.Corp;
import com.cethik.irmp.model.DictionaryInfo;
import com.cethik.irmp.service.CorpService;
import com.cethik.irmp.service.DictionaryInfoService;
import com.cethik.irmp.vmodel.DictSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongping on 2018/09/25.
 */

/**
 * 直接返回内容
 */
@RestController//注意与@Controller的区别
public class PublicController extends BaseController {

    @Autowired
    DictionaryInfoService dictionaryInfoService;


    @Autowired
    CorpService corpService;

    @Autowired
    AuthRealm authRealm;

    /**
     * 分页查询
     * Created by zhongping on 2018/09/12.
     */
    @RequestMapping(value = "/public/querySelectDataList")
    public BaseResponse<?> selectDataList(HttpServletRequest request) {
        List<DictionaryInfo> caseTypeList = new ArrayList<>();
        List<DictionaryInfo> caseStateList = new ArrayList<>();

        List<Corp> corpList = corpService.selectAll();//p => p.State == 1, " Code ASC").ToList(); ;

        List<DictSelect> corpDataList = SelectDataHelper.GetCorpSelect2List(corpList);

//        UserManager userManager = new UserManager();
//        var userList = userManager.QueryList(p => p.State == 1 && p.UserType == 0).ToList();
//        var userDataList = base.GetUserSelect2List(userList, true);
//        DictionaryDataManager dictionaryDataManager = new DictionaryDataManager();

        //41：案件类别；49：案件受理方式；63：案件状态；40：案件来源；159：督办级别；848：证件种类；70：保密级别；22：案件发案地域；550：社区责任区；29：案件副案别；
        Integer[] dicinfolist = { 41, 49, 63, 40, 159, 848, 70, 22, 1048, 29 };
//        var dictionDataList = dictionaryDataManager.QueryList(p => dicinfolist.Contains(p.DictionaryID));
//
//        caseTypeList = base.GetDictionaryDataSelect2List(dictionDataList.Where(p => p.DictionaryID == 41).OrderBy(o => o.Value).ToList(), true).ToList();
//        caseStateList = base.GetDictionaryDataSelectList(dictionDataList.Where(p => p.DictionaryID == 63).ToList(), true).ToList();


        JSONObject json = new JSONObject();
        json.put("aData", caseStateList);
        json.put("casetypeData", caseTypeList);
        json.put("corpList", corpDataList);

        return BaseResponse.buildSuccessResponse(json);
    }

}
