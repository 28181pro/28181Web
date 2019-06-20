/**
 * 编辑-用户管理js
 */
var doublebox = null;

var vm = new Vue({
	el:'#dpLTE',
	data: {
		roleList:{},
        selectedList:[],
        nonselectedList:[],
		user:{
			orgId: 0,
			orgName: null,
			status: 1,
			roleIdList:[]
		}
	},
	methods : {
        getRoleList: function(){
            $.ajax({
                type: 'get',
                async: false,
                contentType : 'application/json',
                url: '../../sys/role/select?_' + $.now(),
                data: null,
                success: function(r) {
                    vm.roleList = r.rows;
                },
                dataType: 'json'
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
		setForm: function() {
            this.getRoleList();
			$.SetForm({
				url: '../../sys/user/infoUser?_' + $.now(),
		    	param: vm.user.userId,
		    	success: function(data) {
                    vm.user = data;
                    for(var i=0; i<vm.roleList.length; i++){
                        var exist = true;
                        inner:
                            for(var j=0; j<vm.user.roleIdList.length; j++){
                                if(vm.roleList[i].roleId == vm.user.roleIdList[j]){
                                    vm.selectedList.push(vm.roleList[i]);
                                    exist = true;
                                    break inner;
                                }else{
                                    exist = false;
                                }
                            }
                        if (!exist){
                            vm.nonselectedList.push(vm.roleList[i]);
                        }
                    }
                    doublebox = $('.rolebox').doublebox({
                        selectorMinimalHeight: 187,
                        filterPlaceHolder: '关键字...',
                        preserveSelectionOnMove: 'moved',
                        moveOnSelect: false,
                        nonSelectedList: vm.nonselectedList,
                        selectedList:vm.selectedList,
                        optionValue:"roleId",
                        optionText:"roleName",
                        doubleMove:true
                    });
		    	}
			});
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
		    $.ConfirmForm({
		    	url: '../../sys/user/update?_' + $.now(),
		    	param: vm.user,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
