function markerMouseOver(marker, name) {
    return function () {
        var label = new BMap.Label(name, { offset: new BMap.Size(20, -10) });
        label.setStyle({
            width: "120px",
            color: '#fff',
            background: '#3c8dbc',
            border: '1px solid "#3c8dbc"',
            borderRadius: "5px",
            textAlign: "center",
            height: "26px",
            lineHeight: "26px"
        });
        marker.setLabel(label);
    }
}

function markerClicked(channelId) {
    return function () {
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

var cameraIcon = new BMap.Icon("/images/2.png", new BMap.Size(30, 30));

function showDevices(map, devices) {
    let zoomLevel = localStorage.getItem('zoomLevel');

    for (var i = 0; i < devices.length; i++) {
        var device = devices[i];
        if (device.longitude != null && device.latitude != null) {
            var point = new BMap.Point(device.longitude, device.latitude);

            if (zoomLevel !== null) {
                map.centerAndZoom(point, zoomLevel);
            } else {
                map.centerAndZoom(point, 11);
            }
            var marker = new BMap.Marker(point, { icon: cameraIcon });
            marker.addEventListener('click', markerClicked(device.id));
            map.addOverlay(marker);

            marker.addEventListener("mouseover", markerMouseOver(marker, device.name));
            marker.addEventListener("mouseout", function (e) {
                var label = this.getLabel();
                label.setContent("");
                label.setStyle({ border: "none", width: "0px", padding: "0px" });
            })
            // marker.setLabel(label);
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


    if (vm.clickable === true) {
        map.addEventListener('click', function (e) {
            longitude = e.point.lng;
            latitude = e.point.lat;
            if (currentMarker != null) {
                map.removeOverlay(currentMarker);
            }
            currentMarker = new BMap.Marker(e.point);
            map.addOverlay(currentMarker);
        });
    }


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
        clickable: false
    },
    methods: {
        acceptClick: function () {
            top.layerForm.vm.channel.longitude = longitude;
            top.layerForm.vm.channel.latitude = latitude;
            dialogClose();
        }
    }
});

