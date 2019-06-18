function refresh() {
    treetable.query();
    $("#divdetail").html("<p>点击左侧菜单查看明细</p>");
}

function search() {
    treetable.search($("#txtName").val());
}

function show(id) {
    $("#divdetail").load("/prog/detail/" + id, null, function (response, status, xhr) {
        if (status == "success" || status == "notmodified") {
        } else {
        }
    });
}

function authorize(roleid, rolename, progID, op) {
    layer.confirm('是否对角色 【' + rolename + '】 ' + (op == 0 ? '取消' : '') + '授权该菜单 ？', {
        btn: ['确认', '取消']
    }, function (index) {
        var data = {progid: progID, roleids: roleid, state: op};
        $.ajax({
            type: "POST",
            url: "/Role/AuthorizeProg",
            data: data,
            success: function (data) {
                layer.alert(data);
                if (typeof show == 'function') {
                    show(progID);
                }

                layer.close(index);
            },
            error: function (msg) {
                layer.alert('数据发送失败');
            }
        });
    }, function () {
    });

}