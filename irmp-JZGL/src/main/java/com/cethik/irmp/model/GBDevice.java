package com.cethik.irmp.model;

import java.util.Date;

public class GBDevice {

    private Integer     ID;
    private String      gbcode;
    private String     name;
    private Integer     channelSum;
    private String     ip;
    private Integer     port;
    private String     creator;
    private Date        createtime;

    public  Integer getID(){return ID;}
    public void setID( Integer id){
        this.ID = id;
    }
    public String getGbcode(){
        return gbcode;
    }
    public void setGbcode(String code ){
        this.gbcode = code;
    }

    public String getName(){
        return name;
    }
    public  void  setName( String name){
        this.name = name;
    }

    public Integer getChannelSum(){
        return channelSum;
    }
    public void setChannelSum( Integer sum){
        this.channelSum = sum;
    }

    public String getIp(){
        return ip;
    }
    public void setIP( String ip){
        this.ip = ip;
    }

    public Integer getPort(){
        return port;
    }
    public void setPort( Integer port){
        this.port = port;
    }
    public  String getCreator(){
        return creator;
    }
    public void setCreator( String creator){
        this.creator = creator;
    }

    public Date getCreatetime(){
        return  createtime;
    }
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }
}
