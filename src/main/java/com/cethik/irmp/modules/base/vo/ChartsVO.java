package com.cethik.irmp.modules.base.vo;

import java.io.Serializable;

/**
 * @Description
 * @Auther daniel.yu
 * @Date 2019/8/4 21:50
 **/
public class ChartsVO implements Serializable {

    private int value;

    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChartsVO(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
