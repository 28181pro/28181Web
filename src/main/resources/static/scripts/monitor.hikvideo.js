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
var username = "admin";
var password = "12345";
var deviceName = "";
var iStreamCodeType = 1;        //0: 主码流 1: 子码流
//备注：这里写死
var iStreamTransferMode = 1;    //流媒体取流类型(0: UDP 1:TCP)
var iServerRoute = 1;             //注册时配置的线路

var iPlayHandle = -1; 	//播放视频返回句柄

var netAccess = "3G";

///初始化电梯信息
function DeviceInit(elevCode) {
    try {
        var objRegInfo = null;
        if (objDeviceInfo[elevCode] != null) {
            objRegInfo = objDeviceInfo[elevCode];
        }
        else {
            var regInfo = getRegisterInfo(elevCode, 'ALL');
            if (regInfo == null)
                return;
            objRegInfo = regInfo;
            objDeviceInfo[elevCode] = objRegInfo;
        }

        accSvrIp = objRegInfo.AccessSvrIp;
        accSvrPort = objRegInfo.AccessSvrPort;
        stateSvrIp = objRegInfo.StateSvrIp;
        stateSvrPort = objRegInfo.StateSvrPort;

        regSvrIp = objRegInfo.PPVSSvrIp;
        regSvrPort = objRegInfo.PPVSSvrPort;
        streamSvrIp = objRegInfo.StreamSvrIp;
        streamSvrPort = objRegInfo.StreamSvrPort;
        storeSvrIp = objRegInfo.StoreSvrIp;
        storeSvrPort = objRegInfo.StoreSvrPort;

        deviceName = objRegInfo.Location;
        iStreamCodeType = objRegInfo.StreamCodeType;
        iStreamTransferMode = objRegInfo.StreamTransferType;
        iServerRoute = objRegInfo.ServerRoute;
        netAccess = objRegInfo.NetAccess;
        return true;
    }
    catch (e) {
        alert('初始化信息失败');
        return false;
    }
}

///连接设备
function ConnDevice(ocx, elevCode) {
    var rt = ocx.ConnectDevice(elevCode, regSvrIp, regSvrPort, streamSvrIp, streamSvrPort, username, password);
    if (rt == 0) {
        alert("设备连接失败，请重试.");
        return 0;
    }
    return rt;
}

//<播放视频功能>
/*************************************************
Function:		RealPlay
Description:	播放视频
Input:			无			
Output:			无
return:			无				
*************************************************/
function RealPlay() {
    var deviceid = $("#devid").val();
    var ocx = $("#ppvs")[0];
    var streammode = $("#streammode").val();
    Play(deviceid, ocx);
}

/*************************************************
Function:		StopRealPlay
Description:	停止视频播放
Input:			无			
Output:			无
return:			无				
*************************************************/
function StopRealPlay() {
    var deviceid = $("#devid").val();
    var ocx = $("#ppvs")[0];
    StopPlay(ocx, deviceid);
}


/*************************************************
Function:		ResizeVedio
Description:	调整窗口大小
Input:			无			
Output:			无
return:			无				
*************************************************/
var winFlag = 0;
function ResizeVedio() {
    if (winFlag == 0) {
        $("#videoarea").width("352").height("288");
        $("#videoarea").css("margin", "0 auto").css("padding-top", "100px");
        winFlag = 1;
    }
    else if (winFlag == 1) {
        $("#videoarea").width("100%").height("100%");
        $("#videoarea").css("margin", "0 auto").css("padding-top", "0");
        winFlag = 0;
    }
}


/*************************************************
Function:		ToRecConfig
Description:	录像页面 this function was added by sam to accomplished the function of page linking to the record page
Input:			无			
Output:			无
return:			无				
*************************************************/
var _recordFlag = 0;
function ToRecConfig(obj) {
    var ocx = document.getElementById("ppvs");
    if (iPlayHandle < 0) {
        alert("请先播放视频！");
        return;
    }
    if (_recordFlag == 0) {
        StartRecordByEhome(ocx, obj);
    } else {
        StopRecordByEhome(ocx, obj);
    }
}

function StartRecordByEhome(ocx, obj) {
    var result = ocx.StartRecordByEhome();
    if (result >= 0) {
        alert("开始录像，默认存储在C://HikElevator/Video目录下.");
        _recordFlag = 1;
        obj.className = "recordon";
    } else {
        alert("录像失败，请重试.");
        obj.className = "record";
    }
}

function StopRecordByEhome(ocx, obj) {
    ocx.StopRecordByEhome();
    alert("停止录像.");
    obj.className = "record";
    _recordFlag = 0;
}


