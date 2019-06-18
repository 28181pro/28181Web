package com.cethik.irmp.service;

import com.cethik.irmp.mapper.AreaMapper;
import com.cethik.irmp.model.Area;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Area service
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */

@Service
public class AreaService extends BaseService {
    private final static Logger log = LoggerFactory.getLogger(AreaService.class);

    @Autowired
    private AreaMapper mapper;

    public void create(Area model) throws Exception {
        try {
            log.debug("create model: {}", model.getName());
            Integer newid = getTableSequence(model);
            model.setId(newid);
            mapper.create(model);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Area model) {
        log.debug("edit model: {}", model.getName());
        mapper.edit(model);
    }

    public Area detail(Integer id) {
        log.debug("detail model: {}", id);
        return mapper.detail(id);
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(String ids) {
        String[] arrID = ids.split(",");
        for (String id : arrID) {
            mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    public List<Area> selectAll() {
        return mapper.selectAll();
    }

    public List<Area> selectListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<Area> areas = mapper.selectAll();
        return areas;
    }
}
