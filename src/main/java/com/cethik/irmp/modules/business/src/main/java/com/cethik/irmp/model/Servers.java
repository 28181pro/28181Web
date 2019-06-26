package com.cethik.irmp.model;

public class Servers {
    private Integer id;
    private  String lanip;
    private  Integer lanport;
    private String wanip;
    private Integer wanport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanip() {
        return lanip;
    }

    public void setLanip(String lanip) {
        this.lanip = lanip;
    }

    public Integer getLanport() {
        return lanport;
    }

    public void setLanport(Integer lanport) {
        this.lanport = lanport;
    }

    public String getWanip() {
        return wanip;
    }

    public void setWanip(String wanip) {
        this.wanip = wanip;
    }

    public Integer getWanport() {
        return wanport;
    }

    public void setWanport(Integer wanport) {
        this.wanport = wanport;
    }
}
