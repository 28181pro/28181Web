/**
 * 用户管理js
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
			params.username = vm.keyword;
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
			width : "200px"
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
		},{
			field : "manufacturer",
			title : "厂商"
		} ]
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
				title : '新增用户',
				url : 'base/user/add.html?_' + $.now(),
                width : '620px',
                height : '524px',
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
					title : '编辑用户',
					url : 'base/user/edit.html?_' + $.now(),
                    width : '620px',
                    height : '524px',
					scroll : true,
					success : function(iframeId) {
						top.frames[iframeId].vm.user.userId = ck[0].userId;
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
					ids[idx] = item.userId;
				});
				$.RemoveForm({
					url : '../../sys/user/remove?_' + $.now(),
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
					ids[idx] = item.userId;
				});
				$.ConfirmForm({
					msg : '您是否要禁用所选账户吗？',
					url : '../../sys/user/disable?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		},
		play : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedRow(ck)) {
				dialogOpen({
					title : '通道播放',
					url : '../../video/channel/play.html?_' + $.now(),
					width : '620px',
					height : '400px',
					scroll : false,
					success : function(iframeId) {
						//top.frames[iframeId].vm.user.userId = ck[0].userId;
						//top.frames[iframeId].vm.setForm();
					},
					yes : function(iframeId) {
						//top.frames[iframeId].vm.acceptClick();
					},
				});
			}
		},
		reset : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if (checkedRow(ck)) {
				dialogOpen({
					title : '重置密码',
					url : 'base/user/reset.html?_' + $.now(),
					width : '400px',
					height : '220px',
					success : function(iframeId) {
						top.frames[iframeId].vm.user.userId = ck[0].userId;
					},
					yes : function(iframeId) {
						top.frames[iframeId].vm.acceptClick();
					},
				});
			}
		}
	}
})