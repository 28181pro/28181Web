/**
 * 新增-国际设备管理js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		gbdevice: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../video/gbdevice/save?_' + $.now(),
		    	param: vm.gbdevice,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
