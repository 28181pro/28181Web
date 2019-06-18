var bindMenuEvent = function () {
    bindRootMenuEvent();
    bindSubMenuEvent();
    bindSitebarEvent();
};

var bindRootMenuEvent = function () {
    $(".rootNav").click(function () {
        var menuid = $(this).attr('menuid');
        $(".subNavBox").each(function () {
            $(this).hide();
        });
        $("div[menuid='" + menuid + "']").show();

        $(".rootNav").each(function () {
            $(this).parent().removeClass("li-open");
        });
        $(this).parent().addClass("li-open");
    });

    $(".subNavBox").first().show();
};

var bindSubMenuEvent = function () {
    $(".subNav").click(function () {
        var that = this;
        $(".subNav").each(function () {
            if (this != that && $(this).find("span[name='submenu']").attr('class') == "title-icon fa fa-caret-down") {/*显示*/
                $(this).find("span[name='submenu']").removeClass("fa-caret-down");
                $(this).find("span[name='submenu']").addClass("fa-caret-right");
                $(this).removeClass("sublist-down");
                $(this).addClass("sublist-up");
            }

            $(that).parent().siblings().find(".navContent").slideUp(300);
        });

        if ($(that).find("span[name='submenu']").attr('class') == "title-icon fa fa-caret-down") {/*显示*/

            $(that).find("span[name='submenu']").removeClass("fa-caret-down");
            $(that).find("span[name='submenu']").addClass("fa-caret-right");
            $(that).removeClass("sublist-down");
            $(that).addClass("sublist-up");
        }
        else {
            $(that).find("span[name='submenu']").removeClass("fa-caret-right");
            /*隐藏*/
            $(that).find("span[name='submenu']").addClass("fa-caret-down");
            $(that).removeClass("sublist-up");
            $(that).addClass("sublist-down");
        }

        // 修改数字控制速度， slideUp(500)控制卷起速度
        $(that).next(".navContent").slideToggle(300);
    });

    /*左侧鼠标移入提示功能*/
    $(".sBox ul li").mouseenter(function () {
        {
            $(this).find("div").show();
        }
    }).mouseleave(function () {
        $(this).find("div").hide();
    });
};

var bindSitebarEvent = function () {
    $(".left-main .sidebar-fold").click(function () {
        if ($(this).parent().attr('class') == "left-main left-full") {
            $(this).parent().removeClass("left-full");
            $(this).parent().addClass("left-off");

            $(this).parent().parent().find(".right-product").removeClass("right-full");
            $(this).parent().parent().find(".right-product").addClass("right-off");
        } else {
            $(this).parent().removeClass("left-off");
            $(this).parent().addClass("left-full");

            $(this).parent().parent().find(".right-product").removeClass("right-off");
            $(this).parent().parent().find(".right-product").addClass("right-full");
        }
    });
}

var getRootMenus = function (data) {
    var first = true;
    for (var i = 0; i < data.length; i++) {
        var sub = data[i];
        if (sub.pid == null || sub.pid == -1) {
            var li = $("<li class='li-border " + (first ? "li-open" : "") + "'></li>");
            $(".navbar-nav-left").append(li);
            var suba = $("<a menuid='" + sub.id + "'  class='mystyle-color rootNav' href='javascript: void(0)'>" + sub.name + "</span></a>");
            li.append(suba);

            var leftdiv = $("<div class='subNavBox' menuid='" + sub.id + "' style='display:none'></div>");
            leftdiv.append(getMenus(data, sub.id));

            $(".left-subNavBox").append(leftdiv);

            first = false;
        }
    }
}

var getMenus = function (data, pid) {
    var first = true;
    var dataid = 0;
    var div = $("<div class='sBox'></div>");
    for (var i = 0; i < data.length; i++) {
        var sub = data[i];
        if (sub.pid == pid) {
            if (sub.path != null && sub.path != "" && sub.path != "#") {

                dataid++;
                var ul = $("<ul style='display: block;'></ul>");
                div.append(ul);
                var li = $("<li></li>");
                ul.append(li);
                var subdiv = $("<div class='showtitle' style='width: 100px;'><IMG src='/static/themes/leftimg.png'>" + sub.name + "</div>");
                li.append(subdiv);
                var suba = $("<a class='J_menuItem' href='" + sub.path.toLowerCase() + "' data-index=" + dataid + "><span class='sublist-icon fa fa-desktop'></span><span class='sub-title'>" + sub.name + "</span></a>");
                li.append(suba);
            }
            else {
                var tdiv = $("<div class='subNav " + (first ? "sublist-down" : "sublist-up") + "'></div>");
                div.append(tdiv);
                var ul = $("<ul style='display: block;'></ul>");
                tdiv.append(ul);
                var li = $("<li></li>");
                ul.append(li);
                var subdiv = $("<div class='showtitle' style='width: 100px;'><IMG src='/static/themes/leftimg.png'>" + sub.name + "</div>");
                li.append(subdiv);
                var subspan = $("<span name='submenu' class='title-icon fa " + (first ? "fa-caret-down" : "fa-caret-right") + "'></span><span class='sub-title'>" + sub.name + "</span>");
                li.append(subspan);
            }

            //获取子节点
            var childArry = getParentArry(sub.id, data);

            if (childArry.length > 0) {

                var ul = $("<ul class='navContent' style='display: " + (first ? "block" : "none") + ";'></ul>");
                div.append(ul);
                for (var y in childArry) {
                    var third = childArry[y];
                    dataid++;
                    var li = $("<li></li>");
                    ul.append(li);
                    var subdiv = $("<div class='showtitle' style='width: 100px;'><IMG src='/static/themes/leftimg.png'>" + third.name + "</div>");
                    li.append(subdiv);
                    var suba = $("<a class='J_menuItem' href='" + third.path.toLowerCase() + "' data-index=" + dataid + "><span class='sublist-icon fa fa-desktop'></span><span class='sub-title'>" + third.name + "</span></a>");
                    li.append(suba);
                }
            }
        }

        first = false;
    }
    return div;
}

var getParentArry = function (id, arry) {
    var newArry = new Array();
    for (var x in arry) {
        if (arry[x].pid == id && arry[x].nodetype == 0) {
            newArry.push(arry[x]);
        }
    }
    return newArry;
}