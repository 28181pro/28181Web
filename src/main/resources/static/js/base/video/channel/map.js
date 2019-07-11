
function showDevices(map, devices) {
    let zoomLevel = localStorage.getItem("zoomLevel");


    devices.forEach(device=> {
        if (device.longitude != null && device.latitude != null) {
            var point = new BMap.Point(device.longitude, device.latitude);
            
            if (zoomLevel !== null) {
                map.centerAndZoom(point, zoomLevel);
            }
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);   
            marker.addEventListener("click",function (){
                alert("点击了标注..");
            });     
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

    map.addEventListener("zoomend", function(type){
        localStorage.setItem("zoomLevel", map.getViewport().zoom);
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
