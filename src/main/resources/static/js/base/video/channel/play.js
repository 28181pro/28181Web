/**
 * 播放js
 */
var vm = new Vue({
    el:'#dpLTE',
    data: {
        channel: {
            id: location.search.split('id=')[1]
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
    /*,
    mounted: function () {
        var channelId = this.channel.id;
        console.log("Channel id=", channelId);
        if (channelId) {
            $.SetForm({
                url: 'getChannelInfo?_' + $.now(),
                param: channelId,
                success: function(data) {
                    vm.channel = data;
                }
            });
        }
    }*/
})