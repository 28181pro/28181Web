package com.cethik.irmp.util;

import java.util.List;

/**
 * DataTables工具
 *
 * @author zhongping@hikchina.com
 * @author modify by zhengyuhui @2019-04-23
 * @date 2018-09-06
 */
public class DataTablesResult<T> {

    private List<T> aaData; //aaData 与datatales 加载的“dataSrc"对应
    private long iTotalDisplayRecords;
    private long iTotalRecords;
    private String sEcho;
    public DataTablesResult(List<T> aaData){
        setsEcho(sEcho);
        setAaData(aaData);
        setiTotalDisplayRecords(aaData.size());
    }
    public DataTablesResult(){}
    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
}