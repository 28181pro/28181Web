/**
 * 新增-通道管理js
 */
var vm = new Vue({
    el:'#dpLTE',
    data: {
        channel: {

        }
    },
    methods : {
        acceptClick: function() {
            if (!$('#form').Validform()) {
                return false;
            }
            $.SaveForm({
                url: '../../video/channel/save?_' + $.now(),
                param: vm.channel,
                success: function(data) {
                    $.currentIframe().vm.load();
                }
            });
        }
    }
})
