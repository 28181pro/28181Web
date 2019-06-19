/**
 * 通用字典js
 */

$(function () {
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
    $('#dataGrid').bootstrapTreeTableEx({
        url: '../../sys/macro/list?_' + $.now(),
        height: $(window).height() - 56,
        idField: 'macroId',
        treeShowField: 'name',
        parentIdField: 'typeId',
        columns: [
            {checkbox: true},
            {title: '编号', field: 'macroId', visible: false, width: '50px'},
            {title: '参数名', field: 'name', width: '180px'},
            {title: '参数值', field: 'value', width: '180px'},
            {title: '所属类别', field: 'typeName', width: '100px'},
            {
                title: '类型',
                field: 'type',
                width: '60px',
                formatter: function (value, row, index) {
                    if (row.type === 0) {
                        return '<span class="label label-primary">目录</span>';
                    }
                    if (row.type === 1) {
                        return '<span class="label label-warning">参数</span>';
                    }
                }
            },
            {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', width: '50px'},
            {
                title: '可用',
                field: 'status',
                width: '60px',
                formatter: function (value, row, index) {
                    if (row.status === 0) {
                        return '<i class="fa fa-toggle-off"></i>';
                    }
                    if (row.status === 1) {
                        return '<i class="fa fa-toggle-on"></i>';
                    }
                }
            },
            {title: '备注', field: 'remark'}
        ]
    });
}

var vm = new Vue({
	el:'#dpLTE',
	methods : {
		load: function() {
            $('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增字典',
				url: 'base/macro/add.html?_' + $.now(),
				width: '600px',
				height: '420px',
				scroll : true,
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑字典',
					url: 'base/macro/edit.html?_' + $.now(),
					width: '600px',
					height: '420px',
					scroll : true,
					success: function(iframeId){
						top.frames[iframeId].vm.macro.macroId = ck[0].macroId;
						top.frames[iframeId].vm.setForm();
					},
					yes : function(iframeId) {
						top.frames[iframeId].vm.acceptClick();
					},
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.macroId;
				});
				$.RemoveForm({
					url: '../../sys/macro/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})