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
package com.cethik.irmp.core.service.impl;

import com.cethik.irmp.core.model.Notice;
import com.cethik.irmp.common.dal.entity.SysNoticeEntity;
import com.cethik.irmp.common.dal.dao.master.SysNoticeMapper;
import com.cethik.irmp.core.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统通知
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public List<Notice> findPageBreakByCondition(Notice vo) {
//        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
      //  PageHelper.startPage(1, vo.getPageSize());
        SysNoticeEntity sysNoticeEntity=new SysNoticeEntity();

        List<SysNoticeEntity> list = sysNoticeMapper.findPageBreakByCondition(sysNoticeEntity);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Notice> boList = new ArrayList<>();
        for (SysNoticeEntity sysNotice : list) {
            Notice notice=new Notice();
            notice.setContent(sysNotice.getContent());
            notice.setTitle(sysNotice.getTitle());
            notice.setCreateTime(sysNotice.getCreateTime());
            boList.add(notice);
        }
//        PageInfo bean = new PageInfo<SysNotice>(list);
//        bean.setList(boList);
        return boList;
    }


}
