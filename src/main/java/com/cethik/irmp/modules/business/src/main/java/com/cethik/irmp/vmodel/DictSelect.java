/*
 * Copyright 2012-2018 CETHIK All Rights Reserved.
 */

package com.cethik.irmp.vmodel;

import java.io.Serializable;

/**
 * DictSelect view model
 * sql:DictSelect
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-04
 */
public class DictSelect implements Serializable {
    private String id;
    private String name;
    private String text;
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
