package com.cethik.irmp.modules.video.entity;

import java.util.Date;

public class GbDeviceEntity {

    private Integer id;
    private String gbCode;
    private String name;
    private Integer channelSum;
    private String ip;
    private Integer port;
    private String creator;
    private Date createTime;
    private int departId;
    private String departName;
    private int usedStatus;
    private String modifior;
    private String mark;
    private Date modifyTime;


    public String getModifior() {
        return modifior;
    }

    public void setModifior(String modifior) {
        this.modifior = modifior;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String code) {
        this.gbCode = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChannelSum() {
        return channelSum;
    }

    public void setChannelSum(Integer sum) {
        this.channelSum = sum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDepartid() {
        return departId;
    }

    public void setDepart_id(int departid) {
        this.departId = departid;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public int getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(int usedStatus) {
        this.usedStatus = usedStatus;
    }
}
