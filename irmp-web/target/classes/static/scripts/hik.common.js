//<reference path ="jquery-1.7-vsdoc.js">
// 跳转函数(obj：url地址或是按钮对象）
function RedirectToUrl(obj) {
    var url = "";
    if (typeof (obj) == "string") {
        url = obj;
    } else {
        url = obj.getAttribute("url");
    }
    url = url.charAt(0) == "/" ? url : "/" + url;
    window.location.href = url;
}

//将Json日期转化为标准日期格式
function FormatJsonDate(jsonDate, withTime) {
    if (jsonDate == null || typeof (jsonDate) == "undefined") {
        return "";
    }

    function pad(n) { return n < 10 ? '0' + n : n }

    var d = new Date(parseInt(jsonDate));
    //var d = jsonDate.replace(/"\\\/(Date\(-?\d+\))\\\/"/g, 'new new Date($1)');
    var result = d.getFullYear() + '-'
      + pad(d.getMonth() + 1) + '-'
      + pad(d.getDate());

    if (withTime) {
        result += ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds());
    }
    return result;
}

function FormatDate(num) {
    if (num == null) {
        return "";
    }

    var d = new Date(1970, 0, 1, 8, 0, 0);
    d.setSeconds(num);

    return d.format("yyyy-MM-dd hh:mm:ss");
}

// 截取固定长度子字符串 sSource为字符串, length为长度
function CutString(sSource, length) {
    if (sSource == "" || sSource == null) {
        return "";
    }
    else {
        sSource = sSource.replace(/\r\n/g, "");
        //sSource = sSource.replace(" ", "");

        var cutLength = 0;
        var encharCount = 0;
        var cnstring = 0;
        var enstring = 0;
        var sourceLength = sSource.length;
        var sourceByteLength = GetByteLength(sSource);

        if (sourceByteLength / sourceLength == 2 || sourceLength < length) {
            cutLength = length;
        }
        else if (sourceByteLength == sourceLength) {
            cutLength = 2 * length;
        }
        else {
            for (var i = 0; i < sourceLength; i++) {
                var charbytes = sSource.charAt(i).match(/[^\x00-\xff]/) != null ? 2 : 1;
                if (charbytes == 2) {
                    cutLength++;
                    length--;
                }
                else if (charbytes == 1) {
                    cutLength++;
                    encharCount++;
                    if (encharCount % 2 == 0) {
                        length--;
                    }
                }
                if (length == 0) {
                    cnstring = GetByteLength(sSource.substring(i - 1, i));
                    if (sourceLength > i + 1) {
                        enstring = GetByteLength(sSource.substring(i + 1, i + 2));
                    }
                    if (encharCount % 2 == 0 && charbytes == 2) {
                        cutLength = i + 1;
                        break;
                    }
                    else if (charbytes == 1 && enstring == 1 && cnstring == 1) {
                        cutLength = i + 2;
                        break;
                    }
                    else {
                        cutLength = i;
                        break;
                    }
                }
            }
        }
        return cutLength >= sourceLength ? sSource : sSource.substring(0, cutLength) + "...";
    }

}

function GetByteLength(str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        realLength += (charCode >= 0 && charCode <= 128) ? 1 : 2;
    }
    return realLength;
}

function PropertiesTest(obj) {
    var msg = '';
    for (var p in obj) {
        msg += p + "\t";
    }
    alert(msg);
}

//保留2位小数（强制）
function changeTwoDecimal(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

Date.prototype.format = function (f) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(f))
        f = f.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(f))
            f = f.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return f;
}

