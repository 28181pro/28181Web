// 函数 创建多个标注

function markerMouseOverOnline(marker, id, name) {
    return function () {
        var label = new BMap.Label( '名称：' + name, { offset: new BMap.Size(20, -10) });
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

function markerFunOnline(map, points, label, infoWindows) {
    var cameraIcon = new BMap.Icon("/images/32.png", new BMap.Size(32, 32));
    var markers = new BMap.Marker(points, {icon: cameraIcon});
    map.addOverlay(markers);
    markers.setLabel(label);
    markers.addEventListener("click", function (event) {
        map.openInfoWindow(infoWindows, points);//参数：窗口、点  根据点击的点出现对应的窗口
    });
}

function markerPointsOnline(map, mapPoints) {

    var cameraIcon = new BMap.Icon("/images/32.png", new BMap.Size(32, 32));
    var zoomLevel = localStorage.getItem('zoomLevel');

    var lat=0, lng=0, num = 0;

    for (var i = 0; i < mapPoints.length; i++) {
        var device = mapPoints[i];
        if (device.longitude != null && device.latitude != null) {

            var la =parseFloat(device.latitude);
            var lo = parseFloat(device.longitude);
            if( la > 0 && lo > 0){
                lat += la;
                lng += lo;

                num ++;

                var point = new BMap.Point(device.longitude, device.latitude);

                var marker = new BMap.Marker(point, { icon: cameraIcon });
                marker.addEventListener('click', markerClicked(device.id));
                map.addOverlay(marker);

                marker.addEventListener("mouseover", markerMouseOverOnline(marker,  device.branch, device.title));
                marker.addEventListener("mouseout", function (e) {
                    var label = this.getLabel();
                    label.setContent("");
                    label.setStyle({ border: "none", width: "0px", padding: "0px" });
                })
            }

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
    name: '',
    data: {
        map: null,
        mapList: {}
    },
    created: function () {
        var that = this;
        $.ajax({
            type: 'get',
            async: false,
            contentType: 'application/json',
            url: '/base/home/listChannelQuery?_' + $.now(),
            data: null,
            success: function (r) {
                that.mapList = r.rows;
            },
            dataType: 'json'
        });
    },

    //在线
    mounted: function () {

        var cameraIcon = new BMap.Icon("/images/32.png", new BMap.Size(32, 32));
        var that = this;
        //百度地图API功能
        //创建地图
        this.map =  new BMap.Map("chart_map");
        //设置地图样
        this.map.setMapStyle({style: 'midnight'});

        //创建地图坐标点,一般首次创建的这个点为地图的中心坐标点 杭州经纬度:(120.20000,30.26667)，长沙经纬度:(113.00000,28.21667)
        var point = new BMap.Point(120.446147, 30.239772);// new BMap.Point(113.00000, 28.21667);
        //初始化地图，设置中心坐标点和地图级数
        this.map.centerAndZoom(point, 11);
        //this.map.centerAndZoom(new BMapGL.Point(12529157.6, 3217853.21), 11);

        //创建标注
        var marker =  new BMap.Marker(point, { icon: cameraIcon }); //new BMap.Marker(point);
        //方法addOverlay() 向地图中添加覆盖物
        this.map.addOverlay(marker);

        //在PC端可以通过滚轮放大缩小地图，移动端关闭该功能
        this.map.enableScrollWheelZoom(true);
        //缩略图控件 默认右下角且呈可折叠状态（点击隐藏和显示）
        this.map.addControl(new BMap.OverviewMapControl());
        //地图类型控件 默认右上角可切换地图/卫星/三维三种状态
        this.map.addControl(new BMap.MapTypeControl({
            mapTypes: [
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]
        }));
        this.map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
        //addControl()向地图添加控件 平移和缩放控件 PC端默认左上角 移动端默认右下角且只有缩放功能
        this.map.addControl(new BMap.NavigationControl());
        //定位控件 默认左下角
        this.map.addControl(new BMap.GeolocationControl());

        markerPointsOnline(this.map, this.mapList);

    }
});

//离线地图，请打开这里
/*
function markerMouseOver(marker, id, name) {
    return function () {
        var label = new BMapGL.Label( '名称：' + name, { offset: new BMapGL.Size(20, -10) });
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
        // window.open('play.html?id=' + channelId, '_blank');
        dialogOpen({
            title: '播放',
            url : 'video/channel/video.html?_' + $.now(),

            width: '650px',
            height: '378px',
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

//离线地图

function markerFun(map, points, label, infoWindows) {
    var cameraIcon = new BMapGL.Icon("/images/32.png", new BMapGL.Size(32, 32));
    var markers = new BMapGL.Marker(points, {icon: cameraIcon});
    map.addOverlay(markers);
    markers.setLabel(label);
    markers.addEventListener("click", function (event) {
        map.openInfoWindow(infoWindows, points);//参数：窗口、点  根据点击的点出现对应的窗口
    });
}


// 函数 创建多个坐标点
function markerPoints(map, mapPoints) {


    var cameraIcon = new BMapGL.Icon("/images/32.png", new BMapGL.Size(32, 32));
    var zoomLevel = localStorage.getItem('zoomLevel');

    var lat=0, lng=0, num = 0;

    for (var i = 0; i < mapPoints.length; i++) {
        var device = mapPoints[i];
        if (device.longitude != null && device.latitude != null) {

            var la =parseFloat(device.latitude);
            var lo = parseFloat(device.longitude);
            if( la > 0 && lo > 0) {
                lat += la;
                lng += lo;

                num++;

                var point = new BMapGL.Point(device.longitude, device.latitude);


                var marker = new BMapGL.Marker(point, {icon: cameraIcon});
                marker.addEventListener('click', markerClicked(device.id));
                map.addOverlay(marker);

                marker.addEventListener("mouseover", markerMouseOver(marker, device.branch, device.title));
                marker.addEventListener("mouseout", function (e) {
                    var label = this.getLabel();
                    label.setContent("");
                    label.setStyle({border: "none", width: "0px", padding: "0px"});
                })
            }
        }
    }

    var center = new BMapGL.Point( lng / num, lat / num);
    if (zoomLevel !== null) {
        map.centerAndZoom(center, zoomLevel);
    } else {
        map.centerAndZoom(center, 11);
    }

}

var vm = new Vue({
    el: '#vueDiv',
    name: '',
    data: {
        map: null,
        mapList: {}
    },
    created: function () {
        var that = this;
        $.ajax({
            type: 'get',
            async: false,
            contentType: 'application/json',
            url: '/base/home/listChannelQuery?_' + $.now(),
            data: null,
            success: function (r) {
                that.mapList = r.rows;
            },
            dataType: 'json'
        });
    },

    //在线
    mounted: function () {

        var cameraIcon = new BMapGL.Icon("/images/32.png", new BMapGL.Size(32, 32));
        var that = this;
        //百度地图API功能
        //创建地图
        this.map =  new BMap.ETMap("chart_map");
        //设置地图样
        this.map.setMapStyle({style: 'midnight'});

        //创建地图坐标点,一般首次创建的这个点为地图的中心坐标点 杭州经纬度:(120.20000,30.26667)，长沙经纬度:(113.00000,28.21667)

        //初始化地图，设置中心坐标点和地图级数
        this.map.centerAndZoom(new BMapGL.Point(12529157.6, 3217853.21), 11);

        //创建标注
        var marker =  new BMapGL.Marker(point, { icon: cameraIcon }); //new BMap.Marker(point);
        //方法addOverlay() 向地图中添加覆盖物
        this.map.addOverlay(marker);

        //在PC端可以通过滚轮放大缩小地图，移动端关闭该功能
        this.map.enableScrollWheelZoom(true);
        //缩略图控件 默认右下角且呈可折叠状态（点击隐藏和显示）
        this.map.addControl(new BMapGL.OverviewMapControl());
        //地图类型控件 默认右上角可切换地图/卫星/三维三种状态
        this.map.addControl(new BMapGL.MapTypeControl({
            mapTypes: [
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]
        }));
        this.map.addControl(new BMapGL.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
        //addControl()向地图添加控件 平移和缩放控件 PC端默认左上角 移动端默认右下角且只有缩放功能
        this.map.addControl(new BMapGL.NavigationControl());
        //定位控件 默认左下角
        this.map.addControl(new BMapGL.GeolocationControl());

        markerPoints(this.map, this.mapList);

    }
});

*/






