// // 函数 创建多个标注
// function markerFun(map, points, label, infoWindows) {
//     var markers = new BMap.Marker(points);
//     map.addOverlay(markers);
//     //  markers.setLabel(label);
//     markers.addEventListener("click", function (event) {
//         map.openInfoWindow(infoWindows, points);//参数：窗口、点  根据点击的点出现对应的窗口
//     });
// }
//
// // 函数 创建多个坐标点
// function markerPoints(map, mapPoints) {
//     for (var i = 0; i < mapPoints.length; i++) {
//         var points = new BMap.Point(mapPoints[i].longitude, mapPoints[i].latitude);//创建坐标点
//         var opts = {
//             width: 250,
//             height: 100,
//             title: mapPoints[i].title
//         };
//         var label = new BMap.Label(mapPoints[i].branch, {
//             offset: new BMap.Size(25, 5)
//         });
//         var infoWindows = new BMap.InfoWindow(mapPoints[i].con, opts);
//         markerFun(map, points, label, infoWindows);
//     }
// }
//
// var vm = new Vue({
//     el: '#vueDiv',
//     name: '',
//     data: {
//         map: null,
//         mapList: {}
//     },
//     created: function () {
//         var that = this;
//         $.ajax({
//             type: 'get',
//             async: false,
//             contentType: 'application/json',
//             url: '/base/home/listChannelQuery?_' + $.now(),
//             data: null,
//             success: function (r) {
//                 that.mapList = r.rows;
//             },
//             dataType: 'json'
//         });
//     },
//
//     mounted: function () {
//         // var that = this;
//         //百度地图API功能
//         //创建地图
//         this.map = new BMap.Map("chart_map");
//         //设置地图样式
//        this.map.setMapStyle({style:'midnight'});
//
//         //创建地图坐标点,一般首次创建的这个点为地图的中心坐标点 杭州经纬度:(120.20000,30.26667)，长沙经纬度:(113.00000,28.21667)
//         var point = new BMap.Point(120.20000,30.26667);
//         //初始化地图，设置中心坐标点和地图级数
//         this.map.centerAndZoom(point, 9);
//
//         //创建标注
//         var marker = new BMap.Marker(point);
//         //方法addOverlay() 向地图中添加覆盖物
//         this.map.addOverlay(marker);
//         //在PC端可以通过滚轮放大缩小地图，移动端关闭该功能
//         this.map.enableScrollWheelZoom(true);
//         //缩略图控件 默认右下角且呈可折叠状态（点击隐藏和显示）
//         this.map.addControl(new BMap.OverviewMapControl());
//         //地图类型控件 默认右上角可切换地图/卫星/三维三种状态
//         this.map.addControl(new BMap.MapTypeControl({
//             mapTypes: [
//                 BMAP_NORMAL_MAP,
//                 BMAP_HYBRID_MAP
//             ]
//         }));
//         this.map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
//         //addControl()向地图添加控件 平移和缩放控件 PC端默认左上角 移动端默认右下角且只有缩放功能
//         this.map.addControl(new BMap.NavigationControl());
//         //定位控件 默认左下角
//         this.map.addControl(new BMap.GeolocationControl());
//
//         markerPoints(this.map, this.mapList);
//
//
//     }
// });








// 函数 创建多个标注
function markerFun(map, points, label, infoWindows) {
    var markers = new BMap.Marker(points);
    map.addOverlay(markers);
    //  markers.setLabel(label);
    markers.addEventListener("click", function (event) {
        map.openInfoWindow(infoWindows, points);//参数：窗口、点  根据点击的点出现对应的窗口
    });
}

// 函数 创建多个坐标点
function markerPoints(map, mapPoints) {
    for (var i = 0; i < mapPoints.length; i++) {
        var points = new BMap.Point(mapPoints[i].longitude, mapPoints[i].latitude);//创建坐标点
        var opts = {
            width: 250,
            height: 100,
            title: mapPoints[i].title
        };
        var label = new BMap.Label(mapPoints[i].branch, {
            offset: new BMap.Size(25, 5)
        });
        var infoWindows = new BMap.InfoWindow(mapPoints[i].con, opts);
        markerFun(map, points, label, infoWindows);
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

    mounted: function () {
        // var that = this;
        //百度地图API功能
        //创建地图
        this.map = new BMap.Map("chart_map");
        //设置地图样式
        this.map.setMapStyle({style:'midnight'});

        //创建地图坐标点,一般首次创建的这个点为地图的中心坐标点 杭州经纬度:(120.20000,30.26667)，长沙经纬度:(113.00000,28.21667)
        var point = new BMap.Point(120.20000,30.26667);
        //初始化地图，设置中心坐标点和地图级数
        this.map.centerAndZoom(point, 9);

        //创建标注
        var marker = new BMap.Marker(point);
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

        markerPoints(this.map, this.mapList);


    }
});









