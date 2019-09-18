/**
 * 新增-通道管理js
 */

index: [{
    "indexId":0,
    "name": "内网"
}, {
    "indexId":1,
    "name": "外网"
    }]

var vm = new Vue({
    el:'#dpLTE',
    data: {
        channel: {
            registerServerId: 0,
            registerServerName: null,
            streamServerId: 0,
            streamServerName: null,
            registerWay:null
        },
        lines:[{
            "index":0,
            "name": "内网"
        }, {
            "index":1,
            "name": "外网"
            }
        ]
    },
    methods : {
        registerServerTree: function() {
            dialogOpen({
                id: 'layerRegisterServerTree',
                title: '选择注册服务器',
                url: 'video/channel/registerServer.html?_' + $.now(),
                scroll : true,
                width: "300px",
                height: "450px",
                yes : function(iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                }
            })
        },
        streamServerTree: function() {
            dialogOpen({
                id: 'layerStreamServerTree',
                title: '选择流媒体服务器',
                url: 'video/channel/streamServer.html?_' + $.now(),
                scroll : true,
                width: "300px",
                height: "450px",
                yes : function(iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                }
            })
        },
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
