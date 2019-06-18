function refresh() {
    treetable.query();
    $("#divdetail").html("<p>点击左侧角色查看明细</p>");

}
function search() {
    treetable.search($("#txtName").val());
}
function show(id){
    $("#divdetail").load("/role/detail/" + id, null, function (response, status, xhr) {
        if (status == "success" || status == "notmodified") {
        } else {
        }
    });
}


function openAuthPage(e, roleid) {
    var el = $(e);
    var title = el.attr("title");
    var url = el.attr("url") + "/" + roleid;
    var page = layer.open({
        type: 2,
        title: '<div class="console-title"><h5>' + title + '</h5></div>',
        shadeClose: true,
        maxmin: false,
        shade: 0.4,
        area: ['900px', '600px'],
        content: url,
        cancel: function (index) {
            var len = $($("#layui-layer" + index).find("iframe")[0].contentWindow.document).find(".alert-success").length;
            if (window.refresh && len > 0) {
                window.refresh.call();
            }
        }
    });

    layer.full(page);
}