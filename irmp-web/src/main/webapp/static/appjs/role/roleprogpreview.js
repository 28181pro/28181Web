var progs;
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: '/Prog/QueryList',
        success: function (data) {
            progs = data.data;
            loadprogs(progs, "")
        },
        error: function (jqXHR, textStatus, msg) {
        }
    });

    $(".btn-copy").on('click', function () {
        var codes = [];
        $("input[type='checkbox']").each(function () {
            if (this.checked) {
                codes.push($(this).val());

            }
        });

        parent.window.copyChooseCallback.call(this, codes);

        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
});

function loadprogs(datas, progName) {
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
    loadprog(datas, roots, ol);

    $('#nestableprog').nestable("init").on('change', function (e) {
    });
    $('.dd').nestable('collapseAll');

    layer.reset();
    layer.init();
}

function loadprog(datas, roots, elem) {
    for (var i = 0; i < roots.length; i++) {
        var item = roots[i];

        if (roleProgByUser.indexOf(',' + item.id + ',') > -1 || roleID == "1") {
            item.checked = roleUserIds.indexOf(',' + item.id + ',') > -1 ? "checked='checked'" : "";
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
            loadprog(datas, childs, ol);
        }
    }
}

function checkall(obj) {
    $(obj).closest(".dd-item").find("input[type=checkbox]").each(function () {
        this.checked = obj.checked;
    });
}