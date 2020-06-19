$(function() {
    initialPage();
});

function initialPage() {
}

var vm = new Vue({
    el: '#dpLTE',
    data: {
        channel: {
            channelCode: null,
        }
    },
    methods: {
        setDiv:function() {

                var filedate = '';
                var date = new Date();
                var year = date.getFullYear() + '';
                var mon_tmp = (date.getMonth() + 1);
                var day_tmp = date.getDate();
                var mon = mon_tmp < 10 ? '0' + mon_tmp : mon_tmp + '';
                var day = day_tmp < 10 ? '0' + day_tmp : day_tmp + '';
                filedate = year + mon + day;
                var path = filedate + '/' + vm.channel.channelCode + '/';
               // if (vm.channel.channelCode != '34020000001320000015')
               // {
                    $('#imagecontain1').append(
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/1.jpg"  alt="../../photo/' + path + '1.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/2.jpg"  alt="../../photo/' + path + '2.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/3.jpg"   alt="../../photo/' + path + '3.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/4.jpg"   alt="../../photo/' + path + '4.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/5.jpg"  alt="../../photo/' + path + '5.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/6.jpg"  alt="../../photo/' + path + '6.jpg"/></a>'
                    );
                    $('#imagecontain2').append(
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/7.jpg"  alt="../../photo/' + path + '7.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/8.jpg"   alt="../../photo/' + path + '8.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/9.jpg"    alt="../../photo/' + path + '9.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/10.jpg"   alt="../../photo/' + path + '10.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/11.jpg"  alt="../../photo/' + path + '11.jpg"/></a>\n' +
                        '<a href="#"><img src="../../photo/' + path + 'thumbs/12.jpg"  alt="../../photo/' + path + '12.jpg"/></a>'
                    );

               // }
               // else
               // {
                /*
                    $('#imagecontain1').append(
                        '<a href="#"><img src="http://49.235.134.57:8080/' + path +'thumbs/1.jpg"  alt="http://49.235.134.57:8080/'+ path +'1.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/2.jpg"  alt="http://49.235.134.57:8080/'+ path +'2.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/3.jpg"   alt="http://49.235.134.57:8080/'+ path +'3.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/4.jpg"   alt="http://49.235.134.57:8080/'+ path +'4.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/5.jpg"  alt="http://49.235.134.57:8080/'+ path +'5.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/6.jpg"  alt="http://49.235.134.57:8080/'+ path +'6.jpg"/></a>'
                    );
                    $('#imagecontain2').append(
                        '<a href="#"><img src="http://49.235.134.57:8080/' + path +'thumbs/7.jpg"  alt="http://49.235.134.57:8080/'+ path +'7.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/8.jpg"   alt="http://49.235.134.57:8080/'+ path +'8.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/9.jpg"    alt="http://49.235.134.57:8080/'+ path +'9.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/10.jpg"   alt="http://49.235.134.57:8080/'+ path +'10.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/11.jpg"  alt="http://49.235.134.57:8080/'+ path +'11.jpg"/></a>\n' +
                        '<a href="#"><img src="http://49.235.134.57:8080/'+ path +'thumbs/12.jpg"  alt="http://49.235.134.57:8080/'+ path +'12.jpg"/></a>'
                    );
*/
                //}


        },
        acceptClick: function () {

        },
        mounted: function () {

        }
    }
});