function GetCategory(target, category) {
    if (target == 1) {
        if (category == 1) {
            return "执法主体素质能力工作";
        }
        if (category == 2) {
            return "执法纪律和执法监督";
        }
        if (category == 3) {
            return "执法公开";
        }
        if (category == 4) {
            return "网上监督";
        }
        if (category == 5) {
            return "其他评分标准";
        }
    }
    if (target == 2) {
        if (category == 1) {
            return "接警";
        }
        if (category == 2) {
            return "处警";
        }
    }
    if (target == 3) {
        if (category == 1) {
            return "一般规定";
        }
        if (category == 2) {
            return "受立撤案";
        }
        if (category == 3) {
            return "证据收集保全";
        }
        if (category == 4) {
            return "律师参与刑事诉讼";
        }
        if (category == 5) {
            return "强制措施";
        }
        if (category == 6) {
            return "讯（询）问";
        }
        if (category == 7) {
            return "勘验、检查";
        }
        if (category == 8) {
            return "搜查";
        }
        if (category == 9) {
            return "查封、扣押";
        }
        if (category == 10) {
            return "查询、冻结";
        }
        if (category == 11) {
            return "鉴定";
        }
        if (category == 12) {
            return "辨认";
        }
        if (category == 13) {
            return "技术侦查";
        }
        if (category == 14) {
            return "侦查终结";
        }
        if (category == 15) {
            return "案件处理";
        }
        if (category == 16) {
            return "案件处理";
        }
    }
    if (target == 4) {
        if (category == 1) {
            return "一般规定";
        }
        if (category == 2) {
            return "受案";
        }
        if (category == 3) {
            return "回避";
        }
        if (category == 4) {
            return "调查";
        }
        if (category == 5) {
            return "简易程序";
        }
        if (category == 6) {
            return "询问";
        }
        if (category == 7) {
            return "勘验、检查";
        }
        if (category == 8) {
            return "鉴定";
        }
        if (category == 9) {
            return "辨认";
        }
        if (category == 10) {
            return "证据保全";
        }
        if (category == 11) {
            return "听证";
        }
        if (category == 12) {
            return "处罚前告知";
        }
        if (category == 13) {
            return "案件处理";
        }
        if (category == 14) {
            return "执行";
        }
        if (category == 15) {
            return "涉案财物处理";
        }
        if (category == 16) {
            return "调解";
        }
    }
    if (target == 5) {
        if (category == 1) {
            return "执行";
        }
        if (category == 2) {
            return "执法场所管理";
        }
        if (category == 3) {
            return "执法效果";
        }
        if (category == 4) {
            return "其他评分标准";
        }
    }
    if (target == 6) {
        if (category == 1) {
            return "法制员考评数据来源";
        }
        if (category == 2) {
            return "案件审核";
        }
        if (category == 3) {
            return "执法监督";
        }
        if (category == 4) {
            return "执法制度与信息化建设";
        }
        if (category == 5) {
            return "执法培训与服务";
        }
        if (category == 6) {
            return "执法培训与服务";
        }
    }

}

function GetTargetName(target) {
    if (target == 1 || target == 5) {
        return "执法单位考评";
    }
    if (target == 2) {
        return "接处警考评";
    }
    if (target == 3) {
        return "刑事案件审核考评";
    }
    if (target == 4) {
        return "行政案件审核考评";
    }
    if (target == 6) {
        return "法制员考评";
    }
}

function GetAssessTypeName(assesstype) {
    if (assesstype == 1) {
        return "单位";
    }
    else {
        return "个人";
    }
}

