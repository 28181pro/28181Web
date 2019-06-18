$(function () {
    $("#selBARXB").select2({
        language: "zh-CN"
    });
    $("#selBARXB").select2("val", '1')
    $("#selZABJ").select2({
        language: "zh-CN"
    });
    $("#selZABJ").select2("val", '0')
    $("#selZSAJ").select2({
        language: "zh-CN"
    });
    $("#selZSAJ").select2("val", '0')
    DataBind();

    $(".btn-primary").click(function () {
        //备注格式：案件状态|案件类别|案件来源|主办人警员编号|协办人警员编号
        var mark = "";
        //直属案件
        var selZSAJ = $("#selZSAJ").val();
        if (selZSAJ != null){
            var res1 = $("#selZSAJ").select2("data")[0];
            $("#ZSAJ").val(res1.id);
        }
        //报案人性别
        var selBARXB = $("#selBARXB").val();
        if (selBARXB != null) {
            var res2 = $("#selBARXB").select2("data")[0];
            $("#BARXB").val(res2.id);
        }
        //是否专案
        var selZABJ = $("#selZABJ").val();
        if (selZABJ != null) {
            var res3 = $("#selZABJ").select2("data")[0];
            $("#ZABJ").val(res3.id);
        }
        ////是否有现场
        //var res4 = $("#selSFYXC").select2("data")[0];
        //$("#SFYXC").val(res4.id);
        //是否考察现场
        //var res = $("#selSFKCXC").select2("data")[0];
        //$("#SFKCXC").val(res.id);

        var selAJZT = $("#selAJZT").val();
        if (selAJZT != null) {
            var resAJZT = $("#selAJZT").select2("data")[0];
            //  console.log(resAJZT);
            if (resAJZT.id == "-1") {
                $("#AJZT").val("");
                mark = mark + "|";
            }
            else {
                $("#AJZT").val(resAJZT.id);
                mark = mark + "|" + resAJZT.text;
            }
        }
        else {
            mark = mark + "|";
        }
        var selCaseType = $("#selCaseType").val();
        if (selCaseType != null) {
            var resAJLB = $("#selCaseType").select2("data")[0];
            if (resAJLB.id == "-1") {
                $("#AJLB").val("");
                mark = mark + "|";
            }
            else {
                $("#AJLB").val(resAJLB.id);
                $("#AJLBMC").val(resAJLB.text);
                mark = mark + "|" + resAJLB.text;
            }
        }
        else {
            mark = mark + "|";
        }
        var selAJFAB = $("#selAJFAB").val();
        if (selAJFAB != null) {
            var resAJFAB = $("#selAJFAB").select2("data")[0];
            if (resAJFAB.id == "-1") {
                $("#AJFAB").val("");
            }
            else {
                $("#AJFAB").val(resAJFAB.id);
            }
        }
        var selAJLY = $("#selAJLY").val();
        if (selAJLY != null) {
            var resAJLY = $("#selAJLY").select2("data")[0];
            if (resAJLY.id == "-1") {
                $("#AJLY").val("");
                mark = mark + "|";
            }
            else {
                $("#AJLY").val(resAJLY.id);
                mark = mark + "|" + resAJLY.text;
            }
        }
        else {
            mark = mark + "|";
        }
        var selDBJB = $("#selDBJB").val();
        if (selDBJB != null) {
            var resDBJB = $("#selDBJB").select2("data")[0];
            if (resDBJB.id == "-1") {
                $("#DBJB").val("");
            }
            else {
                $("#DBJB").val(resDBJB.id);

            }
        }
        var selSLFS = $("#selSLFS").val();
        if (selSLFS != null) {
            var resSLFS = $("#selSLFS").select2("data")[0];
            if (resSLFS.id == "-1") {
                $("#SLFS").val("");
            }
            else {
                $("#SLFS").val(resSLFS.id);

            }
        }
        var selBARZJZL = $("#selBARZJZL").val();
        if (selBARZJZL != null) {
            var resBARZJZL = $("#selBARZJZL").select2("data")[0];
            if (resBARZJZL.id == "-1") {
                $("#BARZJZL").val("");
            }
            else {
                $("#BARZJZL").val(resBARZJZL.id);

            }
        }
        var selBARBMJB = $("#selBARBMJB").val();
        if (selBARBMJB != null) {
            var resBARBMJB = $("#selBARBMJB").select2("data")[0];
            if (resBARBMJB.id == "-1") {
                $("#BARBMJB").val("");
            }
            else {
                $("#BARBMJB").val(resBARBMJB.id);

            }
        }

        var sldwval = $("#selSLDWMC").val();
        if (sldwval != null) {
            var resSLDWMC = $("#selSLDWMC").select2("data")[0];
            // console.log("受理单位：");
            // console.log(resSLDWMC);
            if (resSLDWMC.id == "-1") {
                $("#SLDW").val("");
            }
            else {
                $("#SLDW").val(resSLDWMC.id);
                $("#SLDWMC").val(resSLDWMC.text);
            }
        }

        var corpidval = $("#selCorpID").val();
        if (corpidval != null) {
            var resCorpID = $("#selCorpID").select2("data")[0];
            if (resCorpID.id == "-1") {
                $("#ZBDW").val("");
            }
            else {
                $("#ZBDW").val(resCorpID.id);
                $("#ZBDWMC").val(resCorpID.text);
                $("#CorpName").val(resCorpID.text);
                $("#CorpID").val(resCorpID.code);
            }
        }
        var selZBRYID = $("#selZBRYID").val();
        //console.log("主办人：" + selZBRYID)
        if (selZBRYID != null) {
            var resZBRYID = $("#selZBRYID").select2("data")[0];
            //console.log(resXBRXM);
            if (resZBRYID.id == "-1") {
                $("#ZBR").val("");
                mark = mark + "|";
            }
            else {
                $("#ZBR").val(resZBRYID.id);
                $("#ZBRYMC").val(resZBRYID.text);
                $("#ZBRXM").val(resZBRYID.text);
                $("#ZBRYID").val(resZBRYID.code);
                mark = mark + "|" + resZBRYID.name;
            }
        }
        else {

            $("#ZBR").val("");
            $("#ZBRYMC").val("");
            $("#ZBRXM").val("");
            $("#ZBRYID").val("");
            mark = mark + "|" ;
        }

        //协办人信息
        var resXBRXM = $("#selXBRXM").select2("data");
        var xbr = "";
        var xbrxm = "";
        var xbryid = "";
        var xbrybh = "";
        if (resXBRXM.length > 0) {
            for (var i = 0; i < resXBRXM.length; i++) {
                xbr = xbr + "," + resXBRXM[i].id;
                xbrxm = xbrxm + "," + resXBRXM[i].text;
                xbryid = xbryid + "," + resXBRXM[i].code;
                xbrybh = xbrybh + "," + resXBRXM[i].name;
            }
            xbr = xbr.substr(1);
            xbrxm = xbrxm.substr(1);
            xbryid = xbryid.substr(1);
            xbrybh = xbrybh.substr(1);
        }
        $("#XBR").val(xbr);
        $("#XBRXM").val(xbrxm);
        $("#XBRYMC").val(xbrxm);
        $("#XBRYID").val(xbryid);
        mark = mark + "|" + xbrybh;

        var slrval = $("#selSLR").val();
        if (slrval != null) {
            var resSLR = $("#selSLR").select2("data")[0];
            if (resSLR.id == "-1") {
                $("#SLR").val("");
            }
            else {
                $("#SLR").val(resSLR.id);
                $("#SLRXM").val(resSLR.text);
            }
        }
        mark = mark.substr(1);
        $("#Mark").val(mark);
        // console.log("备注：" + mark);

    });

});

