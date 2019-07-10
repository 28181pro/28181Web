
var doublebox = null;

var vm = new Vue({
    el:'#dpLTE',
    data: {
        roleList:{},
        selectedList:[],
        nonselectedList:[],
        channel:{
            channelCode: 0,
            strlanip: null,
            strlanport: 1,
            strwanip:null,
            strwanport:1,
            reglanip: null,
            reglanport: 1,
            regwanip:null,
            regwanport:1
        }
    },
    methods : {
        setForm: function() {
            $.SetForm({
                url: '../../video/channel/getChannelInfo?_' + $.now(),
                param: vm.channel.channelCode,
                success: function(data) {
                    vm.channel.regwanport = data.regwanport;
                    vm.channel.regwanip = data.regwanip;
                    vm.channel.reglanip = data.reglanip;
                    vm.channel.reglanport = data.reglanport;
                    vm.channel.strwanip = data.strwanip;
                    vm.channel.strwanport = data.strwanport;
                    vm.channel.strlanip = data.strlanip;
                    vm.channel.strlanport = data.strlanport;
                }

            });

        },
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
                title: '选择部门',
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