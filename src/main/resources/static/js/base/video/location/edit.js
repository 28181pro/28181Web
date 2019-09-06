/**
 * 编辑-菜单管理js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		location:{
			parentName:null,
			parentId:0,
			type:1,
			orderNum:0,
			icon: 'fa fa-circle-o'
		}
	},
	methods : {
		selectIcon: function() {
			dialogOpen({
				id: 'iconSelect',
				title: '选取图标',
				url: 'video/location/icon.html?_' + $.now(),
		        scroll : true,
		        width: "1030px",
		        height: "600px",
		        btn: false
		    })
		},
		menuTree: function(){
		    dialogOpen({
				id: 'layerMenuTree',
				title: '选择上级区域',
				url: 'video/location/tree.html?_' + $.now(),
		        scroll : true,
		        width: "300px",
		        height: "450px",
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
		},
		setForm: function() {
			$.SetForm({
				url: '../../video/location/info?_' + $.now(),
		    	param: vm.location.locationid,
		    	success: function(data) {
		    		vm.location = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../video/location/update?_' + $.now(),
		    	param: vm.location,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