function DataBind() {
    //select2加载数据
    $.ajax({
        dataType: 'json',
        type: 'POST',
        url: '/CaseReadWriteTag/QueryDictionDataList',
        data: { type: 2 },
        error: function (xhr, error, thrown) {
        },
        success: function (chartsData) {

            $("#selAJZT").html("");
            $("#selAJZT").select2({
                language: "zh-CN",
                placeholder: '请选择',
                data: chartsData.aData
            });
            var ajzt = '-1';
            $("#selAJZT").val(ajzt).trigger("change");
            $("#selCaseType").html("");
            $("#selCaseType").select2({
                language: "zh-CN",
                placeholder: '请选择',
                data: chartsData.casetypeData
            });
            var ajlb = '-1';
            $("#selCaseType").val(ajlb).trigger("change");
            $("#selAJFAB").html("");
            $("#selAJFAB").select2({
                language: "zh-CN",
                data: chartsData.caseajfabData
            });
            var AJFAB = '-1';
            $("#selAJFAB").val(AJFAB).trigger("change");
            $("#selAJLY").html("");
            $("#selAJLY").select2({
                language: "zh-CN",
                data: chartsData.caselyData
            });
            var AJLY = '-1';
            $("#selAJLY").val(AJLY).trigger("change");
            $("#selDBJB").html("");
            $("#selDBJB").select2({
                language: "zh-CN",
                data: chartsData.casedbjbData
            });
            var DBJB = '-1';
            $("#selDBJB").val(DBJB).trigger("change");
            $("#selSLFS").html("");
            $("#selSLFS").select2({
                language: "zh-CN",
                data: chartsData.caseslfsData
            });
            var SLFS = '-1';
            $("#selSLFS").val(SLFS).trigger("change");
            $("#selBARZJZL").html("");
            $("#selBARZJZL").select2({
                language: "zh-CN",
                data: chartsData.casezjzlData
            });
            var BARZJZL = '-1';
            $("#selBARZJZL").val(BARZJZL).trigger("change");

            $("#selBARBMJB").html("");
            $("#selBARBMJB").select2({
                language: "zh-CN",
                data: chartsData.casebmjbData
            });
            var BARBMJB = '-1';
            $("#selBARBMJB").val(BARBMJB).trigger("change");
            $("#selSLDWMC").html("");
            $("#selSLDWMC").select2({
                language: "zh-CN",
                data: chartsData.corpList
            });
            var SLDW = '';
            // console.log("受理单位：" + SLDW);
            $("#selSLDWMC").val(SLDW).trigger("change");

            $("#selCorpID").html("");
            $("#selCorpID").select2({
                language: "zh-CN",
                data: chartsData.corpList
            });
            var corpid = '';
            //console.log("主办单位：" + corpid);
            $("#selCorpID").val(corpid).trigger("change");

            $("#selZBRYID").html("");
            $("#selZBRYID").select2({
                language: "zh-CN",
                data: chartsData.userList
            });
            var userid = '';
            //console.log("主办人：" + userid)
            $("#selZBRYID").val(userid).trigger("change");
            $("#selXBRXM").html("");
            $("#selXBRXM").select2({
                language: "zh-CN",
                data: chartsData.userList,
                multiple: true
            });
            var xbr = '';
            var xbrlist = xbr.split(',');
            // console.log("协办人：" + XBR);
            $("#selXBRXM").val(xbrlist).trigger("change");

            $("#selSLR").html("");
            $("#selSLR").select2({
                language: "zh-CN",
                data: chartsData.userList
            });
            var SLR = '';
            $("#selSLR").val(SLR).trigger("change");

        }
    });
}

