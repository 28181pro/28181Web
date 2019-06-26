
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
        nvrSvrIp = objRegInfo.NVRSvrIp;
        nvrSvrPort = objRegInfo.NVRSvrPort;
        deviceIp = objRegInfo.ElevIP;

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


function Subscribe(hikOcx, elevCode, stateIP, statePort, accessIP, accessPort) {
    if (stateIP == null || statePort == null) {
        ShowMsg("实时状态服务器地址获取失败，请重试!");
        return;
    }
    if (accessIP == null || accessPort == null) {
        ShowMsg("接入服务器地址获取失败，请重试!");
        return;
    }
    var t = hikOcx.SubscribeMessage(elevCode, stateIP, statePort, accessIP, accessPort);
}

function Unsubscribe(hikOcx) {
    var t = hikOcx.UnSubscribeMessage();
}

function SubscribeMessageEX(ocx, stateIP, statePort, accIP, accPort, deviceType, elevCode, onlineIP) {
    if (stateIP == null || statePort == null) {
        ShowMsg("实时状态服务器地址获取失败，请重试!");
        return;
    }
    if (accIP == null || accPort == null) {
        ShowMsg("接入服务器地址获取失败，请重试!");
        return;
    }
    var t = ocx.SubscribeMessageEX(stateIP, statePort, accIP, accPort, deviceType, 1, 1, elevCode, onlineIP);
    return t;
}


function UnSubscribeMessageEX(ocx, t) {
    ocx.UnMessageSubscribe(t, 1);
}