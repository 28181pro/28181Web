$(function () {
    echart_2();
//饼图/扇形
    function echart_2() {
        var echartsB = echarts.init(document.getElementById('chart_2'));
        echartsB.showLoading();
        var names=[];    //横坐标数组（实际用来盛放X轴坐标值）
        var values=[];    //纵坐标数组（实际用来盛放Y坐标值）
        $.ajax({
            type: 'post',
            async: 'true',
            url: '/base/home/getStatisticChannelStatus?_' + $.now(),
            data: {},
            dataType: 'json',
            success: function (r) {
                var result=r.rows;
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        names.push(result[i].name);
                        values.push(result[i].value);
                    }
                    echartsB.hideLoading();
                    echartsB.setOption( {
                        title : {
                            text: '通道状态',
                            subtext: '在线/离线状态',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient : 'vertical',
                            x : 'left',
                            data:names
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                magicType : {
                                    show: true,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                }
                            }
                        },
                        calculable : true,
                        series : [
                            {
                                name:'状态数',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:result
                            }
                        ]
                    });
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                echartsB.hideLoading();
            }
        });
    }



});

