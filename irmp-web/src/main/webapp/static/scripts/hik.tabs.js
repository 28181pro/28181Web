var contabs = [];
(function ($) {
    contabs.getWidth = function (e) {
        var width = 0;
        $(e).each(function () {
            width += $(this).outerWidth(true)
        });
        return width;
    }

    contabs.getHeight = function () {
        return $('#iframe_index').height();
    }

    contabs.setTabWidth = function (e) {
        var a = contabs.getWidth($(e).prevAll());
        var i = contabs.getWidth($(e).nextAll());
        var n = contabs.getWidth($(".content-tabs").children().not(".J_menuTabs"));
        var s = $(".content-tabs").outerWidth(!0) - n;
        var r = 0;

        if ($(".page-tabs-content").outerWidth() < s)
            r = 0;
        else if (i <= s - $(e).outerWidth(!0) - $(e).next().outerWidth(!0)) {
            if (s - $(e).next().outerWidth(!0) > i) {
                r = a;
                for (var o = e; r - $(o).outerWidth() > $(".page-tabs-content").outerWidth() - s;)
                    r -= $(o).prev().outerWidth(), o = $(o).prev()
            }
        } else {
            a > s - $(e).outerWidth(!0) - $(e).prev().outerWidth(!0) && (r = a - $(e).prev().outerWidth(!0));
        }
        $(".page-tabs-content").animate({ marginLeft: 0 - r + "px" }, "fast");
    }

    contabs.showLeftTab = function () {
        var e = Math.abs(parseInt($(".page-tabs-content").css("margin-left"))), a = contabs.getWidth($(".content-tabs").children().not(".J_menuTabs")), i = $(".content-tabs").outerWidth(!0) - a, n = 0; if ($(".page-tabs-content").width() < i) return !1; for (var s = $(".J_menuTab:first"), r = 0; r + $(s).outerWidth(!0) <= e;) r += $(s).outerWidth(!0), s = $(s).next(); if (r = 0, contabs.getWidth($(s).prevAll()) > i) { for (; r + $(s).outerWidth(!0) < i && s.length > 0;) r += $(s).outerWidth(!0), s = $(s).prev(); n = contabs.getWidth($(s).prevAll()) } $(".page-tabs-content").animate({ marginLeft: 0 - n + "px" }, "fast")
    }

    contabs.showRightTab = function () {
        var e = Math.abs(parseInt($(".page-tabs-content").css("margin-left"))), a = contabs.getWidth($(".content-tabs").children().not(".J_menuTabs")), i = $(".content-tabs").outerWidth(!0) - a, n = 0; if ($(".page-tabs-content").width() < i) return !1; for (var s = $(".J_menuTab:first"), r = 0; r + $(s).outerWidth(!0) <= e;) r += $(s).outerWidth(!0), s = $(s).next(); for (r = 0; r + $(s).outerWidth(!0) < i && s.length > 0;) r += $(s).outerWidth(!0), s = $(s).next(); n = contabs.getWidth($(s).prevAll()), n > 0 && $(".page-tabs-content").animate({ marginLeft: 0 - n + "px" }, "fast")
    }

    contabs.addTab = function () {
        var url = $(this).attr("href");
        var index = $(this).data("index");
        var menuName = $.trim($(this).text());
        var bHave = false;

        if (url == undefined)
            return false;

        $(".J_menuTab").each(function () {
            if ($(this).data("id") == url) {
                if (!$(this).hasClass("active")) {
                    $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                    contabs.setTabWidth(this);
                    $(".J_mainContent .J_iframe").each(function () {
                        if ($(this).data("id") == url) {
                            $(this).show().siblings(".J_iframe").hide()
                        }
                    });
                }
                bHave = true;
            }
        });
        if (!bHave) {
            var tab = '<a href="javascript:;" class="active J_menuTab" data-id="' + url + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $(".J_menuTab").removeClass("active");
            var frm = '<iframe class="J_iframe" name="iframe' + index + '" src="' + url + '" width="100%" height="' + contabs.getHeight() + 'px" frameborder="0" data-id="' + url + '" seamless></iframe>';
            $(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(frm);
            var layer_index = layer.load();
            $(".J_mainContent iframe:visible").load(function () {
                layer.close(layer_index)
            }),
            $(".J_menuTabs .page-tabs-content").append(tab);
            contabs.setTabWidth($(".J_menuTab.active"));
        }

        $(".nav .open").removeClass("open");


        return false;
    }

    contabs.addTabNotMenu = function (url, index, menuName, refresh) {
        var bHave = false;

        if (url == undefined)
            return false;

        $(".J_menuTab").each(function () {
            if ($(this).data("id") == url) {
                if (!$(this).hasClass("active")) {
                    $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                    contabs.setTabWidth(this);
                    $(".J_mainContent .J_iframe").each(function () {
                        if ($(this).data("id") == url) {
                            $(this).show().siblings(".J_iframe").hide()
                        }
                    });
                }
                bHave = true;
                
                if (refresh) {
                    var index = layer.load();
                    var el = $('.J_iframe[data-id="' + $(this).data("id") + '"]');

                    el.attr("src", url).load(function () {
                        layer.close(index)
                    });
                }
            }
        });
        if (!bHave) {
            var tab = '<a href="javascript:;" class="active J_menuTab" data-id="' + url + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $(".J_menuTab").removeClass("active");
            var frm = '<iframe class="J_iframe" name="iframe' + index + '" src="' + url + '" width="100%" height="' + contabs.getHeight() + 'px" frameborder="0" data-id="' + url + '" seamless></iframe>';
            $(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(frm);
            var layer_index = layer.load();
            $(".J_mainContent iframe:visible").load(function () {
                layer.close(layer_index);
            }),
            $(".J_menuTabs .page-tabs-content").append(tab);
            contabs.setTabWidth($(".J_menuTab.active"));
        }
        return false;
    }

    contabs.removeTab = function () {
        var el = $(this).parents(".J_menuTab");
        var id = el.data("id");
        if (el.hasClass("active")) {
            if (el.next(".J_menuTab").size()) {
                var i = el.next(".J_menuTab:eq(0)").data("id");
                el.next(".J_menuTab:eq(0)").addClass("active");
                $(".J_mainContent .J_iframe").each(function () {
                    if ($(this).data("id") == i) {
                        $(this).show().siblings(".J_iframe").hide();
                    }
                });
            }
            else if (el.prev(".J_menuTab").size()) {
                var i = el.prev(".J_menuTab:last").data("id");
                el.prev(".J_menuTab:last").addClass("active"),
                $(".J_mainContent .J_iframe").each(function () {
                    if ($(this).data("id") == i) {
                        $(this).show().siblings(".J_iframe").hide();
                    }
                })
            }
            el.remove();
        } else {
            el.remove();
            $(".J_mainContent .J_iframe").each(function () {
                if ($(this).data("id") == id) {
                    $(this).remove();
                };
            });
            contabs.setTabWidth($(".J_menuTab.active"));
        }
        return false;
    }

    contabs.selectTab = function () {
        if (!$(this).hasClass("active")) {
            var id = $(this).data("id");
            $(".J_mainContent .J_iframe").each(function () {
                if ($(this).data("id") == id) {
                    $(this).show().siblings(".J_iframe").hide();
                }
            });
            $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
            contabs.setTabWidth(this);
        }
    }

    contabs.refreshTab = function () {
        var el = $('.J_iframe[data-id="' + $(this).data("id") + '"]');
        var url = el.attr("src");
        var index = layer.load();
        el.attr("src", url).load(function () {
            layer.close(index)
        })
    }
})(window.jQuery)

$(function () {
    // initMenu();
});

function initMenu() {
    $(".J_menuItem").each(function (index) {
        $(this).attr("data-index") || $(this).attr("data-index", index)
    });
    $(".J_menuItem").on("click", contabs.addTab);
    $(".J_menuTabs").on("click", ".J_menuTab i", contabs.removeTab);
    $(".J_menuTabs").on("click", ".J_menuTab", contabs.selectTab);
    $(".J_menuTabs").on("dblclick", ".J_menuTab", contabs.refreshTab);
    $(".J_tabLeft").on("click", contabs.showLeftTab);
    $(".J_tabRight").on("click", contabs.showRightTab);
}