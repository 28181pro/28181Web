/**
 * 编辑-服务器管理js
 */

var vm = new Vue({
    el:'#dpLTE',
    data: {
        servers: {
            id:null,
            name:null,
            lanIp:null,
            lanPort:null,
            wanIp:null,
            wanPort:null
        }
    },
    methods : {
        setForm: function() {
            $.SetForm({
                url: '../../video/servers/info?_' + $.now(),
                param: vm.servers.id,
                success: function(data) {
                    vm.servers = data;
                }
            });
        },
        acceptClick: function() {
            if (!$('#form').Validform()) {
                return false;
            }
            $.ConfirmForm({
                url: '../../video/servers/update?_' + $.now(),
                param: vm.servers,
                success: function(data) {
                    $.currentIframe().vm.load();
                }
            });
        }
    }
})