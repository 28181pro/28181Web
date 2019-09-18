<?php
!defined('EMLOG_ROOT') && exit('access deined!');
function plugin_setting_view() {
  include 'cyberplayer_config.php';
  echo '<h1><b>cyberplayer播放器设置项</h1>';
  if (isset($_GET['setting']))
    echo '<span class="actived">插件设置完成</span>';
  echo '</div><div class=line></div>';
?>
<div class="post">
  <div class="des" style="margin: 5px 0; width: 100%;background-color: #FFFFE5;padding: 5px 10px;border: 1px #CCCCCC solid;clear: both;border-radius: 4px;">填写百度云accessKey即可<br/>支持mp4, hls, flv等常用视频格式<br/>参考<a href="http://cyberplayer.bcelive.com/demo/new/index.html">官方文档</a>进行优化<br/>如有问题联系<a href="http://www.youngxj.cn">杨小杰博客</a></div>
  <form action="plugin.php?plugin=cyberplayer&action=setting" method="post">
    <div>
      百度云accessKey<br/>
      <p><input type="text" name="ak" value='<?php echo $config["ak"];?>' style="width:30%;"></p>
      <p><input type="submit" value="保存设置"  class="button"/></p>
    </div>
  </form>
</div>
<?php
}

function plugin_setting() {
  $newConfig = '<?php
$config = array(
	"ak" => "'.$_POST["ak"].'"
);';
  @file_put_contents(EMLOG_ROOT.'/content/plugins/cyberplayer/cyberplayer_config.php', $newConfig);
}
?>