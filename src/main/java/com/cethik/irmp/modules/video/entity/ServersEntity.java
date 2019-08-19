package com.cethik.irmp.modules.video.entity;

public class ServersEntity {
    private Integer id;
    private  String lanIp;
    private  Integer lanPort;
    private String wanIp;
    private Integer wanPort;
    private String name;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanIp() {
        return lanIp;
    }

    public void setLanIp(String lanIp) {
        this.lanIp = lanIp;
    }

    public Integer getLanPort() {
        return lanPort;
    }

    public void setLanPort(Integer lanPort) {
        this.lanPort = lanPort;
    }

    public String getWanIp() {
        return wanIp;
    }

    public void setWanIp(String wanIp) {
        this.wanIp = wanIp;
    }

    public Integer getWanPort() {
        return wanPort;
    }

    public void setWanPort(Integer wanPort) {
        this.wanPort = wanPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