function formatTime(second) {
    return [parseInt(second / 60 / 60), parseInt(second / 60 % 60), parseInt(second % 60)].join(":").replace(/\b(\d)\b/g, "0$1");
}
//时间秒数格式化
function formatSecondTime(s) {
    var t;
    if (s > -1) {
        hour = Math.floor(s / 3600);
        min = Math.floor(s / 60) % 60;
        sec = s % 60;
        day = parseInt(hour / 24);
        if (day > 0) {
            hour = hour - 24 * day;
            t = day + "天 " + hour + "时";
        }
        else t = hour + "时";
        if (min < 10) { t += "0"; }
        t += min + "分";
        if (sec < 10) { t += "0"; }
        t += sec + "秒";
    }
    return t;
}

Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {
        "M+": this.getMonth() + 1,                 //月份   
        "d+": this.getDate(),                    //日   
        "h+": this.getHours(),                   //小时   
        "m+": this.getMinutes(),                 //分   
        "s+": this.getSeconds(),                 //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds()             //毫秒   
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

String.prototype.replaceAll = function (str1, str2) {
    var str = this;
    var result = str.replace(eval("/" + str1 + "/gi"), str2);
    return result;
}

function getJsonName(sJson, value) {
    var aData = typeof (JSON) == 'undefined' ? eval(sJson) : JSON.parse(sJson);
    var name = "";
    for (var i = 0; i < aData.length; i++) {
        if (aData[i].Value == value) {
            name = aData[i].Name;
            break;
        }
    }
    return name;
}

function GetState(state) {
    if (state == 1) {
        return '<span class="label label-primary">启用</span>';
    }
    else if (state == -1) {
        return '<span class="label label-danger">冻结</span>';
    }
    else if (state == 2) {
        return '<span class="label label-warning">待批</span>';
    }
    else if (state == 3) {
        return '<span class="label label-danger">驳回</span>';
    } else {
        return '<span class="label label-danger">注销</span>';
    }
}


function FormatSmsStatus(status) {
    switch (status) {
        case 0:
            return '<span class="label label-info">未发送</span>';
            break;
        case 1:
            return '<span class="label label-warning">发送中</span>';
            break;
        case 2:
            return '<span class="label label-success">发送成功</span>';
            break;
        case 3:
            return '<span class="label label-danger">发送失败</span>';
            break;
        case 9:
            return '<span class="label label-default">取消发送</span>';
            break;
        default:
    }

};

function FormatPushStatus(status) {
    switch (status) {
        case 0:
            return '<span class="label label-info">未发送</span>';
            break;
        case 1:
            return '<span class="label label-warning">发送中</span>';
            break;
        case 2:
            return '<span class="label label-success">发送成功(在线)</span>';
            break;
        case 3:
            return '<span class="label label-success">发送成功(离线)</span>';
            break;
        case 4:
            return '<span class="label label-danger">发送失败</span>';
            break;
        case 9:
            return '<span class="label label-default">取消发送</span>';
            break;
        default:
    }

};

function FormatNeedReply(need) {
    switch (need) {
        case 0:
            return '<span class="label label-primary">重发</span>';
            break;
        case 1:
            return '<span class="label label-danger">已发送</span>';

            break;

        default:
            return "";
    }

}

function GetOnlineStatus(state) {
    if (state == 1) {
        return '<span class="label label-primary">在线</span>';
    }
    else if (state == 0) {
        return '<span class="label label-danger">离线</span>';
    } else {
        return '<span class="label label-danger">离线</span>';
    }
}

function GetGender(data) {
    if (data == 1) {
        return "男";
    } else {
        return "女";
    }
}



function GetVehicleCaseState(data) {
    if (data == 0) {
        return "警方破案";
    } else if (data == 2) {
        return "车主撤案";
    } else if (data == 3) {
        return "协助寻回";
    } else {
        return '<span class="label label-danger">车辆被盗</span>';
    }
}


function GetPersonCaseState(data) {
    if (data == 1) {
        return '<span class="label label-warning">已破案</span>';
    } else if (data == 2) {
        return '<span class="label label-success">已寻回</span>';
    } else if (data == 3) {
        return '<span class="label label-info">已归还</span>';
    } else {
        return '<span class="label label-danger">已走失</span>';
    }
}

function GetGoodsCaseState(data) {
    if (data == 1) {
        return '<span class="label label-warning">已破案</span>';
    } else if (data == 2) {
        return '<span class="label label-success">已寻回</span>';
    } else if (data == 3) {
        return '<span class="label label-info">已归还</span>';
    } else {
        return '<span class="label label-danger">已遗失</span>';
    }
}

function GetSchoolCaseState(data) {
    if (data == 1) {
        return '<span class="label label-warning">已破案</span>';
    } else if (data == 2) {
        return '<span class="label label-success">已寻回</span>';
    } else if (data == 3) {
        return '<span class="label label-info">已归还</span>';
    } else {
        return '<span class="label label-danger">已走失</span>';
    }
}

function GetCaseState(data) {
    if (data == "1") {
        return '<span class="label label-danger">已被盗</span>';
    }
    else {
        return '<span class="label label-primary">正常</span>';
    }
}
//破案撤空/撤案撤控/寻回撤控/主动撤控
function GetDispatchState(data) {
    if (data == "1") {
        return '<span class="label label-primary">预警</span>';
    }
    else {
        return '<span class="label label-danger">撤销</span>';
    }
}
//0：案件预警提醒；1：借阅提醒；2：上交提醒；3：未贴标签提醒；4：上交检察院提醒；5：未关柜门告警；6：行政案件提醒；7：刑事案件提醒；8：卷宗久放未动；9：移交提醒；10：移交办理
function GetDispatchType(data) {
    switch (data) {
        case 0:
            return "案件预警提醒";
            break;
        case 1:
            return "借阅提醒";
            break;
        case 2:
            return "上交提醒";
            break;
        case 3:
            return "未贴标签提醒";
            break;
        case 4:
            return "上交检察院提醒";
            break;
        case 5:
            return "未关柜门告警";
            break;
        case 6:
            return "行政案件提醒";
            break;
        case 7:
            return "刑事案件提醒";
            break;
        case 8:
            return "卷宗久放未动";
            break;
        case 9:
            return "移交提醒";
            break;
        case 10:
            return "移交办理";
            break;
        case 11:
            return "上交检察院退回办理";
            break;
        case 12:
            return "归档退回办理";
            break;
        case 13:
            return "借阅超期";
            break;
        case 14:
            return "借阅超期";
            break;
        case 15:
            return "应入柜未入柜";
            break;
        case 16:
            return "应入柜未入柜";
            break;
        case 17:
            return "应入柜未入柜";
            break;
        case 18:
            return "应入柜未入柜";
            break;
        case 19:
            return "行政案件办理期限到期";
            break;
        case 20:
            return "行政案件办理期限到期";
            break;
        case 21:
            return "久放未动";
            break;
        case 22:
            return "久放未动";
            break;
        case 23:
            return "久放未动";
            break;
        case 24:
            return "久放未动";
            break;
        case 25:
            return "待归档";
            break;
        case 26:
            return "待归档";
            break;
        case 26:
            return "待归档";
            break;
        case 27:
            return "待归档";
            break;
        case 28:
            return "待归档";
            break;
        case 29:
            return "拘留到期";
            break;
        case 30:
            return "拘留到期";
            break;
        case 31:
            return "取保候审到期";
            break;
        case 32:
            return "监视居住到期";
            break;
        case 33:
            return "移诉提醒";
            break;
        case 34:
            return "整改到期提醒";
            break;
        case 35:
            return "卷宗整改提醒";
            break;
        case 36:
            return "整改到期提醒";
            break;
    }
}


function GetInsurancedState(data) {

    if (data == "1") {
        return '否';
    }
    else {
        return '是';
    }
}

function GetUseState(data) {
    if (data == "1") {
        return '<span class="label label-danger">已锁定</span>';
    }
    else {
        return '<span class="label label-primary">正常</span>';
    }
}

function GetBatteryState(data) {
    if (data == 1) {
        return '<span class="label label-danger">低电量</span>';
    } else {
        return '<span class="label label-info">正常</span>';
    }
}

function GetBatteryHistory(data) {
    if (data == 0) {
        return '<span class="label label-danger">低电量</span>';
    } else {
        return '<span class="label label-info">正常</span>';
    }
}

function ToCDB(str) {
    var tmp = "";
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 65248 && str.charCodeAt(i) < 65375) {
            tmp += String.fromCharCode(str.charCodeAt(i) - 65248);
        }
        else {
            tmp += String.fromCharCode(str.charCodeAt(i));
        }
    }
    return tmp;
}

