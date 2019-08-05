package com.cethik.irmp.modules.base.vo;

import java.io.Serializable;

/**
 * @Description
 * @Auther daniel.yu
 * @Date 2019/8/4 21:50
 **/
public class StatisticVO implements Serializable {

    //数量
    private Integer num;

    //名称
    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
