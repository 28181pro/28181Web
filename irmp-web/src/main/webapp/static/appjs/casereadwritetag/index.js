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
    var option = { ID: "#tbDataList", sAjaxSource: "/CaseReadWriteTag/selectPage" };
    option.aoColumns = [
        new columnCheckbox(),
        new columnSequence(),
        new columnCommon("casenumber", "案件编号", {
            mRender: function (data, type, obj) {
                var btn = columnOperationLink([
                    { "Type": "Detail", "Path": "/casereadwritetag/detail/" + obj.CaseNumber, "Visible": "@btnDetail", "Title": data }
                ]);
                return btn;
            },
            //bSortable: false,
            sWidth: "140px"
        }),
        new columnCommon("casename", "案件名称", {
            sClass: "limit"
        }),
        new columnCommon("organisername", "主办人", { sWidth: "60px" }),
        new columnCommon("corpname", "承办单位", { sClass: "limit" }),
        //new columnCommon("CabinetNum", "设备编号", { sWidth: "60px" }),
        // new columnCommon("Brand", "犯罪嫌疑人", { bVisible: false }),
        new columnCommon("casetypename", "案件类别", {
            sWidth: "60px",
            sClass: "limit"
            //bVisible: false
        }),
        new columnCommon("receptiondate", "受理时间", {
            mRender: function (data, type, obj) {
                return FormatJsonDate(data, true);
            },
            sWidth: "130px"
        }),
        new columnCommon("iscabinet", "是否入柜", {
            mRender: function (data, type, obj) {
                if (obj.isturnonbox == 1 || obj.isturnonbox == 2)
                    return " - ";
                return GetCabinetBind(data, obj.ispasteepc);
            },
            sWidth: "60px",
        }),
        new columnCommon("cellnumber", "所在柜子", {
            mRender: function (data, type, obj) {
                if (obj.iscabinet == null || obj.iscabinet == "" || obj.iscabinet == 0)
                    return " - ";
                if (data == null)
                    return " - ";
                return "【<b>" + obj.cabinetnum + GetBoxCellNumber(data) + "</b>号】";
            },
            sWidth: "90px",
        }),
        new columnCommon("casestate", "案件状态", {

            mRender: function (data, type, obj) {
                return GetAJState(data);
            },
            sWidth: "80px"
            //bVisible: false
        }),
        new columnCommon("nowstate", "卷宗状态", {
            mRender: function (data, type, obj) {
                return GetNowCaseState(data);
            }
        }),
        //new columnCommon("ISPasteEPC", "标签状态", {
        //    mRender: function (data, type, obj) {
        //        return GetEPCBind(data);
        //    },
        //    sWidth: "70px"
        //}),
        new columnCommon("id", "操作", {
            mRender: function (data, type, obj) {
                var text = obj.sftbq == 1 ? "重新绑定" : "绑定标签";
                var color = obj.sftbq == 1 ? "#FF9900" : "#337ab7";
                var btn = "<a href='javascript:void(0);' url='" + "/CaseReadWriteTag/EditWriteTag/" + data + "' onclick='layer.operate.open(this);' style='color: " + color + "' title='" + text + "'>" + text + "</a>";
                return btn;
            },
            bSortable: false,
            sWidth: "70px"
        })
    ];
    option.bAutoWidth = true;
    option.fnServerParams = prepareFilterParam();
    option.sGroups = [{ Name: "案件信息", Columns: "casenumber,casename,organisername,corpname,receptiondate,cellnumber,casetypename,casestate,iscabinet" }];
    tbl = new dataTableInit(option);
}
