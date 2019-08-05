package com.cethik.irmp.modules.base.vo;

import java.io.Serializable;

/**
 * @Description
 * @Auther daniel.yu
 * @Date 2019/8/4 21:50
 **/
public class ChannelStatusVO implements Serializable {

    //数量
    private Integer num;
    //在线、离线值
    private Integer     onlineStatus;
    //在线、离线值
  //  private String statusName;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

//    public String getStatusName() {
//        return statusName;
//    }
//
//    public void setStatusName(String statusName) {
//        this.statusName = statusName;
//    }
}
