<?php
/*
Plugin Name:cyberplayer播放器
Version: 1.1
Plugin URL: http://www.youngxj.cn
Description:百度云cyberplayer多功能播放器
ForEmlog:5.3.x
Author: 杨小杰
Author URL: http://www.youngxj.cn
*/

!defined('EMLOG_ROOT') && exit('access deined!');
function cyberplayer_js() {
  echo '<script type="text/javascript" src="'.BLOG_URL.'content/plugins/cyberplayer/cyberplayer.js"></script>';
}
function cyberplayer(){
  include 'cyberplayer_config.php';
?>
<script>
  $(document).ready(function(){
    $(".cyberplayer_insert").click(function(){
      $($(".ke-edit-iframe:first").contents().find(".ke-content")).append("<div id=\"playercontainer\"></div><script type=\"text/javascript\">var player=cyberplayer(\"playercontainer\").setup({width:"+($('#videowidth').val())+",height:"+($('#videoheight').val())+",autostart:true,stretching:\"uniform\",volume:100,controls:true,playlist:[{sources:[{file:'"+($('#videourl').val())+"',label:'"+($('#re1').val())+"'},{file:'"+($('#videourl2').val())+"',label:'"+($('#re2').val())+"'}]}],ak:\"<?php echo $config['ak'];?>\"});<\/script>");
    }); 
    $("#cyberplayer_button").click(function(){
      $("#cyberplayer_button").hide();
      $("#cyberplayer_buttons").show();
      $("#cyberplayer_text").show();});
    $("#cyberplayer_buttons").click(function(){
      $("#cyberplayer_buttons").hide();
      $("#cyberplayer_button").show();
      $("#cyberplayer_text").hide();}); 

  });
</script>
<style>
  #cyberplayer_box{font-weight:bold;font-size:12px;margin:5px 0; cursor:pointer;}
  #cyberplayer_box #cyberplayer_button{width:100px;height:auto;border: 0;padding: 3px 3px;font-size: 12px;margin: 3px 3px 3px 0;color: #fff;background-color: #20A2DE;cursor:pointer;text-align:center;}
  #cyberplayer_box #cyberplayer_button:hover{background-color:#ff9000;}
  #cyberplayer_box #cyberplayer_buttons{width:100px;height:auto;border: 0;padding: 3px 3px;font-size: 12px;margin: 3px 3px 3px 0;color: #fff;background-color: #20A2DE;cursor:pointer;text-align:center;}
  #cyberplayer_box #cyberplayer_buttons:hover{background-color:#ff9000;}
  #cyberplayer_text{font-weight:normal;margin:5px 0 10px 0;display:none;border: 1px solid #ccc;padding: 10px;width:500px;float:left;}
  #cyberplayer_text p{margin:0 0 10px 0;font-size:14px;}
  #cyberplayer_text input[type="text"]{font-size:12px; outline:none}
  #cyberplayer_text span{cursor:pointer;padding:3px 3px;float:left;font-size: 14px;margin: 0 10px 0 0;background: #6F8EC5;color:#fff;font-weight:bold;}
  #cyberplayer_text span:hover{background:#00aff0 !important;color:#fff !important;}
  #videowidth,#videoheight{width:55px !important;}
</style>
<div id="cyberplayer_box">
  <div id="cyberplayer_button" title="打开视频插入界面">cyberplayer</div>
  <div id="cyberplayer_buttons" title="关闭视频插入界面" style="display:none;">cyberplayer</div> 
  <div id="cyberplayer_text">

    <p>视频地址①:<input type="text" name="videourl" id="videourl" placeholder="填写需要插入的视频地址" />备注:<input type="text" name="re1" id="re1" placeholder="填写该地址备注" value="①"/></p>
    <p>视频地址②:<input type="text" name="videourl2" id="videourl2" placeholder="填写需要插入的视频地址" />备注:<input type="text" name="re2" id="re2" placeholder="填写该地址备注" value="②"/></p>
    <p>设置宽度：<input type="text" name="videowidth" id="videowidth" placeholder="650" value="650"/></p>
    <p>设置高度：<input type="text" name="videoheight" id="videoheight" placeholder="400" value="400"/></p>
    <p style="margin:10px 0 10px 0px;"><span class="cyberplayer_insert">插入视频代码</span></p></div>
</div><div style="clear:both"></div>
<?php
}
addAction('adm_writelog_head', 'cyberplayer');
addAction('index_head', 'cyberplayer_js');