function GetAbnormalTrackState(data) {
    if (data == 1) {
        return '<span class="label label-success">已撤销</span>';
    } else {
        return '<span class="label label-danger">异常</span>';
    }
}

function GetTrackAbnormalExceptionState(data) {
    if (data == 1) {
        return '<span class="label label-success">已恢复</span>';
    } else {
        return '<span class="label label-danger">已排除</span>';
    }
}

function FormatDeviceUpgradeState(state) {
    if (state == 0) {
        return '待升级 ';
    } else if (state == 1) {
        return '升级成功';
    } else if (state == 2) {
        return '升级失败';
    } else if (state == 3) {
        return '升级中';
    } else {
        return '取消升级';
    }
}
function getUrlParams(id) {
    var el = id ? $("#" + id) : $(".ibox-search");
    var searchParams = "?";
    el.find("input").each(function () {
        if ($(this).attr("type") == "text" || $(this).attr("type") == "hidden") {
            searchParams += $(this).attr('id') + '=' + $(this).val() + '&';
        }
    });

    el.find("select").each(function () {
        searchParams += $(this).attr('id') + '=' + $(this).val() + '&';
    });

    if (searchParams.length > 0)
        searchParams = searchParams.substr(0, searchParams.length - 1);

    return searchParams;
}


//案件类别下拉框选择联动事件
var temp_html;
function btnChange(e) {
    temp_html = "";
    var code = $(e).attr("code");
    var selval = e.options[e.options.selectedIndex].value;
    var seltxt = e.options[e.options.selectedIndex].text;
    var codeval = selval;
    if (code != 3) {
        var tagState1 = document.getElementById("AJLB" + code);
        var tagState2 = document.getElementById("AJLB2");

        var tagState1count = tagState1.options.length;
        if (tagState1count > 0) {
            for (var i = 1; i < tagState1count; i++) {
                tagState1.options.remove(1);
            }
        }
        var tagState2count = tagState2.options.length;
        if (tagState2count > 0) {
            for (var i = 1; i < tagState2count; i++) {
                tagState2.options.remove(1);
            }
        }
        if ((code == 1 || code == 2) && seltxt != "全部") {
            if (code == 2) {
                codeval = codeval.substr(0, 4);
            }
            else {
                codeval = codeval.substr(0, 2);
            }
            $.ajax({
                type: "POST",
                url: "/CaseReadWriteTag/BindCaseTypeList",
                data: { code: codeval, type: code },
                success: function (data) {
                    $.each(data.aData, function (i, item) {
                        var opt = document.createElement("option");
                        opt.text = item.Name;
                        opt.value = item.Value;
                        tagState1.options.add(opt);
                    })
                },
                error: function (msg) {
                    layer.alert('数据加载失败');
                }
            });

            //for (var i = 0; i < 4; i++) {
            //    var opt = document.createElement("option");
            //    var str = codeval + "0" + i.toString();
            //    if (code == 1) {
            //        str = codeval + "0" + i + "00";
            //    }
            //    opt.text = "选择" + str;
            //    opt.value = str;
            //    tagState1.options.add(opt);
            //}
        }
    }
    if (code == 2 && seltxt == "全部") {
        var ajlb = document.getElementById("AJLB0");
        codeval = ajlb.options[ajlb.options.selectedIndex].value;
        codeval = codeval.substr(0, 2)
    }
    else if (code == 3 && seltxt == "全部") {
        var ajlb = document.getElementById("AJLB1");
        codeval = ajlb.options[ajlb.options.selectedIndex].value;
        codeval = codeval.substr(0, 4)
    }
    $(".hidajlb").val(codeval);

}

