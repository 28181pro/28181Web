var getSelectControl = function (callback) {
    callback = callback || $.noop;
    // select2加载数据
    $.ajax({
        dataType: 'json',
        type: 'POST',
        url: '/public/querySelectDataList',
        data: {},
        error: function (xhr, error, thrown) {
        },
        success: function (chartsData) {
            $("#selCaseState").html("");
            $("#selCaseState").select2({
                language: "zh-CN",
                data: chartsData.data.aData
            });
            //单位信息
            $("#selCorpName").html("");
            $("#selCorpName").select2({
                language: "zh-CN",
                data: chartsData.data.corpList
            });
            //案件类别
            $("#selCaseType").html("");
            $("#selCaseType").select2({
                language: "zh-CN",
                data: chartsData.data.casetypeData
            });

            callback();
            // refresh();
        }
    });
};

