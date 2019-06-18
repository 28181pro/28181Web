(function ($, layer) {
    //增删改查等功能操作
    layer.operate = [];
    layer.operate.open = function (e, isfull) {
        var el = $(e);
        var title = el.attr("title");
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            var arrIds = ids.split(',');
            if (arrIds.length > 1) {
                layer.alert('请仅选择一项进行操作!');
                return;
            }

            url = url + "/" + arrIds[0];
        }

        var width = $("body").width() * 0.9;
        var height = $("body").height() * 0.9;
        width = width > 900 ? 900 : width;
        height = height > 600 ? 600 : height;
        //alert($("body").height());
        var page = layer.open({
            type: 2,
            title: '<div class="console-title"><h5>' + title + '</h5></div>',
            shadeClose: true,
            maxmin: false,
            shade: 0.4,
            area: [width + 'px', height + 'px'],
            content: url,
            cancel: function (index) {
                var len = $($("#layui-layer" + index).find("iframe")[0].contentWindow.document).find(".alert-success").length;
                if (window.refresh && len > 0) {
                    window.refresh.call();
                }
            }
        });

        if (isfull != false) {
            layer.full(page);
        }
    }
    layer.operate.deleteop = function (e) {
        var el = $(e);
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            url = url + "?ids=" + ids;
        }

        layer.confirm('确定要删除吗？', {
            btn: ['删除', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
                    layer.alert(msg.data);
                    if (window.refresh) {
                        window.refresh.call();
                    }
                },
                error: function (jqXHR, textStatus, msg) {
                    layer.alert(msg);
                }
            });
        }, function () {
        });
    }
    layer.operate.optshow = function (e) {
        var el = $(e);
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            url = url + "?ids=" + ids;
        }

        var showname = el.attr("showname");
        layer.confirm('确定要' + showname + '吗？', {
            btn: [showname, '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
                    layer.alert(msg);
                    if (window.refresh) {
                        window.refresh.call();
                    }
                },
                error: function (jqXHR, textStatus, msg) {
                    layer.alert(msg);
                }
            });
        }, function () {
        });
    }
    layer.operate.callback = function (name) {
        if (parent.window.refresh && $(".alert-success").length > 0) {
            parent.window.refresh.call();
        }

        var index = parent.layer.getFrameIndex(name);
        parent.layer.close(index);
    }
    layer.operate.cancel = function (e) {
        var el = $(e);
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            url = url + "?ids=" + ids;
        }
        var text = el.attr("text");

        layer.confirm('确定要' + text + '吗？', {
            btn: [text, '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
                    layer.alert(msg);
                    if (window.refresh) {
                        window.refresh.call();
                    }
                },
                error: function (jqXHR, textStatus, msg) {
                    layer.alert(msg);
                }
            });
        }, function () {
        });
    }
    layer.operate.reset = function (e) {
        var el = $(e);
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            var arrIds = ids.split(',');
            if (arrIds.length > 1) {
                layer.alert('请仅选择一项进行操作!');
                return;
            }

            url = url + "?id=" + ids;
        }

        var showname = el.attr("showname");
        layer.confirm('确定要' + showname + '吗？', {
            btn: [showname, '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
                    layer.alert(msg);
                    if (window.refresh) {
                        window.refresh.call();
                    }
                },
                error: function (jqXHR, textStatus, msg) {
                    layer.alert(msg);
                }
            });
        }, function () {
        });
    }
    //柜子单元格选择
    layer.operate.Cupboard = function (e, isfull) {
        var el = $(e);
        var title = el.attr("title");
        var url = el.attr("url");
        var type = el.attr("opt-type");
        var isDevice = 0;
        var deviceCorpID = "";
        var deviceCorpName = "";
        if (url.indexOf("corpID=&") >= 0 || url.indexOf("corpID=") < 0) {
            layer.alert('请先选择单位后再添加柜子编号!');
            return;
        }
        else {
            var count = url.indexOf("corpID");
            var count1 = url.indexOf("&userID");
            var count2 = url.indexOf("&isDevice");
            deviceCorpID = url.substring(url.indexOf("corpID") + 7, count1);
            deviceCorpName = url.substr(url.indexOf("corpName") + 9);
            isDevice = url.substr(url.indexOf("&isDevice") + 10, 1);
        }
        //判断单位是否添加设备
        if (isDevice != 1) {
            layer.alert('【' + deviceCorpName + '】单位卷宗柜设备未添加，无法分配柜子！');
            return;
        }

        var width = $("body").width() * 0.9;
        var height = $("body").height() * 0.9;
        width = width > 800 ? 600 : width;
        height = height > 600 ? 550 : height;
        //alert($("body").height());
        var page = layer.open({
            type: 2,
            title: '<div class="console-title"><h5>' + title + '</h5></div>',
            shadeClose: true,
            maxmin: false,
            shade: 0.4,
            area: [800 + 'px', 480 + 'px'],
            content: url,
            cancel: function (index) {
                var len = $($("#layui-layer" + index).find("iframe")[0].contentWindow.document).find(".alert-success").length;
                if (window.refresh && len > 0) {
                    window.refresh.call();
                }
            }
        });

        if (isfull != false) {
            layer.full(page);
        }



    }

    layer.operate.Archives = function (e, isfull) {
        var el = $(e);
        var title = el.attr("title");
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

           // var arrIds = ids.split(',');
            //if (arrIds.length > 1) {
            //    layer.alert('请仅选择一项进行操作!');
            //    return;
            //}
            url = url + "/" + ids;
        }

        var width = $("body").width() * 0.9;
        var height = $("body").height() * 0.9;
        width = width > 900 ? 900 : width;
        height = height > 600 ? 600 : height;
        //alert($("body").height());
        var page = layer.open({
            type: 2,
            title: '<div class="console-title"><h5>' + title + '</h5></div>',
            shadeClose: true,
            maxmin: false,
            shade: 0.4,
            area: [width + 'px', height + 'px'],
            content: url,
            cancel: function (index) {
                var len = $($("#layui-layer" + index).find("iframe")[0].contentWindow.document).find(".alert-success").length;
                if (window.refresh && len > 0) {
                    window.refresh.call();
                }
            }
        });

        if (isfull != false) {
            layer.full(page);
        }
    }

    //地图定位选择
    layer.location = [];
    layer.location.open = function (e) {
        var width = $("body").width() * 0.9;
        var height = $("body").height() * 0.9;
        width = width > 800 ? 800 : width;
        height = height > 600 ? 600 : height;
        var longitude = $('.input-longitude').val();
        var latitude = $('.input-latitude').val();
        var url = "/GIS/Location?longitude=" + longitude + "&latitude=" + latitude;
        layer.open({
            type: 2,
            title: "坐标定位",
            shadeClose: true,
            maxmin: true,
            shade: 0.4,
            area: [800 + 'px', 480 + 'px'],
            content: url
        });
    }
    layer.location.callback = function (name) {
        var longitude = $("#hdnLongitude").val();
        var latitude = $("#hdnLatitude").val();
        var gpslongitude = $('#hdnGPSlon').val();
        var gpslatitude = $('#hdnGPSlat').val();
        parent.window.$('.input-longitude').val(longitude);
        parent.window.$('.input-latitude').val(latitude);
        parent.window.$('.input-gpslongitude').val(gpslongitude.substring(0, 10));
        parent.window.$('.input-gpslatitude').val(gpslatitude.substring(0, 10));
        var index = parent.layer.getFrameIndex(name);
        parent.layer.close(index);
    }

    //弹出框选择
    layer.choose = [];
    layer.choose.open = function (e) {
        layer.operate.open(e, false);
    }
    layer.choose.close = function (name) {
        var index = parent.layer.getFrameIndex(name);
        parent.layer.close(index);
    }
    layer.choose.check = function (e, name) {
        var el = $(e);
        var fun = el.attr("fun");
        fun = fun == null ? "chooseCallBack" : fun;
        var data = el.attr("data");

        eval("parent.window." + fun + ".call(this, JSON.parse(data));");

        var index = parent.layer.getFrameIndex(name);
        parent.layer.close(index);
    }

    //卷宗上交提醒
    layer.operate.Turnremind = function (e) {
        var el = $(e);
        var url = el.attr("url");
        var type = el.attr("opt-type");
        if (type == "table") {
            var ids = getSelectRowIds();
            if (ids == "") {
                layer.alert('请选择操作项!');
                return;
            }

            url = url + "?ids=" + ids;
        }

        layer.confirm('确定要上交提醒吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
                    layer.alert(msg);
                    if (window.refresh) {
                        window.refresh.call();
                    }
                },
                error: function (jqXHR, textStatus, msg) {
                    layer.alert(msg);
                }
            });
        }, function () {
        });
    }

    //物证选择
    layer.evidence = [];
    layer.evidence.open = function (e) {
        var el = $(e);
        var url = el.attr("url");
        if (url.indexOf("caseNumber=&") >= 0 || url.indexOf("caseNumber=") < 0) {
            layer.alert('请先选择案件后再选择物品!');
            return;
        }
        layer.operate.open(e, false);
    }


    layer.init = function () {
        $(".btn-opt").on('click', function (e) {
            layer.operate.open(e.currentTarget);
        });
        $(".btn-del").on('click', function (e) {
            layer.operate.deleteop(e.currentTarget);
        });
        $(".btn-optshow").on('click', function (e) {
            layer.operate.optshow(e.currentTarget);
        });

        $(".btn-optclose").on('click', function (e) {
            layer.operate.callback(window.name);
        });
        $(".btn-location").on('click', function (e) {
            layer.location.open(e.currentTarget);
        });
        $(".btn-locationCallback").on('click', function (e) {
            layer.location.callback(window.name);
        });
        $(".btn-choose").on('click', function (e) {
            layer.choose.open(e.currentTarget);
        });
        $(".btn-chooseClose").on('click', function (e) {
            layer.choose.close(window.name);
        });
        $(".btn-check").on('click', function (e) {
            layer.choose.check(e.currentTarget, window.name);
        });
        $(".btn-reset").on('click', function (e) {
            layer.operate.reset(e.currentTarget);
        });

        $(".btn-sjtx").on('click', function (e) {
            layer.operate.Turnremind(e.currentTarget);
        });

        $(".btn-cupboard").on('click', function (e) {
            layer.operate.Cupboard(e.currentTarget);
        });
        $(".btn-archives").on('click', function (e) {
            layer.operate.Archives(e.currentTarget);
        });
        $(".btn-evidence").on('click', function (e) {
            layer.evidence.open(e.currentTarget);
        });

    }

    layer.reset = function () {
        $(".btn-opt").off('click');
        $(".btn-cancel").off('click');
        $(".btn-del").off('click');
        $(".btn-optshow").off('click');
        $(".btn-optclose").off('click');
        $(".btn-location").off('click');
        $(".btn-locationCallback").off('click');
        $(".btn-choose").off('click');
        $(".btn-chooseClose").off('click');
        $(".btn-check").off('click');
        $(".btn-reset").off('click');
        $(".btn-sjtx").off('click');
        $(".btn-cupboard").off('click');
        $(".btn-archives").off('click');

        
    }

})(window.jQuery, layer)

$(function () {
    layer.init();
})