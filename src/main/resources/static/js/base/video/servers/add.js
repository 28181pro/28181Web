/**
 * 新增-服务管理js
 */

var vm = new Vue({
    el:'#dpLTE',
    data: {
        servers:{
            name:null,
            lanIp:null,
            lanPort:null,
            wanIp:null,
            wanPort:null
        }
    },
    methods : {
        acceptClick: function() {
            if (!$('#form').Validform()) {
                return false;
            }
            $.SaveForm({
                url: '../../video/servers/save?_' + $.now(),
                param: vm.servers,
                success: function(data) {
                    $.currentIframe().vm.load();
                }
            });
        }
    }
})