///<summary>设备注册信息初始化</summary>
///<param name="IDeviceID">设备编码</param>
///<param name="iPPVSOCX">视频播放</param>
///<returns></returns>
function Play(elevCode, PlayOCX) {

    var success = true;

    //初始化设备注册信息
    DeviceInit(elevCode);

    //连接设备
    var line = ConnDevice(PlayOCX, elevCode);

    //如果正在播放，则停止视频
    if (line > 0) {
        if (iPlayHandle > 0) {
            line = StopPlay(PlayOCX, elevCode);
        }

        StartPlayHandle(elevCode, PlayOCX, line);
    }
}

///<summary>开始播放准备</summary>
///<param name="elevCode">设备代码</param>
///<param name="PlayOCX">控件对象</param>
///<param name="line">播放线路</param>
///<returns></returns>
function StartPlayHandle(elevCode, PlayOCX, line) {
    iPlayHandle = PlayOCX.StartVideoByEhomeTCP(line);
    if (iPlayHandle > 0) {
        VedioPlayLog(elevCode, true);
    }
    else {
    }
}


///<summary>停止播放</summary>
///<param name="PlayOCX">控件对象</param>
///<param name="elevCode">设备代码</param>
///<returns></returns>
function StopPlay(PlayOCX, elevCode) {
    setTimeout(AudioFuncStop, 0);
    if (PlayOCX == null) return;
    if (iPlayHandle > -1) {
        if (PlayOCX.StopVideoByEhome() > 0) {
            VedioPlayLog(elevCode, false);
            iPlayHandle = -1;
        }
        else {
        }
    }
}


/**********************************
功能: 分屏预览By Channal
参数: 
iDeviceIndex：  		设备序号
elevCode:      		设备ID
iClientStreamMode:      取流方式
***********************************/
function StartPlayByChannel(elevCode, iDeviceIndex) {
    var iChannelIndex = iDeviceIndex - 1;
    VedioPlayLog(elevCode, false);
    //选中的OCX对象
    var PlayOCX = document.getElementById("PPVS" + iDeviceIndex);

    //初始化设备注册信息
    DeviceInit(elevCode);

    //连接设备
    var line = ConnDevice(PlayOCX, elevCode);

    //先停止当前通道正在播放的视频
    var _m_szWindowInChannel = m_szWindowInChannel[iChannelIndex];
    if (typeof (_m_szWindowInChannel) != "undefined" && _m_szWindowInChannel != "") {
        var device_index = _m_szWindowInChannel.split(',')[0];
        StopPlayByChannel(device_index);
    }

    //如果设备原来已经在播放，则停止原先的
    for (var i = 0; i < 16; i++) {
        if (i != iChannelIndex && m_bChannelPlay[i][0] == elevCode) {
            m_bChannelPlay[i][0] = "";
            m_bChannelPlay[i][1] = "";
            StopPlayByChannel(i + 1);
        }
    }

    //播放视频
    StartPlayHandle(elevCode, PlayOCX, line);

    m_szWindowInChannel[iChannelIndex] = iDeviceIndex + "," + elevCode; //该通道正在播放
    m_bChannelPlay[iChannelIndex][0] = elevCode; //播放设备ID
    m_bChannelPlay[iChannelIndex][1] = deviceName; //播放使用地点

    //选中下个窗口
    var nextWin = 1;
    if (iDeviceIndex < m_iCurWindowMode) {
        nextWin = iDeviceIndex + 1;
    } else {
        nextWin = 1;
    }
    SelectWindow(nextWin);
}

function StopPlayByChannel(iDeviceIndex) {
    //选中的OCX对象
    var elevCode = m_bChannelPlay[iDeviceIndex - 1][0];
    var PlayOCX = document.getElementById("PPVS" + iDeviceIndex);
    if (PlayOCX == null) return;
    if (PlayOCX.StopVideoByEhome() > 0) {
        VedioPlayLog(m_bChannelPlay[iDeviceIndex - 1][0], false);
        m_szWindowInChannel[iDeviceIndex - 1] = "";
        m_bChannelPlay[iDeviceIndex - 1][0] = "";
        m_bChannelPlay[iDeviceIndex - 1][1] = "";
    }
    else {
    }
}

function StopPlayAllWindows() {
    setTimeout(AudioFuncStop, 0);
    for (var j = 0; j < 16; j++) {
        var index = m_szWindowInChannel[j];
        if (typeof (index) != "undefined" && index != "") {
            StopPlayByChannel(index.split(",")[0]);
        }
    }
}


