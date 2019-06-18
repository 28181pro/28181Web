/*
 * Copyright 2012-2018 CETHIK All Rights Reserved.
 */

package com.cethik.irmp.model;

import com.cethik.irmp.util.StringUtils;

/**
 * Prog model
 * sql:Prog
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-04
 */
public class Prog {
    private Integer id;
    private String name;
    private String controller = "";
    private String action = "";
    private String params = "";
    private String path = "";
    private Integer nodetype;
    private Integer pid;
    private String nodepath;
    private Integer sequence = 0;
    private String icon = "";
    private String mark = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNodetype() {
        return nodetype;
    }

    public void setNodetype(Integer nodetype) {
        this.nodetype = nodetype;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getNodepath() {
        return nodepath;
    }

    public void setNodepath(String nodepath) {
        this.nodepath = nodepath;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDefaultIcon() {
        return StringUtils.isNoneEmpty(this.icon) ? this.icon : this.nodetype == 1 ? " fa-wrench" : " fa-globe";
    }
}
