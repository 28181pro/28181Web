package com.cethik.irmp.modules.video.entity;

import java.io.Serializable;

public class ChannelEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer     id;
    private String      channelCode;
    private String      gbdeviceCode;
    private String      name;
    private String      ip;
    private Integer     port;
    private Integer     onlineStatus;
    private String      manufacturer;
    /*
    private String      address;
    private Integer     registerway;
    private String      model;
    private String      longitude;
    private String      latitude;
    private ServersEntity   streaminfo;
    private ServersEntity   registerinfo;
*/
    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelcode) {
        this.channelCode = channelcode;
    }

    public String getGbdeviceCode() {
        return gbdeviceCode;
    }

    public void setGbdeviceCode(String gbdevicecode) {
        this.gbdeviceCode = gbdevicecode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlinestatus) {
        this.onlineStatus = onlinestatus;
    }
/*
    public Integer getRegisterway() {
        return registerway;
    }

    public void setRegisterway(Integer registerway) {
        this.registerway = registerway;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public ServersEntity getStreaminfo() {
        return streaminfo;
    }

    public void setStreaminfo(ServersEntity streaminfo) {
        this.streaminfo = streaminfo;
    }

    public ServersEntity getRegisterinfo() {
        return registerinfo;
    }

    public void setRegisterinfo(ServersEntity registerinfo) {
        this.registerinfo = registerinfo;
    }

 */
}
