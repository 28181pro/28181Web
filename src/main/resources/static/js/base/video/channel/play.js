/**
 * 播放js
 */
var vm = new Vue({
    el:'#dpLTE',
    data: {
        channel: {
            id: 0
        }
    },
    methods : {
        setForm: function() {
            $.SetForm({
                url: '../../video/channel/getChannelInfo?_' + $.now(),
                param: vm.channel.id,
                success: function(data) {
                    vm.channel = data;
                }
            });
        }
    }
})