var progs;

$(document).ready(function () {
    initProg(roleUserIds);
    initRole();
});

function initProg(selected) {
    $.ajax({
        type: "POST",
        url: '/Prog/QueryList',
        success: function (data) {
            progs = data.data;
            loadprogs(progs, "", selected)
        },
        error: function (jqXHR, textStatus, msg) {
        }
    });
}

function loadprogs(datas, progName, selected) {
    $('#nestableprog').empty();

    if (progName != "") {
        var searchdatas = $.grep(datas, function (value) {
            return value.name.indexOf(progName) > -1;
        });

        datas = $.grep(datas, function (value) {
            var bSelect = false;
            $(searchdatas).each(function (index, item) {
                if (item.id == value.id || item.nodepath.indexOf("/" + value.id + "/") > -1) {
                    bSelect = true;
                    return false;
                }
            })
            return bSelect;
        });
    }

    var roots = $.grep(datas, function (value) {
        return value.pid == -1;
    });

    var ol = $('<ol class="dd-list"></ol>');
    $('#nestableprog').append(ol[0]);
    loadprog(datas, roots, ol, selected);

    $('#nestableprog').nestable("init").on('change', function (e) {
    });
    $('.dd').nestable('collapseAll');

    layer.reset();
    layer.init();
}

function loadprog(datas, roots, elem, selected) {
    for (var i = 0; i < roots.length; i++) {
        var item = roots[i];

        if (availableProg.indexOf(',' + item.id + ',') > -1 || roleID == "1") {
            item.checked = selected.indexOf(',' + item.id + ',') > -1 ? "checked='checked'" : "";
            item.iconDisplay = item.nodetype == 0 ? "label-info" : "label-warning";
            var template = $('#template-dd').html();
            Mustache.parse(template);
            var rendered = Mustache.render(template, item);
            elem.append($(rendered));
        }

        var childs = $.grep(datas, function (value) {
            return value.pid == item.id;
        });

        if (childs.length > 0) {
            var ol = $('<ol class="dd-list"></ol>');
            $('li[data-id="' + item.id + '"]').append(ol[0]);
            loadprog(datas, childs, ol, selected);
        }
    }
}

function checkall(obj) {
    $(obj).closest(".dd-item").find("input[type=checkbox]").each(function () {
        this.checked = obj.checked;
    });
}

function initRole() {
    $.ajax({
        type: "POST",
        url: '/role/getRoleList',
        success: function (aData) {
            $(".row-role").empty();
            var roles = aData.data;
            for (var i = 0; i < roles.length; i++) {
                var role = roles[i];
                if (role.id == roleID)
                    continue;
                role.markDisplay = CutString(role.mark, 30);
                role.operate = "copy(" + role.id + ",'" + role.name + "'" + ")";
                role.preview = "preview(this, " + role.id + ",'" + role.name + "'" + ")";
                loadrole(roles[i]);
            }
            
            layer.reset();
            layer.init();
        },
        error: function (jqXHR, textStatus, msg) {
        }
    });
}

function loadrole(role) {
    var row = $('<li class="clearfix"></li>');
    $(".role-prog-auth").append(row);

    var template = $('#template-role').html();
    Mustache.parse(template);
    var rendered = Mustache.render(template, role);
    row.append($(rendered));
}

function copy(roleid, rolename) {
    layer.confirm('是否复制 【' + rolename + '】 ' + ' 至该角色 ？', {
        btn: ['确认', '取消']
    }, function (index) {
        var data = {roleid: roleid};
        $.ajax({
            type: "POST",
            url: "/Role/getProgList",
            data: data,
            success: function (data) {
                var selected = ',' + data.data + ',';
                initProg(selected);
                layer.close(index);
            },
            error: function (msg) {
                layer.alert('数据加载失败');
            }
        });

    }, function () {
    });

}

function preview(obj, roleid, rolename) {
    layer.operate.open(obj, false);
}

function copyChooseCallback(ids) {
    var selected = ',' + ids + ',';
    initProg(selected);
}