/**
 * 编辑-角色管理js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
        gbdevice: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../video/gbdevice/info?_' + $.now(),
		    	param: vm.gbdevice.id,
		    	success: function(data) {
		    		vm.gbdevice = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../video/gbdevice/update?_' + $.now(),
		    	param: vm.gbdevice,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})