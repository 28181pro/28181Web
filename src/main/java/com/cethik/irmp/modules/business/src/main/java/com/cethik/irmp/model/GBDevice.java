package com.cethik.irmp.model;

import java.util.Date;

public class GBDevice {

    private Integer    id;
    private String     gbcode;
    private String     name;
    private Integer    channelsum;
    private String     ip;
    private Integer    port;
    private String     creator;
    private Date       createtime;
    private int        departid;
    private String     departname;
    private int        usedstatus;

    public  Integer getId(){return id;}
    public void setId( Integer id){
        this.id = id;
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

    public Integer getChannelsum(){
        return channelsum;
    }
    public void setChannelsum( Integer sum){
        this.channelsum = sum;
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

    public int getDepartid(){
        return  departid;
    }
    public void setDepart_id( int departid){
        this.departid = departid;
    }

    public String  getDepartname(){
        return departname;
    }
    public void setDepartname( String departname){
        this.departname = departname;
    }

    public int  getUsedstatus(){
        return usedstatus;
    }
    public void setUsedstatus( int usedstatus){
        this.usedstatus = usedstatus;
    }
}
