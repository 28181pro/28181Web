/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.model;

/**
 * Role model
 * sql:role
 * @author zhongping@hikchina.com
 * @date 2018-09-04
 */
public class Role {
    private Integer id;
    private Integer pid;
    private Integer roleparentid;
    private String name = "";
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRoleparentid() {
        return roleparentid;
    }

    public void setRoleparentid(Integer roleparentid) {
        this.roleparentid = roleparentid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
