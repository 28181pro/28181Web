package com.cethik.irmp.model;

public class Channel {
    private Integer     id;
    private String      channelcode;
    private String      gbdevicecode;
    private String      name;
    private String      ip;
    private Integer     port;
    private Integer     onlinestatus;
    private String      manufacturer;
    private String      address;
    private Integer     registerway;
    private String      model;
    private String      longitude;
    private String      latitude;
    private Servers   streaminfo;
    private Servers   registerinfo;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getGbdevicecode() {
        return gbdevicecode;
    }

    public void setGbdevicecode(String gbdevicecode) {
        this.gbdevicecode = gbdevicecode;
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

    public Integer getOnlinestatus() {
        return onlinestatus;
    }

    public void setOnlinestatus(Integer onlinestatus) {
        this.onlinestatus = onlinestatus;
    }

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

    public Servers getStreaminfo() {
        return streaminfo;
    }

    public void setStreaminfo(Servers streaminfo) {
        this.streaminfo = streaminfo;
    }

    public Servers getRegisterinfo() {
        return registerinfo;
    }

    public void setRegisterinfo(Servers registerinfo) {
        this.registerinfo = registerinfo;
    }
}
