package com.cethik.irmp.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Caseinfo {
    private Integer id;

    private String casetypeid;

    private String casetypename;

    private String casename;

    private String caseattribute;

    private String casestate;

    private String casestatename;

    private String casenumber;

    private String casenature;

    private String casenaturename;

    private String casesource;

    private String casesourcename;

    private Integer corpid;

    private String corpname;

    private Integer organiserid;

    private String organisername;

    private String coorganiserid;

    private String coorganisername;

    private Date reviewmaturitydate;

    private Date reviewdate;

    private Date discoverydate;

    private Date caseupperdate;

    private Date caselowerdate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date receptiondate;

    private Date filingdate;

    private Date transferdate;

    private Date receivingdate;

    private Date detectionapprovaldate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date archivingdate;

    private Date reportbirthdaydate;

    private Date revocationdate;

    private Date detectiondate;

    private Date closingdate;

    private String cabinetnum;

    private Integer deviceid;

    private Integer cabinetcell;

    private Integer cellnumber;

    private Integer boxid;

    private String boxnum;

    private Integer boxfloorid;

    private String boxfloornum;

    private String boxepc;

    private Integer boxlocation;

    private Integer ispasteepc;

    private Integer iscabinet;

    private Date cabinetinoutdate;

    private Integer isturnonbox;

    private Integer isbox;

    private Date turnonindate;

    private String epc;

    private Integer isprocuratorate;

    private Integer isborrowed;

    private Date borroweddate;

    private Date returndate;

    private Integer state;

    private Integer nowstate;

    private Integer ajjbxxid;

    private Integer creator;

    private Integer modifier;

    private Date createtime;

    private Date modifytime;

    private String mark;

    private Date reportdate;

    private String corpnum;

    private String organiserpolicecode;

    private String coorganiserpolicecode;

    private Date procuratoratedate;

    private String userpoliceid;

    private String latebb;

    private String coorganisernum;

    private String relegationcorpname;

    private Integer relegationcorpid;

    private String relegationcorpnum;

    private Integer iscasearchives;

    private Date casearchivesdate;

    private String casebrief;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasetypeid() {
        return casetypeid;
    }

    public void setCasetypeid(String casetypeid) {
        this.casetypeid = casetypeid;
    }

    public String getCasetypename() {
        return casetypename;
    }

    public void setCasetypename(String casetypename) {
        this.casetypename = casetypename;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getCaseattribute() {
        return caseattribute;
    }

    public void setCaseattribute(String caseattribute) {
        this.caseattribute = caseattribute;
    }

    public String getCasestate() {
        return casestate;
    }

    public void setCasestate(String casestate) {
        this.casestate = casestate;
    }

    public String getCasestatename() {
        return casestatename;
    }

    public void setCasestatename(String casestatename) {
        this.casestatename = casestatename;
    }

    public String getCasenumber() {
        return casenumber;
    }

    public void setCasenumber(String casenumber) {
        this.casenumber = casenumber;
    }

    public String getCasenature() {
        return casenature;
    }

    public void setCasenature(String casenature) {
        this.casenature = casenature;
    }

    public String getCasenaturename() {
        return casenaturename;
    }

    public void setCasenaturename(String casenaturename) {
        this.casenaturename = casenaturename;
    }

    public String getCasesource() {
        return casesource;
    }

    public void setCasesource(String casesource) {
        this.casesource = casesource;
    }

    public String getCasesourcename() {
        return casesourcename;
    }

    public void setCasesourcename(String casesourcename) {
        this.casesourcename = casesourcename;
    }

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public Integer getOrganiserid() {
        return organiserid;
    }

    public void setOrganiserid(Integer organiserid) {
        this.organiserid = organiserid;
    }

    public String getOrganisername() {
        return organisername;
    }

    public void setOrganisername(String organisername) {
        this.organisername = organisername;
    }

    public String getCoorganiserid() {
        return coorganiserid;
    }

    public void setCoorganiserid(String coorganiserid) {
        this.coorganiserid = coorganiserid;
    }

    public String getCoorganisername() {
        return coorganisername;
    }

    public void setCoorganisername(String coorganisername) {
        this.coorganisername = coorganisername;
    }

    public Date getReviewmaturitydate() {
        return reviewmaturitydate;
    }

    public void setReviewmaturitydate(Date reviewmaturitydate) {
        this.reviewmaturitydate = reviewmaturitydate;
    }

    public Date getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Date getDiscoverydate() {
        return discoverydate;
    }

    public void setDiscoverydate(Date discoverydate) {
        this.discoverydate = discoverydate;
    }

    public Date getCaseupperdate() {
        return caseupperdate;
    }

    public void setCaseupperdate(Date caseupperdate) {
        this.caseupperdate = caseupperdate;
    }

    public Date getCaselowerdate() {
        return caselowerdate;
    }

    public void setCaselowerdate(Date caselowerdate) {
        this.caselowerdate = caselowerdate;
    }

    public Date getReceptiondate() {
        return receptiondate;
    }

    public void setReceptiondate(Date receptiondate) {
        this.receptiondate = receptiondate;
    }

    public Date getFilingdate() {
        return filingdate;
    }

    public void setFilingdate(Date filingdate) {
        this.filingdate = filingdate;
    }

    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    public Date getReceivingdate() {
        return receivingdate;
    }

    public void setReceivingdate(Date receivingdate) {
        this.receivingdate = receivingdate;
    }

    public Date getDetectionapprovaldate() {
        return detectionapprovaldate;
    }

    public void setDetectionapprovaldate(Date detectionapprovaldate) {
        this.detectionapprovaldate = detectionapprovaldate;
    }

    public Date getArchivingdate() {
        return archivingdate;
    }

    public void setArchivingdate(Date archivingdate) {
        this.archivingdate = archivingdate;
    }

    public Date getReportbirthdaydate() {
        return reportbirthdaydate;
    }

    public void setReportbirthdaydate(Date reportbirthdaydate) {
        this.reportbirthdaydate = reportbirthdaydate;
    }

    public Date getRevocationdate() {
        return revocationdate;
    }

    public void setRevocationdate(Date revocationdate) {
        this.revocationdate = revocationdate;
    }

    public Date getDetectiondate() {
        return detectiondate;
    }

    public void setDetectiondate(Date detectiondate) {
        this.detectiondate = detectiondate;
    }

    public Date getClosingdate() {
        return closingdate;
    }

    public void setClosingdate(Date closingdate) {
        this.closingdate = closingdate;
    }

    public String getCabinetnum() {
        return cabinetnum;
    }

    public void setCabinetnum(String cabinetnum) {
        this.cabinetnum = cabinetnum;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getCabinetcell() {
        return cabinetcell;
    }

    public void setCabinetcell(Integer cabinetcell) {
        this.cabinetcell = cabinetcell;
    }

    public Integer getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(Integer cellnumber) {
        this.cellnumber = cellnumber;
    }

    public Integer getBoxid() {
        return boxid;
    }

    public void setBoxid(Integer boxid) {
        this.boxid = boxid;
    }

    public String getBoxnum() {
        return boxnum;
    }

    public void setBoxnum(String boxnum) {
        this.boxnum = boxnum;
    }

    public Integer getBoxfloorid() {
        return boxfloorid;
    }

    public void setBoxfloorid(Integer boxfloorid) {
        this.boxfloorid = boxfloorid;
    }

    public String getBoxfloornum() {
        return boxfloornum;
    }

    public void setBoxfloornum(String boxfloornum) {
        this.boxfloornum = boxfloornum;
    }

    public String getBoxepc() {
        return boxepc;
    }

    public void setBoxepc(String boxepc) {
        this.boxepc = boxepc;
    }

    public Integer getBoxlocation() {
        return boxlocation;
    }

    public void setBoxlocation(Integer boxlocation) {
        this.boxlocation = boxlocation;
    }

    public Integer getIspasteepc() {
        return ispasteepc;
    }

    public void setIspasteepc(Integer ispasteepc) {
        this.ispasteepc = ispasteepc;
    }

    public Integer getIscabinet() {
        return iscabinet;
    }

    public void setIscabinet(Integer iscabinet) {
        this.iscabinet = iscabinet;
    }

    public Date getCabinetinoutdate() {
        return cabinetinoutdate;
    }

    public void setCabinetinoutdate(Date cabinetinoutdate) {
        this.cabinetinoutdate = cabinetinoutdate;
    }

    public Integer getIsturnonbox() {
        return isturnonbox;
    }

    public void setIsturnonbox(Integer isturnonbox) {
        this.isturnonbox = isturnonbox;
    }

    public Integer getIsbox() {
        return isbox;
    }

    public void setIsbox(Integer isbox) {
        this.isbox = isbox;
    }

    public Date getTurnonindate() {
        return turnonindate;
    }

    public void setTurnonindate(Date turnonindate) {
        this.turnonindate = turnonindate;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public Integer getIsprocuratorate() {
        return isprocuratorate;
    }

    public void setIsprocuratorate(Integer isprocuratorate) {
        this.isprocuratorate = isprocuratorate;
    }

    public Integer getIsborrowed() {
        return isborrowed;
    }

    public void setIsborrowed(Integer isborrowed) {
        this.isborrowed = isborrowed;
    }

    public Date getBorroweddate() {
        return borroweddate;
    }

    public void setBorroweddate(Date borroweddate) {
        this.borroweddate = borroweddate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNowstate() {
        return nowstate;
    }

    public void setNowstate(Integer nowstate) {
        this.nowstate = nowstate;
    }

    public Integer getAjjbxxid() {
        return ajjbxxid;
    }

    public void setAjjbxxid(Integer ajjbxxid) {
        this.ajjbxxid = ajjbxxid;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public String getCorpnum() {
        return corpnum;
    }

    public void setCorpnum(String corpnum) {
        this.corpnum = corpnum;
    }

    public String getOrganiserpolicecode() {
        return organiserpolicecode;
    }

    public void setOrganiserpolicecode(String organiserpolicecode) {
        this.organiserpolicecode = organiserpolicecode;
    }

    public String getCoorganiserpolicecode() {
        return coorganiserpolicecode;
    }

    public void setCoorganiserpolicecode(String coorganiserpolicecode) {
        this.coorganiserpolicecode = coorganiserpolicecode;
    }

    public Date getProcuratoratedate() {
        return procuratoratedate;
    }

    public void setProcuratoratedate(Date procuratoratedate) {
        this.procuratoratedate = procuratoratedate;
    }

    public String getUserpoliceid() {
        return userpoliceid;
    }

    public void setUserpoliceid(String userpoliceid) {
        this.userpoliceid = userpoliceid;
    }

    public String getLatebb() {
        return latebb;
    }

    public void setLatebb(String latebb) {
        this.latebb = latebb;
    }

    public String getCoorganisernum() {
        return coorganisernum;
    }

    public void setCoorganisernum(String coorganisernum) {
        this.coorganisernum = coorganisernum;
    }

    public String getRelegationcorpname() {
        return relegationcorpname;
    }

    public void setRelegationcorpname(String relegationcorpname) {
        this.relegationcorpname = relegationcorpname;
    }

    public Integer getRelegationcorpid() {
        return relegationcorpid;
    }

    public void setRelegationcorpid(Integer relegationcorpid) {
        this.relegationcorpid = relegationcorpid;
    }

    public String getRelegationcorpnum() {
        return relegationcorpnum;
    }

    public void setRelegationcorpnum(String relegationcorpnum) {
        this.relegationcorpnum = relegationcorpnum;
    }

    public Integer getIscasearchives() {
        return iscasearchives;
    }

    public void setIscasearchives(Integer iscasearchives) {
        this.iscasearchives = iscasearchives;
    }

    public Date getCasearchivesdate() {
        return casearchivesdate;
    }

    public void setCasearchivesdate(Date casearchivesdate) {
        this.casearchivesdate = casearchivesdate;
    }

    public String getCasebrief() {
        return casebrief;
    }

    public void setCasebrief(String casebrief) {
        this.casebrief = casebrief;
    }
}