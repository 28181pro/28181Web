function webUploaderInit(option) {
    var webUploader = this;
    webUploader.imageurl = option.imageurl;
    webUploader.id = option.id;
    webUploader.url = option.url ? option.url : "/File/UpLoadProcess";
    webUploader.folder = option.folder;
    webUploader.fileSingleSizeLimit = option.fileSingleSizeLimit ? option.fileSingleSizeLimit : 1024 * 1024 * 400;  //默认单个上传文件大小限制400M
    webUploader.fileSizeLimit = option.fileSizeLimit ? option.fileSizeLimit : 1024 * 1024 * 1024;  //默认上传文件大小限制1G
    webUploader.fileNumLimit = option.fileNumLimit ? option.fileNumLimit : 3;  //默认上传文件数300
    webUploader.fileDescription = option.fileDescription ? option.fileDescription : "或将附件拖到这里，单次最多可选" + webUploader.fileNumLimit + "个";
    webUploader.auto = option.auto ? option.auto : true;
    webUploader.accept = option.accept;
    //初始化view
    webUploader.filePickerID = webUploader.id + "_filePicker";
    webUploader.otherFilePickerID = webUploader.id + "_otherFilePicker";
    webUploader.dndAreaID = webUploader.id + "_dndAreaID"; 0
    webUploader.progressID = webUploader.id + "_progressID";
    webUploader.isfile = option.isfile ? option.isfile : false; //默认为图片文件，不是普通文件
    webUploader.defaultbg = option.defaultbg ? option.defaultbg : "default-bg";//组件背景
    webUploader.typeid = option.typeid;
    $("#" + webUploader.id).html('<div class="queueList"><div id="' + webUploader.dndAreaID + '" class="placeholder ' + webUploader.defaultbg + ' ' + (option.isfile ? 'files' : '') + '"><div id="' + webUploader.filePickerID + '"></div><p>' + webUploader.fileDescription + '</p></div></div><div class="statusBar" style="display:none;"><div class="progress"><span class="text">0%</span><span class="percentage"></span></div><div class="info"></div><div class="btns"><div id="' + webUploader.otherFilePickerID + '"></div><div class="uploadBtn">开始上传</div></div></div>');

    webUploader.$wrap = $('#' + webUploader.id);
    webUploader.$queue = $('<ul class="filelist"></ul>').appendTo(webUploader.$wrap.find('.queueList'));//图片容器
    webUploader.$statusBar = webUploader.$wrap.find('.statusBar');//状态栏，包括进度和控制按钮
    webUploader.$info = webUploader.$statusBar.find('.info');//文件总体选择信息
    webUploader.$upload = webUploader.$wrap.find('.uploadBtn');//上传按钮
    if (webUploader.auto) {
        webUploader.$upload.hide();
    }
    webUploader.$placeHolder = webUploader.$wrap.find('.placeholder');//没选择文件之前的内容
    webUploader.$progress = webUploader.$statusBar.find('.progress').hide();//总体进度条
    webUploader.fileCount = 0;// 添加的文件数量
    webUploader.fileSize = 0;// 添加的文件总大小
    webUploader.ratio = 1;// 优化retina, 在retina下这个值是2
    webUploader.thumbnailWidth = 180 * webUploader.ratio;// 缩略图大小
    webUploader.thumbnailHeight = 180 * webUploader.ratio;
    webUploader.state = 'pedding';// 可能有pedding, ready, uploading, confirm, done.
    webUploader.percentages = {};// 所有文件的进度信息，key为file id
    webUploader.supportTransition = (function () {
        var s = document.createElement('p').style,
            r = 'transition' in s ||
                  'WebkitTransition' in s ||
                  'MozTransition' in s ||
                  'msTransition' in s ||
                  'OTransition' in s;
        s = null;
        return r;
    })();

    webUploader.uploader = WebUploader.create({
        auto: webUploader.auto,
        pick: {
            id: '#' + webUploader.filePickerID,
            label: '点击选择'
        },
        formData: {
            folder: webUploader.folder,
            width: webUploader.thumbnailWidth,
            height: webUploader.thumbnailHeight

        },

        dnd: '#' + webUploader.id + ' .queueList',
        disableGlobalDnd: true,

        chunked: false,
        accept: option.accept,
        swf: '/Scripts/plugins/webuploader/Uploader.swf',
        server: webUploader.url,
        fileNumLimit: webUploader.fileNumLimit,
        fileSizeLimit: webUploader.fileSizeLimit,
        fileSingleSizeLimit: webUploader.fileSingleSizeLimit,

        // 图片压缩
        compress: {
            width: 800,
            height: 800,
            quality: 80
        }
    });

    if (option.oneimage == undefined || option.oneimage == null || !option.oneimage) {
        webUploader.uploader.addButton({
            id: '#' + webUploader.otherFilePickerID,
            label: '继续添加'
        });
    }



    webUploader.uploader.onUploadProgress = function (file, percentage) {
        var $li = $('#' + file.id);
        var $percent = $li.find('.progress span');
        $percent.css('width', percentage * 100 + '%');
        webUploader.percentages[file.id][1] = percentage;
        webUploader.updateTotalProgress();
    };

    webUploader.uploader.onFileQueued = function (file) {
        webUploader.fileCount++;
        webUploader.fileSize += file.size;

        if (webUploader.fileCount === 1) {
            webUploader.$placeHolder.addClass('element-invisible');
            webUploader.$statusBar.show();
        }

        webUploader.addFile(file);
        webUploader.setState('ready');
        webUploader.updateTotalProgress();
    };

    webUploader.uploader.onFileDequeued = function (file) {
        webUploader.fileCount--;
        webUploader.fileSize -= file.size;

        if (!webUploader.fileCount) {
            webUploader.setState('pedding');
        }

        webUploader.removeFile(file);
        webUploader.updateTotalProgress();
    };

    webUploader.uploader.on('beforeFileQueued', function (file) {
        if (webUploader.typeid && webUploader.typeid !== undefined) {
            webUploader.uploader.option('formData', {
                folder: webUploader.folder + "/" + $("#" + webUploader.typeid).val()
            });
        }
    });

    webUploader.uploader.on('uploadSuccess', function (file, response) {
        var fileNewName = response.name;
        var fileAttachmentType = response.attachmentType;
        $('#' + file.id).find("#FileNewName").val(fileNewName);
        $('#' + file.id).find("#FileAttachmnetType").val(fileAttachmentType);
    });

    webUploader.uploader.on('all', function (type) {
        switch (type) {
            case 'uploadFinished':
                webUploader.setState('confirm');
                break;

            case 'startUpload':
                webUploader.setState('uploading');
                break;

            case 'stopUpload':
                webUploader.setState('paused');
                break;
        }
    });

    webUploader.uploader.onError = function (code) {
        if (code == "Q_TYPE_DENIED") {
            layer.alert('文件格式不正确！');
        }
        if (code == "Q_EXCEED_SIZE_LIMIT") {
            layer.alert('文件大小超出最大限度！');
        }

    };

    webUploader.uploader.on('uploadAccept', function (file, response) {
        if (response.code == 1) {
            return false;
        }
        return true;
    });

    webUploader.$upload.on('click', function () {
        if ($(this).hasClass('disabled')) {
            return false;
        }

        if (webUploader.state === 'ready') {
            webUploader.uploader.upload();
        } else if (webUploader.state === 'paused') {
            webUploader.uploader.upload();
        } else if (webUploader.state === 'uploading') {
            webUploader.uploader.stop();
        }
    });

    webUploader.$info.on('click', '.retry', function () {
        webUploader.uploader.retry();
    });

    webUploader.$info.on('click', '.ignore', function () {
        alert('todo');
    });

    webUploader.updateTotalProgress = function () {
        var loaded = 0;
        var total = 0;
        var spans = webUploader.$progress.children();
        var percent;

        $.each(webUploader.percentages, function (k, v) {
            total += v[0];
            loaded += v[0] * v[1];
        });

        percent = total ? loaded / total : 0;

        spans.eq(0).text(Math.round(percent * 100) + '%');
        spans.eq(1).css('width', Math.round(percent * 100) + '%');
        webUploader.updateStatus();
    }

    webUploader.updateStatus = function () {
        var text = '';
        var stats;
        if (webUploader.state === 'ready') {
            text = '选中' + webUploader.fileCount + '个图片，共' + WebUploader.formatSize(webUploader.fileSize) + '。';
        } else if (webUploader.state === 'confirm') {
            stats = webUploader.uploader.getStats();
            if (stats.uploadFailNum) {
                text = '已成功上传' + stats.successNum + '张照片，' + stats.uploadFailNum + '个照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>';
            }
        } else {
            stats = webUploader.uploader.getStats();
            text = '共' + webUploader.fileCount + '个';
        }

        webUploader.$info.html(text);
    }

    webUploader.setState = function (val) {
        var file, stats;

        if (val === webUploader.state) {
            return;
        }

        webUploader.$upload.removeClass('state-' + webUploader.state);
        webUploader.$upload.addClass('state-' + val);
        webUploader.state = val;

        switch (webUploader.state) {
            case 'pedding':
                webUploader.$placeHolder.removeClass('element-invisible');
                webUploader.$queue.parent().removeClass('filled');
                webUploader.$queue.hide();
                webUploader.$statusBar.addClass('element-invisible');
                webUploader.uploader.refresh();
                break;

            case 'ready':
                webUploader.$placeHolder.addClass('element-invisible');
                //$('#' + webUploader.otherFilePickerID).removeClass('element-invisible');
                webUploader.$queue.parent().addClass('filled');
                webUploader.$queue.show();
                webUploader.$statusBar.removeClass('element-invisible');
                webUploader.uploader.refresh();
                break;

            case 'uploading':
                //$('#' + webUploader.otherFilePickerID).addClass('element-invisible');
                webUploader.$progress.show();
                webUploader.$upload.text('暂停上传');
                break;

            case 'paused':
                webUploader.$progress.show();
                webUploader.$upload.text('继续上传');
                break;

            case 'confirm':
                webUploader.$progress.hide();
                webUploader.$upload.text('开始上传').addClass('disabled');

                stats = webUploader.uploader.getStats();
                if (stats.successNum && !stats.uploadFailNum) {
                    webUploader.setState('finish');
                    return;
                }
                break;
            case 'finish':
                stats = webUploader.uploader.getStats();
                if (stats.successNum) {
                    //alert('上传成功');
                } else {
                    // 没有成功的图片，重设
                    webUploader.state = 'done';
                    location.reload();
                }
                break;
            case "init":
                webUploader.$placeHolder.hide();
                webUploader.$queue.parent().addClass('filled');
                webUploader.$progress.hide();
                break;
        }

        webUploader.updateStatus();
    }

    webUploader.addFile = function (file) {
        var $li = $('<li id="' + file.id + '">' +
                '<p class="title">' + file.name + '</p>' +
                '<p class="imgWrap"></p>' +
                '<p class="progress"><span></span></p>' +
                '<input type="hidden" id="FileName" name="FileName" value="' + file.name + '" />' +
                '<input type="hidden" id="FileNewName" name="FileNewName" />' +
                '<input type="hidden" id="FileType" name="FileType" value="' + file.ext + '" />' +
                '<input type="hidden" id="FileSize" name="FileSize" value="' + file.size + '" />' +
                '<input type="hidden" id="FileAttachmnetType" name="FileAttachmnetType" />' +
                '</li>'),

            $btns = $('<div class="file-panel">' +
                '<span class="cancel">删除</span>' +
                '<span class="rotateRight">向右旋转</span>' +
                '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find('p.imgWrap'),
            $info = $('<p class="error"></p>'),

            showError = function (code) {
                switch (code) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        break;

                    case 'interrupt':
                        text = '上传暂停';
                        break;

                    case 'server':
                        text = '服务器错误';
                        break;

                    default:
                        text = '上传失败，请重试';
                        break;
                }

                $info.text(text).appendTo($li);
            };

        if (file.getStatus() === 'invalid') {
            showError(file.statusText);
        } else {
            // @todo lazyload
            $wrap.text('预览中');
            webUploader.uploader.makeThumb(file, function (error, src) {
                if (error) {
                    if (webUploader.isfile) {
                        var fl = $('<img src="/scripts/plugins/webuploader/images/update.png" style="width: 60px; height: 60px; margin: 60px;">');
                        $wrap.empty().append(fl);
                        return;
                    }
                    $wrap.text('不能预览');
                    return;
                }

                var img = $('<img src="' + src + '">');
                $wrap.empty().append(img);
            }, webUploader.thumbnailWidth, webUploader.thumbnailHeight);
            webUploader.percentages[file.id] = [file.size, 0];
            file.rotation = 0;
        }

        file.on('statuschange', function (cur, prev) {
            if (prev === 'progress') {
                $prgress.hide().width(0);
            } else if (prev === 'queued') {
                $li.off('mouseenter mouseleave');
                $btns.remove();
            }

            // 成功
            if (cur === 'error' || cur === 'invalid') {
                console.log(file.statusText);
                showError(file.statusText);
                webUploader.percentages[file.id][1] = 1;
            } else if (cur === 'interrupt') {
                showError('interrupt');
            } else if (cur === 'queued') {
                webUploader.percentages[file.id][1] = 0;
            } else if (cur === 'progress') {
                $info.remove();
                $prgress.css('display', 'block');
            } else if (cur === 'complete') {
                $li.append('<span class="success"></span>');

                //[新增]  增加删除按钮
                $btns = $('<div class="file-panel">' + '<span class="cancel">删除</span></div>').appendTo($li);
                $wrap = $li.find('p.imgWrap');
                $li.on('mouseenter', function () {
                    $(this).find(".file-panel").stop().animate({ height: 30 });
                });

                $li.on('mouseleave', function () {
                    $(this).find(".file-panel").stop().animate({ height: 0 });
                });

                $btns.on('click', 'span', function () {
                    var index = $(this).index();
                    switch (index) {
                        case 0:
                            webUploader.uploader.removeFile(file, true);
                            return;
                    }
                });
            }

            $li.removeClass('state-' + prev).addClass('state-' + cur);
        });

        $li.on('mouseenter', function () {
            $btns.stop().animate({ height: 30 });
        });

        $li.on('mouseleave', function () {
            $btns.stop().animate({ height: 0 });
        });

        $btns.on('click', 'span', function () {
            var index = $(this).index(),
                deg;

            switch (index) {
                case 0:
                    uploader.removeFile(file);
                    return;

                case 1:
                    file.rotation += 90;
                    break;

                case 2:
                    file.rotation -= 90;
                    break;
            }

            if (webUploader.supportTransition) {
                deg = 'rotate(' + file.rotation + 'deg)';
                $wrap.css({
                    '-webkit-transform': deg,
                    '-mos-transform': deg,
                    '-o-transform': deg,
                    'transform': deg
                });
            } else {
                $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                // use jquery animate to rotation
                // $({
                //     rotation: rotation
                // }).animate({
                //     rotation: file.rotation
                // }, {
                //     easing: 'linear',
                //     step: function( now ) {
                //         now = now * Math.PI / 180;

                //         var cos = Math.cos( now ),
                //             sin = Math.sin( now );

                //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                //     }
                // });
            }


        });

        $li.appendTo(webUploader.$queue);
    }

    webUploader.removeFile = function (file) {
        var $li = $('#' + file.id);

        delete webUploader.percentages[file.id];
        webUploader.updateTotalProgress();
        $li.off().find('.file-panel').off().end().remove();
    }

    webUploader.$upload.addClass('state-' + webUploader.state);
    webUploader.updateTotalProgress();

    if (option.datas) {
        data = typeof (JSON) == 'undefined' ? eval("[" + option.datas + "]")[0] : JSON.parse(option.datas);
        if (data.length == 0) return;
        var $li, $btns, $wrap;
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            var imageurl = "";
            var filename = item.FileNewName.replace(/\\/g, "\/");;
            imageurl = webUploader.imageurl + filename;
            $li = $('<li id="' + item.ID + '" class="state-complete">' +
                '<p class="title">' + item.FileName + '</p>' +
                '<p class="imgWrap"><img src="' + imageurl + '" style="width:' + webUploader.thumbnailWidth + 'px;heigth:' + webUploader.thumbnailHeight + 'px;"></p>' +
                '<p class="progress"><span></span></p>' +
                '<input type="hidden" id="FileName" name="FileName" value="' + item.FileName + '" />' +
                '<input type="hidden" id="FileNewName" name="FileNewName" value="' + item.FileNewName + '" />' +
                '<input type="hidden" id="FileType" name="FileType" value="' + item.FileType + '" />' +
                '<input type="hidden" id="FileSize" name="FileSize" value="' + item.FileSize + '" />' +
                '<input type="hidden" id="FileAttachmnetType" name="FileAttachmnetType" value="' + item.RelationType + '" />' +
                '</li>');

            $btns = $('<div class="file-panel">' +
                  '<span class="cancel">删除</span></div>').appendTo($li);

            $wrap = $li.find('p.imgWrap');

            $li.on('mouseenter', function () {
                $(this).find(".file-panel").stop().animate({ height: 30 });
            });

            $li.on('mouseleave', function () {
                $(this).find(".file-panel").stop().animate({ height: 0 });
            });

            $btns.on('click', 'span', function () {
                var index = $(this).index(),
                    deg;

                var rotation = 0;
                switch (index) {
                    case 0:
                        $(this).closest("li").remove();
                        return;

                    case 1:
                        rotation += 90;
                        break;

                    case 2:
                        rotation -= 90;
                        break;
                }

                if (webUploader.supportTransition) {
                    deg = 'rotate(' + rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((rotation / 90) % 4 + 4) % 4) + ')');
                }


            });

            webUploader.$queue.append($li);

            webUploader.fileCount++;
            webUploader.fileSize += item.FileSize;
        }

        if (webUploader.fileCount > 0) {
            webUploader.$placeHolder.addClass('element-invisible');
            webUploader.$statusBar.show();
        }

        webUploader.setState('init');
    }
}
