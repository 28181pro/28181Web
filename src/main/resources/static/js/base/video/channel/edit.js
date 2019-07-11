/**
 * 编辑-通道管理js
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
                url: '../../video/channel/info?_' + $.now(),
                param: vm.channel.id,
                success: function(data) {
                    vm.channel = data;
                }
            });
        },
        acceptClick: function() {
            if (!$('#form').Validform()) {
                return false;
            }
            $.ConfirmForm({
                url: '../../video/channel/update?_' + $.now(),
                param: vm.channel,
                success: function(data) {
                    $.currentIframe().vm.load();
                }
            });
        },
        sellocation: function() {
            dialogOpen({
                id: 'location',
                title: '选择坐标',
                url: '../../video/channel/map.html?_' + $.now(),
                scroll : true,
                width: "500px",
                height: "450px",
                yes : function(iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                }
            })
        }
    }
})