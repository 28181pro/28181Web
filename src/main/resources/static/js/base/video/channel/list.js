/**
 * 通道管理js
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
        url : '../../video/channel/list?_' + $.now(),
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
            field : "channelCode",
            title : "通道编号",
            width : "200px"
        }, {
            field : "gbdeviceCode",
            title : "设备编号",
            width : "200px"
        }, {
            field : "name",
            title : "名称",
            width : "300px"
        }, {
            field : "ip",
            title : "出口IP",
            width : "130px"
        }, {
            field : "port",
            title : "端口",
            width : "100px"
        },  {
            field : "onlineStatus",
            title : "状态",
            width : "60px",
            formatter : function(value, row, index) {
                if (value == '0') {
                    return '<span class="label label-danger">离线</span>';
                } else if (value == '1') {
                    return '<span class="label label-success">正常</span>';
                }
            }
        }, {
            field : "registerServerName",
            title : "注册服务器",
            width : "150px"
        }, {
            field : "streamServerName",
            title : "流媒体服务器",
            width : "150px"
        },{
            field : "manufacturer",
            title : "厂商",
            width : "0px"
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
				title : '新增通道',
				url : 'video/channel/add.html?_' + $.now(),
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
					title : '编辑通道',
					url : 'video/channel/edit.html?_' + $.now(),
                    width: '500px',
                    height: '500px',
					scroll : true,
					success : function(iframeId) {
						top.frames[iframeId].vm.channel.id = ck[0].id;
						top.frames[iframeId].vm.setForm();
					},
					yes : function(iframeId) {
						top.frames[iframeId].vm.acceptClick();
					},
				});
			}
		},
        play : function() {
            var ck = $('#dataGrid').bootstrapTable('getSelections');
            if (checkedRow(ck)) {
                dialogOpen({
                    title : '播放',
                    //url : 'video/channel/play.html?_' + $.now(),
					url : 'video/channel/video.html?_' + $.now(),
                    width : '500px',
                    height : '420px',
                    scroll : true,
                    success : function(iframeId) {
                        top.frames[iframeId].vm.channel.id = ck[0].id;
                        top.frames[iframeId].vm.setForm();
                    },
                    yes : function(iframeId) {
                        top.frames[iframeId].vm.acceptClick();
                    },
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
					url : '../../video/channel/remove?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		}
		,
		enable : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedArray(ck)) {
				$.each(ck, function(idx, item) {
					ids[idx] = item.id;
				});
				$.ConfirmForm({
					msg : '您是否要启用所选通道吗？',
					url : '../../video/channel/enable?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		},
		disable : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedArray(ck)) {
				$.each(ck, function(idx, item) {
					ids[idx] = item.id;
				});
				$.ConfirmForm({
					msg : '您是否要禁用所选通道吗？',
					url : '../../video/channel/disable?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		}
	}
})