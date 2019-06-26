package com.cethik.irmp.service;

import com.cethik.irmp.annotition.CetcLog;
import com.cethik.irmp.aspect.OPERATE;
import com.cethik.irmp.mapper.CaseinfoMapper;
import com.cethik.irmp.mapper.GBDeviceMapper;
import com.cethik.irmp.model.Caseinfo;
import com.cethik.irmp.model.GBDevice;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GBDeviceService extends BaseService  {
    private final static Logger log = LoggerFactory.getLogger(CaseinfoService.class);

    @Autowired
    private GBDeviceMapper mapper;

    @CetcLog(type= OPERATE.QUERY,info="查询部份的列表2222")
    public List<GBDevice> selectListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<GBDevice> list = mapper.selectAll();
        return list;
    }

    @CetcLog(type= OPERATE.QUERY,info="查询所有的列表")
    public List QueryGBDeviceList(){
        List<GBDevice> list = mapper.selectAll();
        return list;
    }
}
