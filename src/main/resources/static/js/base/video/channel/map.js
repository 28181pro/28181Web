
function showDevices(map, devices) {
    devices.forEach(device=> {
        if (device.longitude != null && device.latitude != null) {
            var point = new BMap.Point(device.longitude, device.latitude);
            map.centerAndZoom(point, 12);
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);        
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
    map.setCurrentCity("北京");
    map.enableScrollWheelZoom(true);

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
