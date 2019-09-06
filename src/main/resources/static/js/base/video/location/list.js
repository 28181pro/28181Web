/**
 * 系统菜单js
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
        url: '../../video/location/list?_' + $.now(),
        height: $(window).height() - 56,

        idField: 'locationId',
        treeShowField: 'name',
        parentIdField: 'parentId',
        columns: [
            {checkbox: true},
            {title: '编号', field: 'locationId', visible: false, width: '50px'},
            {title: '名称', field: 'name', width: '80px'},
            {title: '上级菜单', field: 'parentName', width: '80px'},
            {
                title: '图标',
                field: 'icon',
                width: '50px',
                formatter: function (value, row, index) {
                    return row.icon == null ? '' : '<i class="' + row.icon + ' fa-lg"></i>';
                }
            },
            {
                title: '类型',
                field: 'type',
                width: '60px',
                formatter: function (value, row, index) {
                    if (row.type === 0) {
                        return '<span class="label label-primary">市公司</span>';
                    }
                    if (row.type === 1) {
                        return '<span class="label label-success">区县公司</span>';
                    }
                    if (row.type === 2) {
                        return '<span class="label label-warning">供电所</span>';
                    }
					if (row.type === 3) {
						return '<span class="label label-warning">供电线路</span>';
					}
                }
            },
            {title: '排序', field: 'orderNum', width: '50px'}
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
				title: '新增区域',
				url: '/video/location/add.html?_' + $.now(),
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
					title: '编辑区域',
					url: 'video/location/edit.html?_' + $.now(),
					width: '600px',
					height: '420px',
					scroll : true,
					success: function(iframeId){
						top.frames[iframeId].vm.location.locationid = ck[0].locationId;
						console.log(ck[0].locationid);
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
					ids[idx] = item.locationId;
				});
				$.RemoveForm({
					url: '../../video/location/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})
