
var doublebox = null;

var vm = new Vue({
    el:'#dpLTE',
    data: {
        roleList:{},
        selectedList:[],
        nonselectedList:[],
        channel:{
            channelCode: 0,
            strmLanIp: null,
            strmLanPort: 1,
            strmWanIp:null,
            strmWanPort:1,
            regLanIp: null,
            regLanPort: 1,
            regWanIp:null,
            regWanPort:1
        }
    },
    methods : {
        setForm: function() {
            $.SetForm({
                url: '../../video/channel/getChannelInfo?_' + $.now(),
                param: vm.channel.channelCode,
                success: function(data) {
                    alert(data.strmWanIp );
                    vm.channel.regWanPort = data.regWanPort;
                    vm.channel.regWanIp = data.regWanIp;
                    vm.channel.regLanIp = data.regLanIp;
                    vm.channel.regLanPort = data.regLanPort;
                    vm.channel.strmWanIp = data.strmWanIp;
                    vm.channel.strmWanPort = data.strmWanPort;
                    vm.channel.strmLanIp = data.strmLanIp;
                    vm.channel.strmLanPort = data.strmLanPort;
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