var tbl;
$(function () {
    refresh();
    $(".btn-cancel").click(function () {
        var ids = getSelectRowIds();
        if (ids == "") {
            layer.alert('请选择操作项!');
            return;
        }
        var index = layer.prompt({
            id: "user-cancel-prompt",
            btn: ['注销', '取消'],
            title: '<div class="console-title"><h5>请填写注销原因</h5></div>',
            formType: 2,
            content: '<textarea class="layui-layer-input"></textarea><br><lable>操作人：</lable> '
        }, function (text) {

            var data = {ids: ids, msg: text};
            $.ajax({
                type: "POST",
                url: "/user/cancel",
                data: data,
                success: function (data) {
                    layer.alert(data);
                    refresh();
                    layer.close(index);
                },
                error: function (msg) {
                    layer.alert('数据加载失败');
                    layer.close(index);
                }
            });
        });
    });


    $(".btn-zwcj").click(function () {
        var ids = getSelectRowIds();
        if (ids == "") {
            layer.alert('请选择操作项!');
            return;
        }
        if (ids.indexOf(",") > -1) {
            layer.alert('请选择一项操作!');
            return;
        }
        fingerPrintCollect(ids);
    })

    $(".btn-trash-o").on('click', function (e) {
        layer.operate.deleteop(e.currentTarget);
    });

});

function refresh() {
    if (tbl != null) {
        tbl.fnDestroy();
    }
    getData();
    regCheckAll();
}

function getData() {
    var option = {ID: "#tbDataList", sAjaxSource: "/user/selectPage"};
    option.aoColumns = [
        new columnCheckbox(),
        new columnSequence(),
        new columnCommon("account", "登录账号", {sWidth: "60px"}),
        new columnCommon("name", "姓名", {
            sWidth: "90px"
        }),

        new columnCommon("gender", "性别", {
            mRender: function (data, type, obj) {
                if (data == 0)
                    return "女"
                else
                    return "男";
            },
            sWidth: "50px"
        }),
        new columnCommon("mobilephone", "电话", {sWidth: "120px"}),
        new columnCommon("corpname", "所属单位"),
        //new columnCommon("LastLoginTime", "最近登录时间", {
        //    mRender: function (data, type, obj) {
        //        return FormatJsonDate(data);
        //    },
        //    bVisible: false
        //}),
        //new columnCommon("LastLoginIP", "最近登录IP", {
        //    bVisible: false
        //}),
        // new columnCommon("CupboardNumberName", "分配柜子编号", { sClass: "limit" }),
        // new columnCommon("State", "状态", {
        //     mRender: function (data, type, obj) {
        //         return GetState(data);
        //     },
        //     sWidth: "60px"
        // }),
        // new columnCommon("ISAddCupboard", "分配柜子", {
        //     mRender: function (data, type, obj) {
        //         return GetCupboardState(data);
        //     },
        //     sWidth: "60px"
        // }),
        // new columnCommon("ISFingerprint", "指纹采集", {
        //     mRender: function (data, type, obj) {
        //         return GetFingerPrintState(data);
        //     },
        //     sWidth: "60px"
        // }),
        new columnCommon("id", "操作", {
            mRender: function (data, type, obj) {
                var btn = columnOperationLink([
                    {"Type": "Detail", "Path": "/user/detail/" + data, "Visible": s_detail_h, "Title": "明细"},
                    {"Type": "Edit", "Path": "/user/edit/" + data, "Visible": s_edit_h, "Title": "编辑"}
                ]);
                return btn;
            },
            bSortable: false,
            sWidth: "90px"
        })
    ];
    option.fnServerParams = prepareFilterParam();
    option.sGroups = [{Name: "人员信息", Columns: "account,name,gender,mobilephone,corpname"}];
    tbl = new dataTableInit(option);
}


var iRet;
var strRet;
var strBmp1, strBmp2, strBmp3;
var strTZ, strMB;
var strBestBmp;

//指纹采集
function fingerPrintCollect(ids) {
    strMB = "";
    // var dt = new Date();
    iRet = dtm.FGetTemplate(60000);
    // document.getElementById('spendtime').value = dateDiff(dt, new Date());
    //    alert(iRet);
    if (iRet == 0) {
        strMB = dtm.FGetFingerInfo();
        $.ajax({
            type: "POST",
            url: "/FingerPrint/ZWCollect",
            data: {"UserID": ids, "cpMBBuf": strMB},
            success: function (data) {
                layer.alert(data);
                refresh();
            },
            error: function (jqXHR, textStatus, msg) {
                layer.alert(msg);
            }
        });
    }
    else {
        if (iRet == -100)
            alert("用户取消!");
        else
            alert("采集指纹模板失败!");
    }
}