//案件类别加载
function BindCaseType() {
    var tagState = document.getElementById("AJLB0");
    if (tagState == null) {
        return;
    }
    var tagStatecount = tagState.options.length;
    if (tagStatecount > 0) {
        for (var i = 1; i < tagStatecount; i++) {
            tagState.options.remove(1);
        }
    }
    $.ajax({
        type: "POST",
        url: "/CaseReadWriteTag/BindCaseTypeList",
        data: { code: "", type: 0 },
        success: function (data) {
            $.each(data.aData, function (i, item) {
                var opt = document.createElement("option");
                opt.text = item.Name;
                opt.value = item.Value;
                tagState.options.add(opt);
            })
        },
        error: function (msg) {
            layer.alert('数据加载失败');
        }
    });
}

//考评项根目录加载
function BindEvaluateConfig(e) {
    var tagState = document.getElementById("selTarget");

    if (tagState == null) {
        return;
    }
    var code = $("#selTarget").attr("code");
    var deltype = $("#selTarget").attr("deltype");
    if (deltype == 1) {
        var tagStatecount = tagState.options.length;
        if (tagStatecount > 0) {
            for (var i = 1; i < tagStatecount; i++) {
                tagState.options.remove(1);
            }
        }
    }
    $.ajax({
        dataType: 'json',
        type: "POST",
        url: "/PublicVisit/QueyEvaluateConfigList",
        data: { code: code, type: 1 },
        success: function (data) {
            $.each(data.aData, function (i, item) {
                var opt = document.createElement("option");
                opt.text = item.Description;
                opt.value = item.ID;
                opt.category = item.Category;
                tagState.options.add(opt);
            })
        },
        error: function (msg) {
            layer.alert('数据加载失败');
        }
    });
}
//根目录选择
function selChangeRoot(e) {
    var code = $(e).attr("code");
    var deltype = $(e).attr("deltype");
    var selval = e.options[e.options.selectedIndex].value;
    var seltxt = e.options[e.options.selectedIndex].text;
    var codeval = selval;
    var tagState2 = document.getElementById("selCategory");
    var tagState2count = tagState2.options.length;
    if (deltype == 1) {
        if (tagState2count > 0) {
            for (var i = 1; i < tagState2count; i++) {
                tagState2.options.remove(1);
            }
        }
    }
    $.ajax({
        dataType: 'json',
        type: "POST",
        url: "/PublicVisit/QueyEvaluateConfigList",
        data: { parintid: selval, code: code, type: 2 },
        success: function (data) {
            $.each(data.aData, function (i, item) {
                var opt = document.createElement("option");
                opt.text = item.Description;
                opt.value = item.ID;
                opt.target = item.Target;
                tagState2.options.add(opt);
            })
        },
        error: function (msg) {
            layer.alert('数据加载失败');
        }
    });
}


