var tbl;
$(function () {
    refresh();
});

function refresh() {
    if (tbl != null) {
        tbl.fnDestroy();
    }
    getData();
    regCheckAll();
}

function getData() {
    var option = {ID: "#tbDataList", sAjaxSource: "/area/selectPage"};
    option.aoColumns = [
        new columnSequence(),
        new columnCommon("code", "区域编号", {sWidth: "150px"}),
        new columnCommon("name", "区域名称"),
        new columnCommon("ID", "操作", {
            mRender: function (data, type, obj) {
                return '<button class="btn btn-primary btn-xs btn-check" type="button" fun="chooseAreaCallBack" data=\'' + JSON.stringify(obj) + '\'><i class="fa fa-check"></i>选择</button>';
            },
            sWidth: "80px"
        })
    ];
    option.fnServerParams = prepareFilterParam();
    option.fnDrawCallback = function () {
        tableEvent();
        layer.reset();
        layer.init();
    }
    tbl = new dataTableInit(option);
}