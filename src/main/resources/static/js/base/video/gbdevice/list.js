/**
 * 国际设备管理js
 */

$(function () {
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../video/gbdevice/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.name = vm.keyword;
			return params;
		},
		columns: [{
			checkbox: true
		}, {
			field : "id",
			title : "序号",
			width : "50px"
		}, {
            field : "gbCode",
            title : "设备国际编号",
            width : "100px"
        }, {
			field : "name",
			title : "名称",
			width : "150px"
		}, {
			field : "channelSum",
			title : "通道数",
			width : "50px"
		}, {
			field : "ip",
			title : "出口ip",
			width : "150px"
		}, {
			field : "port",
			title : "端口",
			width : "150px"
		}, {
            field : "usedStatus",
            title : "使用状态",
            width : "50px",
            formatter : function(value, row, index) {
                if (value == '0') {
                    return '<span class="label label-danger">不可用</span>';
                } else if (value == '1') {
                    return '<span class="label label-success">可用</span>';
                }
            }
        }, {
			field : "createTime",
			title : "创建时间",
            width : "200px"
		}, {
            field : "modifyTime",
            title : "更新时间",
            width : "200px"
        }]
	})
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增国际设备',
				url: 'video/gbdevice/add.html?_' + $.now(),
				width: '550px',
				height: '400px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑国际设备',
					url: 'video/gbdevice/edit.html?_' + $.now(),
                    width: '550px',
                    height: '400px',
					success: function(iframeId){
						top.frames[iframeId].vm.gbdevice.id = ck[0].id;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.id;
				});
				$.RemoveForm({
					url: '../../video/gbdevice/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})