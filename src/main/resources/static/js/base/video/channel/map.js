
function markerClicked(channelId) {
    return function() {
        dialogOpen({
            title: '播放',
            url: 'video/channel/play.html?_' + $.now(),
            width: '650px',
            height: '550px',
            scroll: true,
            success: function (iframeId) {
                console.log('[MAP] Setting channnel id to play video:', channelId);
                top.frames[iframeId].vm.channel.id = channelId;
                top.frames[iframeId].vm.setForm();
            },
            yes: function (iframeId) {
                top.frames[iframeId].vm.acceptClick();
            },
        });
    }
}


function showDevices(map, devices) {
    let zoomLevel = localStorage.getItem('zoomLevel');

    for(var i = 0; i < devices.length; i++) {
        var device = devices[i];     
           if (device.longitude != null && device.latitude != null) {
            var point = new BMap.Point(device.longitude, device.latitude);

            if (zoomLevel !== null) {
                map.centerAndZoom(point, zoomLevel);
            } else {
                map.centerAndZoom(point, 11);
            }
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);
            marker.addEventListener('click', markerClicked(device.id));
            var label = new BMap.Label(device.name, { offset: new BMap.Size(20, -10) });
            marker.setLabel(label);
        }
    }
}
var longitude;
var latitude;
var currentMarker;

$(function () {
    var map = new BMap.Map('allmap');
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));



    map.enableScrollWheelZoom(true);

    map.addEventListener('zoomend', function (type) {
        localStorage.setItem('zoomLevel', map.getViewport().zoom);
    });


    map.addEventListener('click', function (e) {
        longitude = e.point.lng;
        latitude = e.point.lat;
        if (currentMarker != null) {
            map.removeOverlay(currentMarker);
        }
        currentMarker = new BMap.Marker(e.point);
        map.addOverlay(currentMarker);
    });


    let request = {
        pageNumber: 1,
        pageSize: 1000, // Need to be fixed
        sortOrder: 'asc',
        username: null
    };

    $.ajax({
        type: 'POST',
        async: false,
        contentType: 'application/json',
        url: 'list?_' + $.now(),
        data: JSON.stringify(request),
        success: function (r) {
            showDevices(map, r.rows);
        },
        dataType: 'json'
    });
})


var vm = new Vue({
    el: '#vueDiv',
    data: {
        keyword: null
    },
    methods: {
        acceptClick: function () {
            top.layerForm.vm.longitude = longitude;
            top.layerForm.vm.latitude = latitude;
            dialogClose();
        }
    }
});

