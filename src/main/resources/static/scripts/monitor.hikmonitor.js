// <reference path="jquery.js" />
// <reference path="Ajax.js" />
// <reference path="extension.js" />
/****************** 定义全局共用参数 **************************/
var objDeviceInfo = {};
var regSvrIp = "";              //注册服务器
var regSvrPort = "";
var streamSvrIp = "";           //流媒体服务器
var streamSvrPort = "";
var storeSvrIp = "";            //存储服务器
var storeSvrPort = "";
var accSvrIp = "";              //电梯接入服务器
var accSvrPort = "";
var stateSvrIp = "";            //状态服务器
var stateSvrPort = "";
var nvrSvrIp = "";              //NVR服务器
var nvrSvrPort = "";
var deviceIp = "";              //设备IP
var username = "admin";
var password = "12345";
var deviceName = "";
var iStreamCodeType = 1;        //0: 主码流 1: 子码流
//备注：这里写死
var iStreamUseType = 1;         //流媒体使用方式(0: 点对点 1: 服务器转发)
var iStreamTransferMode = 1;    //流媒体取流类型(0: UDP 1:TCP)
var iServerRoute = 1;             //注册时配置的线路

var iConnHandle = -1; 	//连接设备返回句柄
var iPlayHandle = -1; 	//播放视频返回句柄

var netAccess = "3G";

var streamType = 1;   //视频回放三个参数，写死
var streamClass = 0;
var devLine = 0;

/*
* ************* HikTech 视频播放接口 ********************
*/


// 开始和结束录像
var _recordFlag = 0;
function RecordHandle(obj) {
    var ocx = document.getElementById("ppvs");
    if (_recordFlag == 0) {
        StartRecord(ocx, obj);
    } else {
        StopRecord(ocx, obj);
    }
}

function StartRecord(ocx, obj) {
    var result = ocx.StartRecord();
    if (result >= 0) {
        //alert("开始录像，默认存储在C://HikElevator/Video目录下.");
        _recordFlag = 1;
        obj.className = "recordon";
    } else {
        //alert("录像失败，请重试.");
        obj.className = "record";
    }
}

function StopRecord(ocx, obj) {
    ocx.StopRecord();
    //alert("停止录像.");
    obj.className = "record";
    _recordFlag = 0;
}

// 开始和结束对讲
var _talkHandle = 0;
function TalkHandle(obj) {
    var ocx = document.getElementById("ppvs");
    if (_talkHandle == 0) {
        var elevCode = $("#devid").val();
        StartTalk(obj, ocx, elevCode);
    } else {
        StopTalk(obj, ocx);
    }
}

function StartTalk(obj, ocx, elevCode) {
    DeviceInit(elevCode);
    //var loginType = 0;

    var result = ocx.StartTalk(streamSvrIp, streamSvrPort, regSvrIp, regSvrPort, elevCode, iServerRoute, iStreamCodeType, 1);

    if (result < 0) {
        alert("打开对讲失败，请重试.");
        return;
    }
    obj.className = "talkon";
    _talkHandle = 1;
}

function StopTalk(obj, ocx) {
    ocx.StopTalk();
    _talkHandle = 0;
    obj.className = "talk";
}

// 截图
function Snapshot(obj) {
    var ocx = document.getElementById("ppvs");
    var elevCode = $("#devid").val();
    var pic = ocx.SnapShot();
    if (pic >= 0) {
        alert("截图成功.");
    }
}


var connectHandle = "";
function VedioPlayLog(iDeviceCode, isStart) {
    var obj = {};
    var userid = getCookie("userid");
    userid = userid == "" ? 10 : userid;
    obj.UserID = userid
    obj.DeviceCode = iDeviceCode;
    obj.IP = getCookie("clientip");

    obj.IsStart = isStart;

    if (!isStart) {
        obj.ConnectHandle = connectHandle;
    }
    else {
        connectHandle = new Date().format("yyyyMMddhhmmssS");
        obj.ConnectHandle = connectHandle;
    }

    SetVideoLog(obj);
}

// 视频回放NVR
var vedioOcx = null;
function VedioPlaybackStart(elevCode, startTime, endTime) {
    if (vedioOcx == null) {
        vedioOcx = document.getElementById("ppvs");
    }
    DeviceInit(elevCode);

    vedioOcx.ConnectDevice(nvrSvrIp, nvrSvrPort, "admin", "kqgd12345");

    vedioOcx.PlayByTime(deviceIp, startTime, endTime);
}

function VedioPlaybackStop() {
    if (vedioOcx != null) {
        vedioOcx.stopbytime();
    }
}

function VedioPlaybackFast() {
    if (vedioOcx != null) {
        vedioOcx.fast();
    }
}

function VedioPlaybackSlow() {
    if (vedioOcx != null) {
        vedioOcx.slow();
    }
}

// 视频回放
function VedioPlaybackStartOld(ocx, elevCode, startTime) {
    DeviceInit(elevCode);

    ocx.playreview(regSvrIp, regSvrPort, streamSvrIp, streamSvrPort, elevCode, streamType, streamClass, devLine, startTime, 1);
}

function VedioPlaybackStopOld(ocx) {
    ocx.stopreview();
}