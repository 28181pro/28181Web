$(function () {
    echart_3();

    //echart_3通道在线情况
    function echart_3() {
        var myChart = echarts.init(document.getElementById('chart_3'));
        myChart.showLoading();
        var names = [];    //横坐标数组（实际用来盛放X轴坐标值）
        var values = [];    //纵坐标数组（实际用来盛放Y坐标值）
        $.ajax({
            type: 'post',
            async: 'true',
            url: '/base/home/getStatisticOnlineNumByGbdevices?_' + $.now(),
            data: {},
            dataType: 'json',
            success: function (r) {
                var result = r.rows;
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        names.push(result[i].name);
                        values.push(result[i].value);
                    }
                    myChart.hideLoading();
                    myChart.setOption({
                        title: {
                            text: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['台区设备情况'],
                            textStyle: {
                                color: '#fff'
                            },
                            top: '8%'
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {show: true},
                                magicType: {show: true, type: ['line', 'bar']}
                            }
                        },
                        grid: {
                            top: '40%',
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        color: ['#FF4949','#FFA74D','#FFEA51','#4BF0FF','#44AFF0','#4E82FF','#584BFF','#BE4DFF','#F845F1'],
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: names,
                            splitLine: {
                                show: false
                            },
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
                            name: '数量',
                            type: 'value',
                            splitLine: {
                                show: false
                            },
                            // y轴的字体样式
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#fff'
                                }
                            },
                            axisLine: {
                                lineStyle: {
                                    color: '#fff'
                                }
                            }

                        },
                        series: [
                            {
                                name: '数量',
                                type: 'line',
                                data: values,

                                itemStyle: {
                                    normal: {
                                        color: '#FAD860'
                                    }
                                },

                            }
                        ]
                    });
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        });
    }


});




