/**
 * 组织机构js
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
        url: '../../sys/org/list?_' + $.now(),
        height: $(window).height() - 56,
        idField: 'orgId',
        treeShowField: 'name',
        parentIdField: 'parentId',
        columns: [
            {checkbox: true},
            {title: '编号', field: 'orgId', visible: false, width: '80px'},
            {title: '名称', field: 'name'},
            {title: '机构编码', field: 'code', width: '200px'},
            {title: '上级机构', field: 'parentName', width: '300px'},
            {title: '可用', field: 'status', width: '60px', formatter: function(value, row, index){
                    if(row.status === 0){
                        return '<i class="fa fa-toggle-off"></i>';
                    }
                    if(row.status === 1){
                        return '<i class="fa fa-toggle-on"></i>';
                    }
                }},
            {title: '排序', field: 'orderNum', width: '80px'}
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
				title: '新增机构',
				url: 'base/org/add.html?_' + $.now(),
				width: '500px',
				height: '320px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑机构',
					url: 'base/org/edit.html?_' + $.now(),
					width: '500px',
					height: '320px',
					success: function(iframeId){
						top.frames[iframeId].vm.org.orgId = ck[0].orgId;
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
					ids[idx] = item.orgId;
				});
				$.RemoveForm({
					url: '../../sys/org/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})