//案件类别截取操作处理
function CaseTypeSubStr(casetype) {
    var strCaseType = casetype;
    var strlength = casetype.length;
    var casetypetwo = casetype.substr(strlength - 2, 2);
    var casetypefour = casetype.substr(strlength - 4, 4);
    if (casetypefour == "0000") {
        strCaseType = casetype.substr(0, strlength - 4);
    }
    else if (casetypetwo == "00") {
        strCaseType = casetype.substr(0, strlength - 2);
    }
    return strCaseType;

}


//案件类别名称获取
function TableCaseTypeBind(code, caselist) {
    $.each(caselist, function (i, item) {
        if (item.Value == code) {
            return item.Name;
        }
    })
}

//案件类别名称获取
function GetEPCBind(state) {
    if (state == 1) {
        return '<span class="label label-primary">已贴标签</span>';
    }
    else {
        return '<span class="label label-danger">未贴标签</span>';
    }
}


function GetTurnOnBind(state) {
    if (state == 1) {
        return '<span class="label label-primary">已上交</span>';
    }
    else if (state == 2) {
        return '<span class="label label-success">已上交</span>';
    }
    else if (state == 3) {
        return '<span class="label label-warning">上交未入柜</span>';
    }
    else {
        return '<span class="label label-danger">未上交</span>';
    }

}
//归档状态
function GetCaseArchivesStateBind(state) {
    if (state == 0) {
        return '<span class="label label-danger">退回</span>';
    }
    else {
        return '<span class="label label-primary">归档</span>';
    }

}

//案件状态
function GetAJState(state) {
    switch (parseInt(state)) {
        case 1:
            return '报案';
            break;
        case 2:
            return '受案';
            break;
        case 3:
            return '立案';
            break;
        case 4:
            return '破案';
            break;
        case 5:
            return '结案';
            break;
        case 6:
            return '撤销案';
            break;
        case 7:
            return '不立案';
            break;
        case 8:
            return '初查';
            break;
        case 9:
            return '归档';
            break;
        case 18:
            return '转行政案件';
            break;
        case 19:
            return '指定管辖';
            break;
        case 20:
            return '同意受理';
            break;
        case 21:
            return '不予处理';
            break;
        case 22:
            return '移送';
            break;
        case 23:
            return '终止案件调查';
            break;
        case 24:
            return '调查';
            break;
        case 25:
            return '处罚';
            break;
        case 26:
            return '劳动教养';
            break;
        case 27:
            return '治安调解';
            break;
        case 28:
            return '转刑事案件';
            break;
        case 29:
            return '暂缓执行';
            break;
        case 30:
            return '复议';
            break;
        case 31:
            return '诉讼';
            break;
        case 32:
            return '撤销行政处罚';
            break;
        case 33:
            return '当场处罚';
            break;
        case 34:
            return '简易治安调解';
            break;
        case 35:
            return '简易事故办理';
            break;
        case 36:
            return '一般事故办理';
            break;
        case 37:
            return '移交';
            break;
        default:
            return '';
            break;
    }
}

