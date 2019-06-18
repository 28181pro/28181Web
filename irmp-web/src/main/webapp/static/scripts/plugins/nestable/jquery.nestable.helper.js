var treetable = [];
(function ($) {
    treetable.id;
    treetable.queryurl;
    treetable.reseturl;
    treetable.data;

    treetable.init = function () {
        var el = $(".treetable");
        treetable.id = el.attr("id");
        treetable.queryurl = el.attr("queryurl");
        treetable.reseturl = el.attr("reseturl");

        treetable.query();
    }

    treetable.query = function () {
        $.ajax({
            type: "POST",
            url: treetable.queryurl,
            success: function (data) {
                treetable.data = data.data;
                treetable.search("");
            },
            error: function (jqXHR, textStatus, msg) {

            }
        });
    }

    treetable.search = function (text) {
        $("#" + treetable.id).empty();

        var datas = treetable.data;
        if (text != "") {
            var searchdatas = $.grep(datas, function (value) {
                return value.name.indexOf(text) > -1;
            });

            datas = $.grep(datas, function (value) {
                var bSelect = false;
                $(searchdatas).each(function (index, item) {
                    if (item.nodepath) {
                        if (item.id == value.id || item.nodepath.indexOf("/" + value.id + "/") > -1) {
                            bSelect = true;
                            return false;
                        }
                    } else {
                        if (item.id == value.id ) {
                            bSelect = true;
                            return false;
                        }
                    }
                    
                })
                return bSelect;
            });
        }

        var roots = $.grep(datas, function (value) {
            return value.pid == -1;
        });

        var ol = $('<ol class="dd-list"></ol>');
        $("#" + treetable.id).append(ol[0]);
        treetable.add(datas, roots, ol);

        $("#" + treetable.id).nestable("init").on('change', function (e) {
            var list = e.length ? e : $(e.target);
            var json = list.nestable('serialize');
            $.ajax({
                type: "POST",
                url: treetable.reseturl,
                data: {nestables:  JSON.stringify(json)},
                success: function (aData) {

                },
                error: function (jqXHR, textStatus, msg) {
                }
            });
        });
     
       
        $('.dd').nestable('expandFirst');

        layer.reset();
        layer.init();
    }

    treetable.add = function (datas, roots, elem) {
        for (var i = 0; i < roots.length; i++) {
            var item = roots[i];
            var template = $('#template-dd').html();
            Mustache.parse(template);
            var rendered = Mustache.render(template, item);
            elem.append($(rendered));

            var childs = $.grep(datas, function (value) {
                return value.pid == item.id;
            });

            if (childs.length > 0) {
                var ol = $('<ol class="dd-list"></ol>');
                $('li[data-id="' + item.id + '"]').append(ol[0]);
                treetable.add(datas, childs, ol);
            }
        }
    }

})(window.jQuery)

$(function () {
    treetable.init();
})