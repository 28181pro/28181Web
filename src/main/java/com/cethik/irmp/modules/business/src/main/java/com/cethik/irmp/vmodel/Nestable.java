/*
 * Copyright 2012-2018 CETHIK All Rights Reserved.
 */

package com.cethik.irmp.vmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Nestable view model
 * sql:Nestable
 *
 * @author zhongping@hikchina.com
 * @date 2018-09-04
 */
public class Nestable implements Serializable {
    private Integer id;
    private List<Nestable> children = new ArrayList<>();

    public Nestable(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Nestable> getChildren() {
        return children;
    }

    public void setChildren(List<Nestable> children) {
        this.children = children;
    }
}
