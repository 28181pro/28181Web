$(function () {
    $(".layer-date").each(function (index, item) {
        var id = $(item).attr("id");
        laydate({ elem: '#' + id, format: 'YYYY-MM-DD' })
    });
    $(".layer-datetime").each(function (index, item) {
        var id = $(item).attr("id");
        laydate({ elem: '#' + id, istime: true, format: 'YYYY-MM-DD hh:mm:ss' })
    });
})