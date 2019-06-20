/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.cethik.irmp.web.controller;

import com.cethik.irmp.core.model.Notice;
import com.cethik.irmp.common.dal.entity.SysNoticeEntity;
import com.cethik.irmp.common.dal.dao.master.SysNoticeMapper;
import com.cethik.irmp.core.service.SysNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统通知-- 首页菜单下方滚动显示

 */
//@RestController
@Controller
@RequestMapping("/notice")
public class RestNoticeController {
    private static Logger logger = LoggerFactory.getLogger(RestNoticeController.class);
    @Autowired
    private SysNoticeService noticeService;

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

//    @PostMapping("/list")
//    public PageResult list(NoticeVO vo) {
//        PageInfo<Notice> pageInfo = noticeService.findPageBreakByCondition(vo);
//        return ResultUtil.tablePage(pageInfo);
//    }

    @GetMapping("/list")
    @ResponseBody
    public List list(SysNoticeEntity noticeEntity) {
        List<SysNoticeEntity> list = sysNoticeMapper.findPageBreakByCondition(noticeEntity);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Notice> boList = new ArrayList<>();
        for (SysNoticeEntity sysNotice : list) {
            Notice notice = new Notice();
            notice.setContent(sysNotice.getContent());
            notice.setTitle(sysNotice.getTitle());
            notice.setCreateTime(sysNotice.getCreateTime());
            boList.add(notice);
        }
        return boList;

    }

    @GetMapping("/testlist")
    public String  testlist(Model model) {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        model.addAttribute("items", list);
        model.addAttribute("name","seawater888");

        return "base/log/testlist";

    }

    @GetMapping("/testlist2")
    public String  testlist2(Model model) {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        model.addAttribute("items", list);
        model.addAttribute("name","seawater888");

        return "base/log/testlist2";

    }

}
