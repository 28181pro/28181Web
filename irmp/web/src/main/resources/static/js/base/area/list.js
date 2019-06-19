/**
 * 行政区域js
 */

$(function() {
	initialPage();
	getGrid();
});

function initialPage() {
	$("#treePanel").css('height', $(window).height()-54);
	$(window).resize(function() {
		$("#treePanel").css('height', $(window).height()-54);
		$('#dataGrid').bootstrapTable('resetView', {
			height : $(window).height() - 108
		});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url : '../../sys/area/list?_' + $.now(),
		height : $(window).height() - 108,
		queryParams : function(params) {
			params.name = vm.keyword;
			params.parentCode = vm.parentCode;
			return params;
		},
		pagination : false,
		columns : [ {
			checkbox : true
		}, {
			field : "areaId",
			title : "编号",
			width : "50px"
		}, {
			field : "areaCode",
			title : "区域代码",
			width : "100px"
		}, {
			field : "name",
			title : "区域名称",
			width : "200px"
		}, {
			field : "layer",
			title : "层级",
			width : "60px",
			formatter : function(value, row, index) {
				if (value == 1) {
					return '<span class="label label-primary">省级</span>';
				}
				if (value == 2) {
					return '<span class="label label-success">地市</span>';
				}
				if (value == 3) {
					return '<span class="label label-warning">区县</span>';
				}
			}
		}, {
			field : "orderNum",
			title : "排序",
			width : "60px",
			align : "center",
		}, {
			field : "status",
			title : "可用",
			width : "60px",
			align : "center",
			formatter : function(value, row, index) {
				if (value == 0) {
					return '<i class="fa fa-toggle-off"></i>';
				}
				if (value == 1) {
					return '<i class="fa fa-toggle-on"></i>';
				}
			}
		}, {
			field : "remark",
			title : "备注"
		} ]
	})
}

var setting = {
	async : {
		enable: true,
		type: "get",
		url: "../../sys/area/select",
		autoParam: ["areaCode"]
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "areaCode",
			pIdKey : "parentCode",
			rootPId : "0"
		},
		key : {
			url : "nourl"
		}
	},
	callback : {
		onClick : function(event, treeId, treeNode) {
			vm.parentCode = treeNode.areaCode;
			vm.parentName = treeNode.name;
			vm.load();
		}
	}
};
var ztree;

var vm = new Vue({
	el : '#dpLTE',
	data : {
		keyword : null,
		parentCode : '0',
		parentName : null
	},
	methods : {
		load : function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		getArea : function(parentCode) {
			$.get('../../sys/area/select', {areaCode: parentCode},function(r) {
				ztree = $.fn.zTree.init($("#areaTree"), setting, r);
			})
		},
		save : function() {
			dialogOpen({
				title : '新增区域',
				url : 'base/area/add.html?_' + $.now(),
				width : '500px',
				height : '450px',
				success : function(iframeId) {
					top.frames[iframeId].vm.area.parentCode = vm.parentCode;
					top.frames[iframeId].vm.area.parentName = vm.parentName;
				},
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if (checkedRow(ck)) {
				dialogOpen({
					title : '编辑区域',
					url : 'base/area/edit.html?_' + $.now(),
					width : '500px',
					height : '450px',
					success : function(iframeId) {
						top.frames[iframeId].vm.area.areaId = ck[0].areaId;
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
					ids[idx] = item.areaId;
				});
				$.RemoveForm({
					url : '../../sys/area/remove?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		}
	},
	created : function() {
		this.getArea(this.parentCode);
	}
})