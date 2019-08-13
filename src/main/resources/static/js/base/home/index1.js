$(function () {
    echart_1();

    function echart_1() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_1'));
        //数据加载完之前先显示一段简单的loading动画
        myChart.showLoading();
        var names = [];    //横坐标数组（实际用来盛放X轴坐标值）
        var values = [];    //纵坐标数组（实际用来盛放Y坐标值）
        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: '/base/home/selectGbDevice?_' + $.now(),    //请求发送到dataActiont处
            data: {},
            dataType: "json",        //返回数据形式为json
            success: function (r) {
                var result = r.rows;
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        names.push(result[i].name);
                        values.push(result[i].channelSum);
                    }
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        tooltip: {},
                        legend: {
                            data: ['數量'],
                            show: true,
                            textStyle: {
                                color: '#fff'
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data: names,
                            //去除网格线
                            splitLine: {show: false},
                            // x轴的字体样式
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#fff'
                                },
                                interval: 0
                            },
                            axisLine: {
                                lineStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            //去除网格线
                           // splitLine: {show: false},
                            // y轴的字体样式
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#fff'
                                }
                            },
                            axisLine: {
                                lineStyle: {
                                    color: '#26C0C0'
                                }
                            }
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '数量',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    color: '#26C0C0'
                                }
                            },
                            data: values,
                            itemStyle: {
                                //通常情况下：
                                normal: {
                                    //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                    color: function (params) {
                                        var colorList = ['#B8860B', '#8B008B', '#A0522D', '#8FBC8F', '#f29e3c', '#c05bdd', '#FF1493', '#65d186', '#DAA520', '#CD5C5C', '#f67287', '#F08080', '#00FF00', '#808000', '#7a65f2', '#EE82EE']; //每根柱子的颜色
                                        return colorList[params.dataIndex];
                                    }
                                },
                                //鼠标悬停时：
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }

                        }]
                    });
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        });//end ajax


        window.addEventListener("resize", function () {
            myChart.resize();
        });


    }

});