$(function () {
    $('form').bind('submit', function (e) {
        $("button[type=submit]").attr('disabled', true);
        $("button[type=submit]").text("提交中");
        return true;
    });

});

$(function () {
    $('.inputcode').bind('blur', function (e) {
        var $value = $(this).val();
        if ($value == undefined || $value === "")
            return;
        var prevalue = $value.substring(0, 2);
        if (prevalue.toUpperCase() == "YL") {
            if ($value.length != 8) {
                $(this).val("");
            } else {
                $(this).val( $value.toUpperCase());
            }
        } else {
            if ($value.length != 14) {
                $(this).val("");
            }
        }
    });
    //$(".btn-export").on('click', function (e) {
    //    var url = $(e.currentTarget).attr("url") + getUrlParams();
    //    window.open(url, "_blank");
    //});

});

$(function () {
    $('form').bind('submit', function (e) {
        $("button[type=submit]").attr('disabled', true);
        $("button[type=submit]").text("提交中");
        return true;
    });

});

var GISImageType = '1' == 0 ? "jpg" : "png";

$(function () {
    $('.inputcode').bind('blur', function (e) {
        var $value = $(this).val();
        if ($value == undefined || $value === "")
            return;
        var prevalue = $value.substring(0, 2);
        if (prevalue.toUpperCase() == "YL") {
            if ($value.length != 8) {
                $(this).val("");
            } else {
                $(this).val( $value.toUpperCase());
            }
        } else {
            if ($value.length != 14) {
                $(this).val("");
            }
        }
    });
    //$(".btn-export").on('click', function (e) {
    //    var url = $(e.currentTarget).attr("url") + getUrlParams();
    //    window.open(url, "_blank");
    //});

});
