package com.cethik.irmp.vmodel;


import com.cethik.irmp.model.Prog;

import java.util.List;

/**
 * User view model
 * @author zhongping@hikchina.com
 * @date 2018-09-18
 */

public class CurrentUser {
    private Integer id;
    private String account= "";
    private String name = "";
    private String password= "";
    private Integer usertype;

    private Integer roleid;

    private Integer corpid;
    private Integer corptype;
    private String corpcode= "";
    private String corpname= "";
    private Integer epctype;

    private Integer privilegetype;
    private String privilegevalue= "";

    // 操作权限： 1 全部数据  2区域数据  3单位数据   4本人创建的数据
    private Integer operateprivilegetype;
    private String operateprivilegevalue= "";

    private String policecode= "";
    private Integer userpoliceid;

    //是否单位法制员
    private Integer islegalstaff;
    //是否审核法制员
    private Integer isreviewlegalstaff;

    private List<Prog> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public Integer getCorptype() {
        return corptype;
    }

    public void setCorptype(Integer corptype) {
        this.corptype = corptype;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public Integer getEpctype() {
        return epctype;
    }

    public void setEpctype(Integer epctype) {
        this.epctype = epctype;
    }

    public Integer getPrivilegetype() {
        return privilegetype;
    }

    public void setPrivilegetype(Integer privilegetype) {
        this.privilegetype = privilegetype;
    }

    public String getPrivilegevalue() {
        return privilegevalue;
    }

    public void setPrivilegevalue(String privilegevalue) {
        this.privilegevalue = privilegevalue;
    }

    public Integer getOperateprivilegetype() {
        return operateprivilegetype;
    }

    public void setOperateprivilegetype(Integer operateprivilegetype) {
        this.operateprivilegetype = operateprivilegetype;
    }

    public String getOperateprivilegevalue() {
        return operateprivilegevalue;
    }

    public void setOperateprivilegevalue(String operateprivilegevalue) {
        this.operateprivilegevalue = operateprivilegevalue;
    }

    public String getPolicecode() {
        return policecode;
    }

    public void setPolicecode(String policecode) {
        this.policecode = policecode;
    }

    public Integer getUserpoliceid() {
        return userpoliceid;
    }

    public void setUserpoliceid(Integer userpoliceid) {
        this.userpoliceid = userpoliceid;
    }

    public Integer getIslegalstaff() {
        return islegalstaff;
    }

    public void setIslegalstaff(Integer islegalstaff) {
        this.islegalstaff = islegalstaff;
    }

    public Integer getIsreviewlegalstaff() {
        return isreviewlegalstaff;
    }

    public void setIsreviewlegalstaff(Integer isreviewlegalstaff) {
        this.isreviewlegalstaff = isreviewlegalstaff;
    }

    public List<Prog> getMenus() {
        return menus;
    }

    public void setMenus(List<Prog> menus) {
        this.menus = menus;
    }
}
