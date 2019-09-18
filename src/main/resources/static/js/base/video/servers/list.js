/**
 * 服务器管理js
 */

$(function() {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function() {
        $('#dataGrid').bootstrapTable('resetView', {
            height : $(window).height() - 56
        });
    });
}

function getGrid() {
    $('#dataGrid').bootstrapTableEx({
        url : '../../video/servers/listpage?_' + $.now(),
        height : $(window).height() - 56,
        queryParams : function(params) {
            params.name = vm.keyword;
            return params;
        },
        columns : [ {
            checkbox : true
        }, {
            field : "id",
            title : "编号",
            width : "50px"
        }, {
            field : "name",
            title : "名称",
            width : "300px"
        },{
            field : "lanIp",
            title : "内网IP",
            width : "200px"
        }, {
            field : "lanPort",
            title : "内网端口",
            width : "200px"
        }, {
            field : "wanIp",
            title : "外网IP",
            width : "130px"
        }, {
            field : "wanPort",
            title : "外网端口",
            width : "100px"
        }]
    })
}


var vm = new Vue({
    el : '#dpLTE',
    data : {
        keyword : null
    },
    methods : {
        load : function() {
            $('#dataGrid').bootstrapTable('refresh');
        },
        save : function() {
            dialogOpen({
                title : '新增服务器',
                url : 'video/servers/add.html?_' + $.now(),
                width: '500px',
                height: '500px',
                scroll : true,
                yes : function(iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                },
            });
        },
        edit : function() {
            var ck = $('#dataGrid').bootstrapTable('getSelections');
            if (checkedRow(ck)) {
                dialogOpen({
                    title : '编辑服务器',
                    url : 'video/servers/edit.html?_' + $.now(),
                    width: '500px',
                    height: '500px',
                    scroll : true,
                    success : function(iframeId) {
                        top.frames[iframeId].vm.servers.id = ck[0].id;
                        top.frames[iframeId].vm.servers.name = ck[0].name;
                        top.frames[iframeId].vm.servers.lanIp = ck[0].lanIp;
                        top.frames[iframeId].vm.servers.lanPort = ck[0].lanPort;
                        top.frames[iframeId].vm.servers.wanIp = ck[0].wanIp;
                        top.frames[iframeId].vm.servers.wanPort = ck[0].wanPort;
                        top.frames[iframeId].vm.setForm();
                    },
                    yes : function(iframeId) {
                        top.frames[iframeId].vm.acceptClick();
                    }
                });
            }
        },
        remove : function() {
            var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
            if (checkedArray(ck)) {
                $.each(ck, function(idx, item) {
                    ids[idx] = item.id;
                });
                $.RemoveForm({
                    url : '../../video/servers/remove?_' + $.now(),
                    param : ids,
                    success : function(data) {
                        vm.load();
                    }
                });
            }
        }
    }
})