function markerMouseOver(marker, id, name) {
    return function () {
        var label = new BMap.Label('编号：' + id + '，名称：' + name, { offset: new BMap.Size(20, -10) });
        label.setStyle({
            color: '#fff',
            background: '#3c8dbc',
            border: '1px solid "#3c8dbc"',
            borderRadius: "5px",
            textAlign: "center",
            height: "26px",
            lineHeight: "26px",
            padding: "5px"
        });
        marker.setLabel(label);
    }
}

function markerClicked(channelId) {
    return function () {
         dialogOpen({
             title: '播放',
             url : 'video/channel/video2.html?_' + $.now(),
             width : '500px',
             height : '420px',
            // width: '650px',
           //  height: '500px',

             scroll: true,
             success: function (iframeId) {
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
    var cameraIcon = new BMap.Icon("/images/32.png", new BMap.Size(32, 32));
    var zoomLevel = localStorage.getItem('zoomLevel');

    var lat=0, lng=0, num = 0; 

    for (var i = 0; i < devices.length; i++) {
        var device = devices[i];
        if (device.longitude != null && device.latitude != null) {
            num ++;
            lat += parseFloat(device.latitude);
            lng += parseFloat(device.longitude);
            var point = new BMap.Point(device.longitude, device.latitude);


            var marker = new BMap.Marker(point, { icon: cameraIcon });
            marker.addEventListener('click', markerClicked(device.id));
            map.addOverlay(marker);

            marker.addEventListener("mouseover", markerMouseOver(marker, device.id, device.name));
            marker.addEventListener("mouseout", function (e) {
                var label = this.getLabel();
                label.setContent("");
                label.setStyle({ border: "none", width: "0px", padding: "0px" });
            })
        }
    }
 
    var center = new BMap.Point( lng / num, lat / num);
    if (zoomLevel !== null) {
        map.centerAndZoom(center, zoomLevel);
    } else {
        map.centerAndZoom(center, 11);
    }
}

var vm = new Vue({
    el: '#vueDiv',
    data: {
        map: null,
        longitude: null,
        latitude: null,
        currentMarker: null
    },

    mounted: function () {
        var self = this;
        this.map = new BMap.Map('allmap');

        this.map.setMapStyle({style:'light'});

        this.map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
        this.map.addControl(new BMap.MapTypeControl({
            mapTypes: [
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]
        }));

        this.map.enableScrollWheelZoom(true);

        this.map.addEventListener('zoomend', function (type) {
            localStorage.setItem('zoomLevel', self.map.getViewport().zoom);
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
                showDevices(self.map, r.rows);
            },
            dataType: 'json'
        });
    },
    methods: {

        registerClick: function () {
            var self = this;
            this.map.addEventListener('click', function (e) {
                self.longitude = e.point.lng;
                self.latitude = e.point.lat;
                if (self.currentMarker != null) {
                    self.map.removeOverlay(self.currentMarker);
                }
                self.currentMarker = new BMap.Marker(e.point);
                self.map.addOverlay(self.currentMarker);
            });

        },
        acceptClick: function () {
            if (this.longitude === null || this.latitude == null) {
                alert('请选择地图上的点');
                return;
            }
            top.layerForm.vm.channel.longitude = this.longitude;
            top.layerForm.vm.channel.latitude = this.latitude;
            dialogClose();
        }
    }
});

