
function markerClicked(channelId) {
    return () => {
        dialogOpen({
            title: '播放',
            url: 'video/channel/play.html?_' + $.now(),
            width: '650px',
            height: '550px',
            scroll: true,
            success: function (iframeId) {
                console.log("[MAP] Setting channnel id to play video:", channelId);
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
    let zoomLevel = localStorage.getItem("zoomLevel");


    devices.forEach(device => {
        if (device.longitude != null && device.latitude != null) {
            var point = new BMap.Point(device.longitude, device.latitude);

            if (zoomLevel !== null) {
                map.centerAndZoom(point, zoomLevel);
            }
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);
            marker.addEventListener("click", markerClicked(device.id));
            var label = new BMap.Label(device.name, { offset: new BMap.Size(20, -10) });
            marker.setLabel(label);
        }
    })
}


$(function () {
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));



    map.enableScrollWheelZoom(true);

    map.addEventListener("zoomend", function (type) {
        localStorage.setItem("zoomLevel", map.getViewport().zoom);
    });


    map.addEventListener("click",function(e){
        localStorage.setItem("longitude", e.point.lng);
        localStorage.setItem("latitude", e.point.lat);
		console.log("[MAP] You clicked point:", e.point.lng, e.point.lat);
	});


    let request = {
        pageNumber: 1,
        pageSize: 1000, // Need to be fixed
        sortOrder: "asc",
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
	el : '#vueDiv',
	data : {
		keyword : null
	},
	methods : {
        acceptClick: function() {
            console.log("AcceptClick is called");
        }
    }
});

