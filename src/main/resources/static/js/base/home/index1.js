$(function () {
    echart_1();

    function echart_1() {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_1'));
        //数据加载完之前先显示一段简单的loading动画
        myChart.showLoading();
        var names=[];    //横坐标数组（实际用来盛放X轴坐标值）
        var values=[];    //纵坐标数组（实际用来盛放Y坐标值）
        $.ajax({
            type : "post",
            async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : '/base/home/selectGbDevice?_' + $.now(),    //请求发送到dataActiont处
            data : {},
            dataType : "json",        //返回数据形式为json
            success : function(r) {
                var result=r.rows;
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {
                    for(var i=0;i<result.length;i++){
                        names.push(result[i].name);
                        values.push(result[i].channelSum);
                    }
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        tooltip: {},
                        legend: {
                            data:['數量']
                        },
                        xAxis: {
                            data: names
                        },
                        yAxis: {
                            type: 'value'
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
                            data: values
                        }]
                    });
                }
            },
            error : function(errorMsg) {
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





