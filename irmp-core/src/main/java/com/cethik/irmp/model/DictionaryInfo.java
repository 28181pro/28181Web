/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.model;

/**
 * DictionaryInfo model
 * sql:DictionaryInfo
 * @author zhongping@hikchina.com
 * @date 2018-09-25
 */
public class DictionaryInfo {
    private Integer id;

    private String code= "";
    private String name= "";

    private String mark = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
