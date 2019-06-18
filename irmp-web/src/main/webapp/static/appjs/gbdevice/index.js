var tbl;
var file_index;
var casetypelist;
$(function () {

    getSelectControl(refresh());
    //select2加载数据
    // $.ajax({
    //     dataType: 'json',
    //     type: 'POST',
    //     url: '/PublicVisit/QuerySelectDataList',
    //     data: {},
    //     error: function (xhr, error, thrown) {
    //     },
    //     success: function (chartsData) {
    //         $("#selCaseState").html("");
    //         $("#selCaseState").select2({
    //             language: "zh-CN",
    //             data: chartsData.aData
    //         });
    //         //单位信息
    //         $("#selCorpName").html("");
    //         $("#selCorpName").select2({
    //             language: "zh-CN",
    //             data: chartsData.corpList
    //         });
    //         //案件类别
    //         $("#selCaseType").html("");
    //         $("#selCaseType").select2({
    //             language: "zh-CN",
    //             data: chartsData.casetypeData
    //         });
    //
    //         refresh();
    //     }
    // });


    // refresh();
    //  BindCaseType();

    $(".btn-trash-o").on('click', function (e) {
        layer.operate.deleteop(e.currentTarget);
    });
});


function refresh() {
    //select2选择内容
    var stateval = $("#selCaseState").val();
    if (stateval == null || stateval == "" || stateval == -1) {
        $("#CaseState").val("");
    }
    else { $("#CaseState").val(stateval); }
    //单位信息
    var corpval = $("#selCorpName").val();
    if (corpval == null || corpval == "" || corpval == -1) {
        $("#CorpName").val("");
        $("#CorpID").val("");
    }
    else {
        var res1 = $("#selCorpName").select2("data")[0];
        $("#CorpName").val(res1.text);
        $("#CorpID").val(res1.code);
    }
    //案件类别
    var caseTypeval = $("#selCaseType").val();
    if (caseTypeval == null || caseTypeval == "" || caseTypeval == -1) {
        $("#CaseTypeID").val("");
    }
    else {
        var strCaseType = CaseTypeSubStr(caseTypeval);
        $("#CaseTypeID").val(strCaseType);
    }


    if (tbl != null) {
        tbl.fnDestroy();
    }
    getData();
    regCheckAll();
}

function getData() {
    var option = { ID: "#tbDataList", sAjaxSource: "/gbdevice/selectPage" };
    option.aoColumns = [
        new columnCheckbox(),
        new columnSequence(),
        new columnCommon("gbcode", "国标设备编号", {
            //bSortable: false,
            sWidth: "140px"
        }),
        new columnCommon("name", "设备名称", {
            sClass: "limit"
        }),
        new columnCommon("channelSum", "通道数", { sWidth: "60px" }),
        new columnCommon("ip", "出口IP",{
            sClass: "limit"
        }),
        //new columnCommon("CabinetNum", "设备编号", { sWidth: "60px" }),
        // new columnCommon("Brand", "犯罪嫌疑人", { bVisible: false }),
        new columnCommon("port", "出口端口", {
            sWidth: "60px",
            sClass: "limit"
            //bVisible: false
        }),
        new columnCommon("createtime", "新增时间", {
            mRender: function (data, type, obj) {
                return FormatJsonDate(data, true);
            },
            sWidth: "130px"
        }),
        new columnCommon("id", "操作", {
            mRender: function (data, type, obj) {
               "查询通道"
                "#FF9900"
                var btn = "<a href='javascript:void(0);' url='" + "/Channel/selectPage" + data + "' onclick='layer.operate.open(this);' style='color: " + color + "' title='" + text + "'>" + text + "</a>";
                return btn;
            },
            bSortable: false,
            sWidth: "70px"
        })
    ];

    option.bAutoWidth = true;
    option.fnServerParams = prepareFilterParam();
    option.sGroups = [{ Name: "国标设备信息", Columns: "gbcode, name,channelSum,IP,port,createtime" }];
    tbl = new dataTableInit(option);

}
