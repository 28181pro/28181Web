package com.cethik.irmp.modules.base.vo;

import java.io.Serializable;

/**
 * @Description
 * @Auther daniel.yu
 * @Date 2019/8/3 18:42
 **/
public class MapVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double longitude;
    private Double latitude;

    private String title;
    private String con;
    private String branch;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public MapVO(Double longitude, Double latitude,String title,String con,String branch) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.title = title;
        this.con = con;
        this.branch = branch;

    }

}
