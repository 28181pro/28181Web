/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.service;

import com.cethik.irmp.mapper.ProgMapper;
import com.cethik.irmp.model.Prog;
import com.cethik.irmp.vmodel.Nestable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Prog service
 *
 * @author suzhaoyang@cetiti.com
 * @date 2018-08-31
 */
@Service
public class ProgService extends BaseService  {
    private final static Logger log = LoggerFactory.getLogger(ProgService.class);

    @Autowired
    private ProgMapper mapper;

    public void create(Prog model) throws Exception{
        try {
            log.debug("create model: {}", model.getName());

            Integer newid = getTableSequence(model);
            model.setId(newid);
            model.setPath(model.getController() + "/" + model.getAction());
            if (model.getPath() == "/") model.setPath(null);

            model.setNodepath(GetPath(model.getPid()));
//            if(model.getSequence() == null)
            mapper.create(model);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void edit(Prog model) {
        log.debug("edit model: {}", model.getName());
        mapper.edit(model);
    }

    public Prog detail(Integer id) {
        log.debug("detail prog: {}", id);
        return mapper.detail(id);
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public List<Prog> selectAll(){
        return mapper.selectAll();
    }

    public List<Prog> selectByRoleId(Integer roleId) {
        return mapper.selectByRoleId(roleId);
    }

    public List<Prog> selectByUserId(Integer userId) {
        return mapper.selectByUserId(userId);
    }

    @Transactional
    public void sort(List<Nestable> nestables, int parentID)
    {
        for (Nestable p : nestables) {
            Integer index = nestables.indexOf(p);
            Prog prog = mapper.detail(p.getId());
            prog.setSequence(index + 1);
            prog.setPid(parentID);
            prog.setNodepath(GetPath(prog.getPid()));
            mapper.sort(prog);

            if (nestables.get(index).getChildren() != null)
            {
                sort(nestables.get(index).getChildren(), nestables.get(index).getId());
            }
        }
    }

    private String GetPath(int parentID)
    {
        if (parentID == -1) return "/";
        Prog model = mapper.detail(parentID);
        if (model == null)
            return "/";
        return model.getNodepath() + parentID + "/";
    }
}