/**********************************
功能: 语音对讲功能
参数: 
control：  		        ocx id
iDeviceIndex:      		通道号
VedioOcx:               ocx对象
deviceName:             使用地点
***********************************/
var m_szAudioFlag = "";
var AudioOcx = null;
function AudioFunc() {
    var elevCode = document.getElementById("devid").value;
    AudioOcx = document.getElementById("ppvs1");
    if (m_szAudioFlag == "") {
        AudioFuncStart(elevCode, AudioOcx);
    } else {
        AudioFuncStop(AudioOcx);
    }
}
/*
function AudioFuncStart(elevCode, ocx) {
    DeviceInit(elevCode);
    AudioOcx = ocx;
    if (m_szAudioFlag != "") {
        //如果是同一个视频，已经打开了就直接返回
        if (m_szAudioFlag == elevCode) {
            return;
        }
        //关闭语音对讲
        AudioFuncStop(ocx);
    }

    //连接设备
    ConnDevice(ocx, elevCode);

    try {
        var iRet = ocx.StartTalkByEhome();
        if (iRet >= 0) {
            m_szAudioFlag = elevCode;
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = m_focusWin;
            }
            AudioStatus(1);
        }
        else {
            AudioStatus(0);
            m_szAudioFlag = "";
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = -1;
            }
            alert("开启语音对讲失败!");
        }
    } catch (e) {
        //by pass
    }
}

function AudioFuncStop(ocx) {
    if (ocx == null) {
        if (AudioOcx == null)
            return;
        ocx = AudioOcx;
    }

    try {
        if (m_szAudioFlag != "") {
            AudioStatus(0);
            m_szAudioFlag = "";
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = -1;
            }
            ocx.StopTalkByEhome();
        }
    } catch (e) {
        //by pass
    }
}
*/
function AudioFuncStart(elevCode, AudioOcx) {
    DeviceInit(elevCode);

    if (m_szAudioFlag != "") {
        //如果是同一个视频，已经打开了就直接返回
        if (m_szAudioFlag == elevCode) {
            return;
        }
        //关闭语音对讲
        AudioFuncStop(AudioOcx);
    }

    //连接设备
    //ConnDevice(elevCode, AudioOcx);
	AudioOcx.ConnectDeviceByACS(elevCode, regSvrIp, regSvrPort, username, password);
	
    try {
        var iRet = AudioOcx.VoiceTalkStart(0, 0);
        if (iRet >= 0) {
            m_szAudioFlag = elevCode;
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = m_focusWin;
            }
            AudioStatus(1);
        }
        else {
            AudioStatus(0);
            m_szAudioFlag = "";
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = -1;
            }
            alert("开启语音对讲失败!");
        }
    } catch (e) {alert(e);
        //by pass
    }
}

//停止语音对讲功能
function AudioFuncStop(AudioOcx) {
    if (AudioOcx == null) {
        AudioOcx = document.getElementById("AudioOcx");
        if (AudioOcx == null) return;
    }
    try {
        if (m_szAudioFlag != "") {
            AudioStatus(0);
            m_szAudioFlag = "";
            if (typeof (m_iTalking) != "undefined") {
                m_iTalking = -1;
            }
            AudioOcx.VoiceTalkStop();
        }
    } catch (e) {
        //by pass
    }
}

function AudioStatus(audio_flag) {
    if (audio_flag == 1) {
        $("#btnTalk").removeClass("talk").addClass("talkon");
    } else {
        $("#btnTalk").removeClass("talkon").addClass("talk");
    }
}

/**********************************
功能: 截图功能，单个视频和分屏的右键截图
参数: 
control：  		        ocx id
iDeviceIndex:      		通道号
VedioOcx:               ocx对象
deviceName:             使用地点
***********************************/
function SnapshotVedio(control) {
    if (iPlayHandle < 0) {
        alert("请先播放视频！");
        return;
    }
    var hikVideoOcx = document.getElementById(control);
    SnapshotWindow(hikVideoOcx, deviceName);
}

function SnapshotCurWindow(iDeviceIndex) {
    if (iDeviceIndex == null) iDeviceIndex = m_focusWin;
    var hikVideoOcx = document.getElementById("PPVS" + iDeviceIndex);
    if (m_bChannelPlay[iDeviceIndex - 1][1] != null && m_bChannelPlay[iDeviceIndex - 1][1] != "") {
        SnapshotWindow(hikVideoOcx, m_bChannelPlay[iDeviceIndex - 1][1]);
    } else {
        alert("请先播放视频");
    }
}

function SnapshotWindow(hikVideoOcx, deviceName) {
    if (hikVideoOcx == null) return;

    if (hikVideoOcx.TakePhotos()) {
        alert("截图成功.默认存储在C://HikElevator/Capture目录下.");
    }
}

/***************************
功能：视频播放记录
iDeviceCode: 电梯编码
isStart: true：startplay false:endplay
****************************/
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

//显示和隐藏页面所有的PPVS控件（因为它挡在最前面）
//state: block, none
function ShowHidePPVS(state) {
    var ocxs = document.getElementsByTagName("object");
    var len = ocxs.length;
    var ppvs = null;
    for (var i = 0; i < len; i++) {
        ppvs = ocxs[i];
        if (ppvs.id.toUpperCase().indexOf("PPVS") > -1) {
            ppvs.style.display = state;
        }
    }
}
