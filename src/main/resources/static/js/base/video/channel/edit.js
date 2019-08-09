/**
 * 编辑-通道管理js
 */
var vm = new Vue({
    el:'#dpLTE',
    data: {
        channel: {
            id: 0
        },
        longitude: '',
        latitude: ''
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
                id: 'mapLocation',
                title: '选择坐标',
                url: '../../video/channel/localMap.html?_' + $.now(),
                scroll : true,
                width: "500px",
                height: "450px",
                success : function(iframeId) {
                    top.frames[iframeId].vm.registerClick();
                },
                yes : function(iframeId) {               
                    top.frames[iframeId].vm.acceptClick();
                }
            })
        }
    }
})