//案件是否入柜
function GetCabinetBind(state, tag) {
    if (state == 1) {
        return '<span class="label label-success">在柜</span>';
    } else if (tag == 1) {
        return '<span class="label label-warning">离柜</span>';
    }
    else {
        return '<span class="label label-danger">未入柜</span>';
    }
}

//是否指纹采集
function GetFingerPrintState(data) {

    if (data == "1") {
        return '<span class="label label-success">已采集</span>';
    }
    else {
        return '<span class="label label-danger">未采集</span>';
    }
}
//是否分配柜子编号
function GetCupboardState(data) {

    if (data == "1") {
        return '<span class="label label-success">已分配</span>';
    }
    else {
        return '<span class="label label-danger">未分配</span>';
    }
}


function getCaseTypeData() {
    $.ajax({
        type: "POST",
        url: "/CaseReadWriteTag/BindCaseTypeList",
        data: { code: "", type: 0 },
        success: function (data) {
            casetypelist = data.DataAll;
        },
        error: function (msg) {
            layer.alert('数据加载失败');
        }
    });
}
//案件贴标签预警状态获取
function GetAlarmStateBind(state) {
    if (state == 1) {
        return '<span class="label label-warning">告警</span>';
    }
    else if (state == 2) {
        return '<span class="label label-danger">超期告警</span>';
    }
    else {
        return '<span class="label label-success">预警</span>';
    }
}


//案件借阅状态获取
function GetBorrowedBind(state) {
    if (state == 0) {
        return '<span class="label label-warning">借出</span>';
    }
    else {
        return '<span class="label label-success">已归还</span>';
    }
}

//柜子编号
function GetBoxCellNumber(number) {
    switch (parseInt(number)) {
        case 1:
            return 'A1';
            break;
        case 2:
            return 'A5';
            break;
        case 3:
            return 'A6';
            break;
        case 4:
            return 'A7';
            break;
        case 5:
            return 'A2';
            break;
        case 6:
            return 'A8';
            break;
        case 7:
            return 'A3';
            break;
        case 8:
            return 'A9';
            break;
        case 9:
            return 'A4';
            break;
        case 10:
            return 'A10';
            break;
        case 11:
            return 'B1';
            break;
        case 12:
            return 'B7';
            break;
        case 13:
            return 'B2';
            break;
        case 14:
            return 'B8';
            break;
        case 15:
            return 'B3';
            break;
        case 16:
            return 'B9';
            break;
        case 17:
            return 'B4';
            break;
        case 18:
            return 'B10';
            break;
        case 19:
            return 'B5';
            break;
        case 20:
            return 'B11';
            break;
        case 21:
            return 'B6';
            break;
        case 22:
            return 'B12';
            break;
        case 23:
            return 'C1';
            break;
        case 24:
            return 'C7';
            break;
        case 25:
            return 'C2';
            break;
        case 26:
            return 'C8';
            break;
        case 27:
            return 'C3';
            break;
        case 28:
            return 'C9';
            break;
        case 29:
            return 'C4';
            break;
        case 30:
            return 'C10';
            break;
        case 31:
            return 'C5';
            break;
        case 32:
            return 'C11';
            break;
        case 33:
            return 'C6';
            break;
        case 34:
            return 'C12';
            break;
        case 35:
            return 'D1';
            break;
        case 36:
            return 'D7';
            break;
        case 37:
            return 'D2';
            break;
        case 38:
            return 'D8';
            break;
        case 39:
            return 'D3';
            break;
        case 40:
            return 'D9';
            break;
        case 41:
            return 'D4';
            break;
        case 42:
            return 'D10';
            break;
        case 43:
            return 'D5';
            break;
        case 44:
            return 'D11';
            break;
        case 45:
            return 'D6';
            break;
        case 46:
            return 'D12';
            break;
        default:
            return '';
            break;
    }
}


