var regSvrIp = "";              //注册服务器
var regSvrPort = "";
var streamSvrIp = "";           //流媒体服务器
var streamSvrPort = "";
var iStreamCodeType = 1;        //0: 主码流 1: 子码流

var iStreamTransferMode = 1;    //流媒体取流类型(0: UDP 1:TCP)
var iServerRoute = 1;             //注册时配置的线路

function StartVideo() {
    var ocx = document.getElementById("ppvs");

    var channel2 =  '${channel}';
    var elevCode = '${channel.channelcode}' ;
    regSvrIp = '${channel.registerinfo.wanip}';
    regSvrPort = '${channel.registerinfo.wanport}';
    streamSvrIp = '${channel.streaminfo.wanip}';
    streamSvrPort = '${channel.streaminfo.wanport}';

    //ocx.StartVideo(streamSvrIp, streamSvrPort, regSvrIp, regSvrPort, elevCode, iServerRoute, iStreamCodeType, 0);
    ocx.playvideo(regSvrIp, regSvrPort,streamSvrIp, streamSvrPort,  elevCode, iServerRoute, iStreamCodeType, 0);


    VedioPlayLog(elevCode, true);
}

function StopVideo(ocx) {
    var ocx = document.getElementById("ppvs");
    if (ocx != null) {
        ocx.stopvideo();

        var obj = document.getElementById("btnRecord");
        StopRecord(ocx, obj);
    }

    var elevCode = $("#devid").val();
    VedioPlayLog(elevCode, false);
}