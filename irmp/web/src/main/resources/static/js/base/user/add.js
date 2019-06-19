/**
 * 新增-用户管理js
 */
var doublebox = null;

var vm = new Vue({
	el:'#dpLTE',
	data: {
		roleList:{},
		user:{
			orgId: 0,
			orgName: null,
			status: 1,
			roleIdList:[]
		}
	},
	methods : {
        getRoleList: function(){
            $.get('../../sys/role/select?_' + $.now(), function(r){
                vm.roleList = r.rows;
                doublebox = $('.rolebox').doublebox({
                    selectorMinimalHeight: 187,
                    filterPlaceHolder: '关键字...',
                    preserveSelectionOnMove: 'moved',
                    moveOnSelect: false,
                    nonSelectedList: vm.roleList,
                    selectedList:null,
                    optionValue:"roleId",
                    optionText:"roleName",
                    doubleMove:true
                });
            });
        },
		orgTree: function() {
			dialogOpen({
				id: 'layerOrgTree',
				title: '选择机构',
		        url: 'base/user/org.html?_' + $.now(),
		        scroll : true,
		        width: "300px",
		        height: "450px",
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
		},
		acceptClick: function() {
            var roles = doublebox.getSelectedOptions();
            if(isNullOrEmpty(roles)) {
                dialogMsg('请先选择角色！');
                return false;
            }
            if (!$('#form').Validform()) {
                return false;
            }
            vm.user.roleIdList = [];
            $.each(roles.split(','), function(idx, item){
                vm.user.roleIdList.push(parseInt(item));
            });
		    $.SaveForm({
		    	url: '../../sys/user/save?_' + $.now(),
		    	param: vm.user,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	},
	created : function() {
		this.getRoleList();
	}
})