//案件状态 0:未贴标签;1:已贴标签;2:未入柜;3:已入柜;4:已上交;5:上交入柜 ;6:借阅 ;7: 归还;8:上交检察院;9:结案归档; 10:外部借阅;11：移交办理;12：上交检察院退回；13：归档改办理

function GetNowCaseState(state) {
    switch (parseInt(state)) {
        case 0:
            return '未贴标签';
            break;
        case 1:
            return '已贴标签';
            break;
        case 2:
            return '未入柜';
            break;
        case 3:
            return '已入柜';
            break;
        case 4:
            return '已上交';
            break;
        case 5:
            return '上交入柜';
            break;
        case 6:
            return '卷宗借阅';
            break;
        case 7:
            return '卷宗归还';
            break;
        case 8:
            return '上交检察院';
            break;
        case 9:
            return '<span class="label label-primary">结案归档</span>';
            break;
        case 10:
            return '外部借阅';
            break;
        case 11:
            return '移交办理';
            break;
        case 12:
            return '检察院退回';
            break;
        case 13:
            return '归档改办理';
            break;
        default:
            return '';
            break;
    }
}

//案件移交状态；0:移交；1:接收；2:办理
function GetHandleState(state) {
    switch (parseInt(state)) {
        case 0:
            return '<span class="label label-primary">移交</span>';
            break;
        case 1:
            return '<span class="label label-success">接收办理</span>';
            break;
        case 2:
            return '<span class="label label-danger">接收办理</span>';
            break;
        default:
            return '';
            break;
    }
}



//告警类型；2：上交县局提醒；6：行政案件办理提醒；7：刑事案件办理提醒；8：在柜久放未动提醒；9：卷宗移交接收提醒；10：卷宗移交办理提醒；
function GetAlarmType(state) {
    switch (parseInt(state)) {
        case 2:
            return '上交县局提醒';
            break;
        case 6:
            return '行政案件办理提醒';
            break;
        case 7:
            return '刑事案件办理提醒';
            break;
        case 8:
            return '在柜久放未动提醒';
            break;
        case 9:
            return '移交接收提醒';
            break;
        case 10:
            return '移交办理提醒';
            break;
        default:
            return '';
            break;
    }
}

//出库类型
function GetOutApplyType(type) {
    switch (parseInt(type)) {
        case 1:
            return '发还';
            break;
        case 2:
            return '销毁';
            break;
        default:
            return '';
            break;
    }
}

//流转记录类型
function GetFlowRecordType(type) {
    switch (parseInt(type)) {
        case 11:
            return '新增标签';
            break;
        case 12:
            return '标签变更';
            break;
        case 21:
            return '物品入柜';
            break;
        case 22:
            return '物品离柜';
            break;
        case 21:
            return '标签变更';
            break;
        case 31:
            return '发还';
            break;
        case 41:
            return '销毁';
            break;
        case 51:
            return '物品入库';
            break;
        case 52:
            return '物品出库';
            break;
        default:
            return '';
            break;
    }
}

//设备升级类型
function GetUpgradeType(type) {
    switch (parseInt(type)) {
        case 0:
            return '卷宗柜';
            break;
        case 1:
            return '物证柜';
            break;
        default:
            return '';
            break;
    }
}

//案件问题清单整改期限
function GetRectificationTime(type) {
    switch (parseInt(type)) {
        case 1:
            return '一天';
            break;
        case 2:
            return '三天';
            break;
        case 3:
            return '五天';
            break;
        case 4:
            return '七天';
            break;
        case 5:
            return '十天';
            break;
        case 6:
            return '十一天';
            break;
        default:
            return '';
            break;
    }
}



