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
    private String      address;
    private Integer     streamServerId;
    private String      streamServerName;
    private Integer     registerWay;
    private String      model;
    private String      longitude;
    private String      latitude;
    private Integer     registerServerId;
    private String      registerServerName;
    private String      regLanIp;
    private Integer     regLanPort;
    private String      regWanIp;
    private Integer     regWanPort;
    private String      strmLanIp;
    private Integer     strmLanPort;
    private String      strmWanIp;
    private Integer     strmWanPort;
    private Integer     type;
    private Integer     locationId;
    private String      locationName;
    public Integer getRegisterServerId() {
        return registerServerId;
    }

    public void setRegisterServerId(Integer registerServerId) {
        this.registerServerId = registerServerId;
    }

    public String getStreamServerName() {
        return streamServerName;
    }

    public void setStreamServerName(String streamServerName) {
        this.streamServerName = streamServerName;
    }

    public String getRegisterServerName() {
        return registerServerName;
    }

    public void setRegisterServerName(String registerServerName) {
        this.registerServerName = registerServerName;
    }

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

    public Integer getRegisterWay() {
        return registerWay;
    }

    public void setRegisterWay(Integer registerWay) {
        this.registerWay = registerWay;
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


    public String getRegLanIp() {
        return regLanIp;
    }

    public void setRegLanIp(String regLanIp) {
        this.regLanIp = regLanIp;
    }

    public Integer getRegLanPort() {
        return regLanPort;
    }

    public void setRegLanPort(Integer regLanPort) {
        this.regLanPort = regLanPort;
    }

    public String getRegWanIp() {
        return regWanIp;
    }

    public void setRegWanIp(String regWanIp) {
        this.regWanIp = regWanIp;
    }

    public Integer getRegWanPort() {
        return regWanPort;
    }

    public void setRegWanPort(Integer regWanPort) {
        this.regWanPort = regWanPort;
    }

    public String getStrmLanIp() {
        return strmLanIp;
    }

    public void setStrmLanIp(String strmLanIp) {
        this.strmLanIp = strmLanIp;
    }

    public Integer getStrmLanPort() {
        return strmLanPort;
    }

    public void setStrmLanPort(Integer strmLanPort) {
        this.strmLanPort = strmLanPort;
    }

    public String getStrmWanIp() {
        return strmWanIp;
    }

    public void setStrmWanIp(String strmWanIp) {
        this.strmWanIp = strmWanIp;
    }

    public Integer getStrmWanPort() {
        return strmWanPort;
    }

    public void setStrmWanPort(Integer strmWanPort) {
        this.strmWanPort = strmWanPort;
    }

    public Integer getStreamServerId() {
        return streamServerId;
    }

    public void setStreamServerId(Integer streamServerId) {
        this.streamServerId = streamServerId;
    }


    public void setType( Integer t){
        type = t;
    }

    public Integer getType() {
        return type;
    }

    public void setLocationId( Integer t){
        locationId = t;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationName( String t){
        locationName = t;
    }

    public String getLocationName() {
        return locationName;
    }
    /*
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
