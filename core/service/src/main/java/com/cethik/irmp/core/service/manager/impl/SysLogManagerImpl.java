package com.cethik.irmp.core.service.manager.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.cethik.irmp.common.dal.dao.master.SysLogMapper;
import com.cethik.irmp.common.dal.entity.SysLogDO;
import com.cethik.irmp.common.util.entity.PageResult;
import com.cethik.irmp.common.util.entity.Query;
import com.cethik.irmp.core.service.manager.SysLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统日志
 *
 * @author daniel.yu
 * @date 2017年8月14日 下午8:43:15
 */
@Component("sysLogManager")
public class SysLogManagerImpl implements SysLogManager {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void saveLog(SysLogDO log) {
        sysLogMapper.save(log);
    }



    @Override
    public PageResult listForPage(Query query) {
        int pageNum = query.getAsInt("pageNumber");
        int pageSize = query.getAsInt("pageSize");
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<SysLogDO> sysLogDOList = sysLogMapper.listForPage(query);
        return new PageResult(page.getTotal(), sysLogDOList);
    }


//    @Override
//    public List<SysLogDO> listLog(Query query) {
//        try {
//            Page<SysLogDO> list=PageHelper.startPage(pageInfo.getPageNo(),pageInfo.getPageSize()).doSelectPage(()->sysLogMapper.listForPage());
//            List<SysLogDO> sysLogEntityList=sysLogMapper.listForPage(query);
//            PageInfo page=new PageInfo(sysLogEntityList);
//            int n=page.getPageSize();
//            int n2=page.getPages();
//            int n3=page.getSize();
//            long n4=page.getTotal();
//            long m=n4;
//            return list;
//        } catch (Exception ex) {
//            String s1 = ex.getMessage();
//            System.out.print(ex.getMessage());
//            return null;
//        }
//    }


    @Override
    public int batchRemove(Long[] id) {
        return sysLogMapper.batchRemove(id);
    }

    @Override
    public int batchRemoveAll() {
        return sysLogMapper.batchRemoveAll();
    }

}
