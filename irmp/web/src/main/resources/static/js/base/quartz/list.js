/**
 * 定时任务js
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
		url : '../../quartz/job/list?_' + $.now(),
		height : $(window).height() - 56,
		queryParams : function(params) {
			params.name = vm.keyword;
			return params;
		},
		columns : [ {
			checkbox : true
		}, {
			field : "jobId",
			title : "编号",
			width : "50px"
		}, {
			field : "beanName",
			title : "类名",
			width : "200px"
		}, {
			field : "methodName",
			title : "方法名",
			width : "200px"
		}, {
			field : "params",
			title : "参数",
			width : "300px"
		}, {
			field : "cronExpression",
			title : "表达式",
			width : "130px"
		}, {
			field : "status",
			title : "状态",
			width : "60px",
			formatter : function(value, row, index) {
				if (value == '0') {
					return '<span class="label label-danger">禁用</span>';
				} else if (value == '1') {
					return '<span class="label label-success">正常</span>';
				}
			}
		}, {
			field : "gmtCreate",
			title : "创建时间",
			width : "200px"
		}, {
			field : "remark",
			title : "备注"
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
				title : '新增任务',
				url : 'base/quartz/add.html?_' + $.now(),
				width : '450px',
				height : '392px',
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
					title : '编辑任务',
					url : 'base/quartz/edit.html?_' + $.now(),
					width : '450px',
					height : '392px',
					scroll : true,
					success : function(iframeId) {
						top.frames[iframeId].vm.job.jobId = ck[0].jobId;
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
					ids[idx] = item.jobId;
				});
				$.RemoveForm({
					url : '../../quartz/job/remove?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		},
		enable : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedArray(ck)) {
				$.each(ck, function(idx, item) {
					ids[idx] = item.jobId;
				});
				$.ConfirmForm({
					msg : '您是否要启用所选任务吗？',
					url : '../../quartz/job/enable?_' + $.now(),
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
					ids[idx] = item.jobId;
				});
				$.ConfirmForm({
					msg : '您是否要暂停所选任务吗？',
					url : '../../quartz/job/disable?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		},
		run : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedArray(ck)) {
				$.each(ck, function(idx, item) {
					ids[idx] = item.jobId;
				});
				$.ConfirmForm({
					msg : '您是否要立即运行所选任务吗？',
					url : '../../quartz/job/run?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		}
	}
})