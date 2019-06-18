var rowData; // 记录当前选中行对象（在tableEvent中赋值，通过getSelectRowData()返回）
function dataTableInit(option) {
    var pageSize = 8;
    var height = screen.height;
    // 1280 * 1024每页20条，其他更高分辨率待扩展
    if (height > 1000) {
        pageSize = 10;
    }
    var id = option.ID;
    var url = option.sAjaxSource;   //mvc后台ajax调用接口。
    url = url.charAt(0) == "/" ? url : "/" + url;
    var paginate = option.bPaginate == false ? false : true; //未指定表示true，下同
    var lengthChange = option.bLengthChange == false ? false : true;
    var sorting = option.aaSorting ? option.aaSorting : [];
    var columns = option.aoColumns;
    var params = option.fnServerParams;
    var single = option.bSingle == true ? true : false; //是否单选，未指定代表多选
    var iDisplayLength = option.iDisplayLength ? option.iDisplayLength : pageSize;
    var fnDrawCallback = option.fnDrawCallback ? function () { option.fnDrawCallback.call(this, single) } : function () { tableEvent(single); };
    var fnRowCallback = option.fnRowCallback;
    var info = option.sInfo ? option.sInfo : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录";
    var sDom = option.sDom ? option.sDom : 'frtlpi';
    var sGroups = option.sGroups;
    var bAutoWidth = option.bAutoWidth == true ? true : false;

    if (id == null || id == "") alert("必须指定表格名称");

    var tbl = $(id).dataTable({
        "colReorder": true,
        "bServerSide": true,
        "sAjaxSource": url,
        "fnServerParams": function (aoData) {
            if (params != null) {
                $.each(params, function () {
                    aoData.push($(this)[0]);
                });
            }
        },
        "fnServerData": function (sSource, aoData, fnCallback) {
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": sSource,
                "data": aoData,
                "error": function (xhr, error, thrown) {
                    layer.alert('数据载入失败');
                },
                "success": function (json) {
                    if (fnRowCallback) {
                        json = fnRowCallback.call(this, json);
                    }
                    if (typeof (json.data.aaData) != "object") {
                        json.data.aaData = jQuery.parseJSON(json.data.aaData);
                    }
                    fnCallback(json.data);
                    setTitle();
                }
            });
        },
        "fnDrawCallback": fnDrawCallback,
        'bPaginate': paginate,                                      //是否分页。
        "bProcessing": true,                                        //当datatable获取数据时候是否显示正在处理提示信息。
        'bFilter': false,                                               //是否使用内置的过滤功能。
        'bLengthChange': lengthChange,                  //是否允许用户自定义每页显示条数。
        'sPaginationType': 'full_numbers',                 //分页样式
        'iDisplayLength': iDisplayLength,
        'sDom': sDom,
        "oLanguage": {
            "sLengthMenu": "_MENU_",
            "sZeroRecords": '<i class="iconfont icon-tishi"></i> <div class="inline-block">没有查询到符合条件的记录</div>',//"<i class='iconfont icon-tishi'></i> <span>没有查询到符合条件的记录</span>",
            "sInfo": info,
            "sInfoEmpty": "",
            "sInfoFiltered": "", //"数据表中共为 _MAX_ 条记录",
            "sProcessing": "正在加载中...",
            "sSearch": "搜索",
            //"sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
            "oPaginate": {
                "sFirst": " 首页 ",
                "sPrevious": " 前页 ",
                "sNext": " 后页 ",
                "sLast": " 尾页 "
            }
        }, //多语言配置
        "aLengthMenu": [[5, 8, 10, 15, 20, 50], ["显示5条", "显示8条", "显示10条", "显示15条", "显示20条", "显示50条"]],
        "aaSorting": sorting,
        "aoColumns": columns,
        //"scrollX": bAutoWidth,
        "bAutoWidth": false//, //不自动计算列宽度  
        //"dom": 'T<"clear">lfrtip',
        //"tableTools": {
        //    "aButtons": [
        //        {
        //            "sExtends": "copy",
        //            "sButtonText": "Copy to clipboard"
        //        },
        //        {
        //            "sExtends": "csv",
        //            "sButtonText": "Save to CSV"
        //        },
        //        {
        //            "sExtends": "xls",
        //            "oSelectorOpts": {
        //                page: 'current'
        //            }
        //        }
        //    ]
        //}
    });

    if (sGroups) {
        chooseGroupColumn(tbl, columns, sGroups);
    } else {
        chooseColumn(tbl, columns);
    }

    if (bAutoWidth) {
        tbl.colWidth = getWidth(columns);
        setWidth(id, tbl.colWidth);
    }

    initChooseCheck(id, tbl, bAutoWidth);

    return tbl;
}

function chooseGroupColumn(tbl, columns, sGroups) {
    if ($("#ulcols").length > 0) {
        $("#ulcols").addClass("agile-list");
        var ul = "";
        for (var i = 0; i < sGroups.length; i++) {
            ul += '<li class="success-element"><label class="checkbox-inline i-checks"><input type="checkbox" data-index="' + i + '" name="chkChooseColumnAll' + i + '"> <span class="text-danger">' + sGroups[i].Name + '</span></label>';
            var cols = sGroups[i].Columns.split(',');
            var igroup = parseInt(cols.length / 4) + 1;
            for (var j = 0; j < igroup; j++) {//分组
                ul += '<div class="agile-detail">';
                for (var k = 0; k < 4; k++) {//4个查询条件
                    var index = j * 4 + k;
                    if (index < columns.length && index < cols.length) {
                        var code = cols[index];
                        for (var x = 0; x < columns.length; x++) {
                            var column = columns[x];
                            if (code == column.mData) {
                                var sChecked = column.bVisible == false ? "" : "checked='checked'";
                                var width = column.sWidth == undefined || column.sWidth == null ? 140 : column.sWidth;
                                ul += '<label class="checkbox-inline i-checks"><input type="checkbox" name="chkChooseColumnItem' + i + '" sWidth="' + width + '" value="' + x + '" ' + sChecked + '> ' + column.sTitle + '</label>';
                                break;
                            }
                        }
                    }
                }
                ul += '</div>';
            }
            ul += '</li>';
        }
        $("#ulcols").html(ul);
    }
}

function chooseColumn(tbl, columns) {
    if ($("#ulcols").length > 0) {
        var ul = '<li><div class="checkbox i-checks"><label><input type="checkbox" name="chkChooseColumnAll"> 全选 / 反选 </label></div></li><li class="divider"></li>';
        for (var i = 0; i < columns.length; i++) {
            if (columns[i].mData != null) {
                var sChecked = columns[i].bVisible == false ? "" : "checked='checked'";
                var width = columns[i].sWidth == undefined || columns[i].sWidth == null ? 140 : columns[i].sWidth;
                ul += '<li><div class="checkbox i-checks"><label><input name="chkChooseColumnItem" type="checkbox" sWidth="' + width + '" value="' + i + '" ' + sChecked + '> ' + columns[i].sTitle + '</label></div></li>';
            }
        }
        $("#ulcols").html(ul);
    }
}

function getWidth(columns) {
    var width = 0;
    for (var i = 0; i < columns.length; i++) {
        if (columns[i].bVisible) {
            if (columns[i].sWidth != undefined && columns[i].sWidth != undefined != null) {
                width += parseFloat(columns[i].sWidth);
            } else {
                width += 100;
            }
        }
    }
    return width;
}

function setWidth(id, width) {
    if (width > window.screen.width - 220) {
        $(id).css({ "width": width + "px" });
    } else {
        $(id).css({ "width": "" });
    }
}

function initChooseCheck(id, tbl, bAutoWidth) {
    if ($("#ulcols").length > 0) {
        $('input').on('ifChanged', function (event) {
            var col = $(event.currentTarget);
            if (col.attr("name") == "chkChooseColumnAll") {
                if (this.checked) {
                    $('input[name="chkChooseColumnItem"]').iCheck('check');
                } else {
                    $('input[name="chkChooseColumnItem"]').iCheck('uncheck');
                }
            } else if (col.attr("data-index") != undefined && col.attr("data-index") != null) {
                var index = col.attr("data-index");
                if (this.checked) {
                    $('input[name="chkChooseColumnItem' + index + '"]').iCheck('check');
                } else {
                    $('input[name="chkChooseColumnItem' + index + '"]').iCheck('uncheck');
                }
            }
            else {
                tbl.fnSetColumnVis(col.val(), event.currentTarget.checked);
                if (bAutoWidth) {
                    var width = parseFloat(col.attr("sWidth"));
                    if (event.currentTarget.checked) {
                        tbl.colWidth += width;
                    } else {
                        tbl.colWidth -= width;
                    }
                    setWidth(id, tbl.colWidth);
                }
            }
        });
        $(".i-checks").iCheck({ checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green", });
    }
}

function setTitle() {
    var self = $(".dataTables_wrapper .limit");
    self.each(function () {
        var objString = $(this).text();
        $(this).attr("title", objString);

        //$(this).tooltip({
        //    show: null,
        //    position: {
        //        my: "left top",
        //        at: "left bottom"
        //    },
        //    open: function (event, ui) {
        //        ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast");
        //    }
        //});

    });
}


function columnCorpCheckbox(chkAll) {
    var chk_all = (chkAll == false) ? "" : "<input type='checkbox' id='ckbAll' name='ckbAll' />";
    var ckbColumn = {
        "mData": null,
        "sTitle": chk_all,
        "bSortable": false,
        "sClass": "text-center",
        "sWidth": "10px",
        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            var ckb = "<input type='checkbox' id='ckbItem" + iRow + "' name='ckbItem' value='" + $("#EvaluateYear").val() + "_" + oData.CorpID + "'/>";
            $(nTd).html(ckb);
        }

    };
    return ckbColumn;
}


function columnCheckbox(chkAll) {
    var chk_all = (chkAll == false) ? "" : "<input type='checkbox' id='ckbAll' name='ckbAll' />";
    var ckbColumn = {
        "mData": null,
        "sTitle": chk_all,
        "bSortable": false,
        "sClass": "text-center",
        "sWidth": "10px",
        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            var ckb = "<input type='checkbox' id='ckbItem" + iRow + "' name='ckbItem' value='" + oData.id + "'/>";
            $(nTd).html(ckb);
        }

    };
    return ckbColumn;
}

function columnCheckboxByName(name, chkAll) {
    var chk_all = (chkAll == false) ? "" : "<input type='checkbox' id='ckbAll' name='ckbAll' />";
    var ckbColumn = {
        "mData": null,
        "sTitle": chk_all,
        "bSortable": false,
        "sClass": "text-center",
        "sWidth": "10px",
        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            var ckb = "<input type='checkbox' id='ckbItem" + iRow + "' name='ckbItem' value='" + oData[name] + "'/>";
            $(nTd).html(ckb);
        }

    };
    return ckbColumn;
}

function columnSequence() {
    var seqColumn = {
        "mData": null,
        "sTitle": "序号",
        "bSortable": false,
        "sClass": "text-center",
        "sWidth": "25px",
        "render": function (data, type, full, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
        }
    };
    return seqColumn;
}

function columnCommon(name, title, op) {
    var bSortable = (op != null && op.bSortable == false) ? false : true;
    var mRender = (op != null && typeof (op.mRender) == "function") ? op.mRender : null;
    var sWidth = (op != null && op.sWidth != null) ? op.sWidth : null;
    var bVisible = (op != null && op.bVisible != null) ? op.bVisible : true;
    var sClass = (op != null && op.sClass != null) ? op.sClass : "text-center";
    var col = {
        "mData": name,
        "mDataProp": name,
        "sTitle": title,
        "sClass": sClass,
        "sWidth": sWidth,
        "bSortable": bSortable,
        "bVisible": bVisible,
        "mRender": function (data, type, obj) {
            if (mRender != null) {
                return mRender(data, type, obj);
            }
            return obj[name];
        }
    };
    return col;
}

function columnHyperLink(name, title, op) {
    var mRender = (op != null && typeof (op.mRender) == "function") ? op.mRender : null;
    var sWidth = (op != null && op.sWidth != null) ? op.sWidth : null;
    var displayText = (op != null && op.DisplayText != null) ? op.DisplayText : "查看";
    var sClass = (op != null && op.sClass != null) ? op.sClass : "text-center";
    var bVisible = (op != null && op.bVisible != null) ? op.bVisible : true;
    var seqColumn = {
        "sTitle": title,
        "bSortable": false,
        "sClass": sClass,
        "sWidth": sWidth,
        "bVisible": bVisible,
        "mRender": function (data, type, obj) {
            var no = obj.iDataRow + obj.oSettings._iDisplayStart + 1;
            if ((op != null && op.Display != null)) {
                displayText = obj.aData[op.Display];
            }
            var ckb = "<a id='link" + no + "' href='javascript:" + fnRender(obj) + "' style='color: #1E85BE'>" + displayText + "</a>";
            return ckb;
        }
    };
    return seqColumn;
}

function columnOperationLink(obj) {

    if (obj == null || typeof (obj) != "object") {
        return "";
    }
    var ops = "", split = true;;
    for (i = 0; i < obj.length; i++) {
        var o = obj[i];

        if (o.Type == "Detail") {
            var link = detailLink(o.Path, o.Visible, o.Title);
            if (link != "") {
                ops += link;
                split = false;
            }
        } else if (o.Type == "Edit") {
            var link = editLink(o.Path, o.Visible, o.Title);
            if (link != "") {
                ops += link;
                split = false;
            }
        } else if (o.Type == "Fn") {
            var link = fnLink(o.Visible, o.Title, o.Fn, o.Data);
            if (link != "") {
                ops += link;
                split = false;
            }
        }
        if (!split && i + 1 < obj.length && obj[i + 1].Visible && obj[i + 1].Visible.toLowerCase() == "true") {
            ops += splitLink();
        }

    }
    return ops;
}

function splitLink() {
    var link = "<span class='text-explode'>|</span>";
    return link;
}

function detailLink(path, visible, title) {
    if (!visible || visible.toLowerCase() == "false") return "";
    var link = "<a href='javascript:void(0);' url='" + path + "' onclick='layer.operate.open(this);' title='" + title + "'>" + title + "</a> "
    return link;
}

function editLink(path, visible, title) {
    if (!visible || visible.toLowerCase() == "false") return "";
    var link = "<a href='javascript:void(0);' url='" + path + "' onclick='layer.operate.open(this);'  title='" + title + "'>" + title + "</a>";
    return link;
}

function fnLink(visible, title, fn, data) {
    if (!visible || visible.toLowerCase() == "false") return "";
    var link = "<a href='javascript:void(0);' onclick='" + fn + "(" + data + ");'  title='" + title + "'>" + title + "</a>";
    return link;
}






function tableEvent(bSingle) {
    $("#tbDataList tbody tr").hover(function () {
        $(this).addClass("trmouseover");
    }, function () {
        $(this).removeClass("trmouseover");
    }).click(function (e) { // 单击checkbox可多选，单击行则实现单选功能
        e = window.event || e;
        var srcElement = e.srcElement || e.target; // 兼容Firefox(注意参数e必须添加)
        if (srcElement != null && srcElement.type != "checkbox") {
            regUnCheckAll(); // 先取消之前的选中项
            $(this).find("input[name='ckbItem']").each(function () {
                this.checked = true;
                $(this).parent().parent().addClass("selected");
            });
        }
        // 记录当前行的值对象
        rowData = tbl.fnGetData(this);
    }).dblclick(function () {
        var btn = $("button[url*=Detail]");
        if (btn.length > 0) {
            layer.operate.open(btn[0]);
        }
    });

    if (bSingle) {
        regCheckSingle();
    }

    // 单击checkbox事件（不会触发上面的行点击事件）
    checkBoxClicked();
}

// CheckBox全选事件
function regCheckAll() {
    $("#ckbAll").click(function () {
        var checked = this.checked;
        $("input[type='checkbox'][name='ckbItem']").each(function () {
            this.checked = checked;
            if (checked) {
                $(this).parent().parent().addClass("selected");
            } else {
                $(this).parent().parent().removeClass("selected");
            }
        });
    });
}

// 实现CheckBox全部取消事件
function regUnCheckAll() {
    if ($("#ckbAll").length > 0) {
        $("#ckbAll")[0].checked = false;
    }
    $("input[type='checkbox'][name='ckbItem']").each(function () {
        this.checked = false;
        $(this).parent().parent().removeClass("selected");
    });
}

// 实现单选功能
function regCheckSingle() {
    var ckbs = $("input[type='checkbox'][name='ckbItem']");
    ckbs.click(function () {
        var chked = this.checked;
        if (chked) {
            ckbs.each(function () {
                this.checked = false;
                $(this).parent().parent().removeClass("selected");
            });
            this.checked = true;
            $(this).parent().parent().addClass("selected");
        }
    });
}

// 单击checkbox事件
function checkBoxClicked() {
    $("input[type='checkbox'][name='ckbItem']").each(function () {
        $(this).click(function () {
            var checked = this.checked;
            if (checked) {
                $(this).parent().parent().addClass("selected");
            } else {
                $(this).parent().parent().removeClass("selected");
            }
        })
    });
}

function getSelectRowIds() {
    var reVal = '';
    $("input[type='checkbox'][name='ckbItem']").each(function () {
        if (this.checked) {
            reVal += $(this).val() + ",";
        }
    });
    reVal = reVal.substr(0, reVal.length - 1);
    return reVal;
}

// 获取jquery.datatables选中的行的值
function getSelectRows(tbl) {
    var selectedRows = new Array();
    var rows = tbl.fnGetNodes();

    for (var i = 0; i < rows.length; i++) {
        if ($(rows[i]).find(":checkbox")[0].checked) {
            selectedRows.push(tbl.fnGetData(rows[i]));
        }
    }
    return selectedRows;
}

// 返回当前选中行的值对象
function getSelectedRowData() {
    return rowData;
}

function prepareFilterParam(id) {
    var el = id ? $("#" + id) : $(".ibox-search");
    var searchParams = [];
    el.find("input").each(function () {
        if ($(this).attr("type") == "text" || $(this).attr("type") == "hidden") {
            var obj = { "name": $(this).attr('id'), "value": $(this).val() };
            searchParams.push(obj);
        }
    });

    el.find("select").each(function () {
        var obj = { "name": $(this).attr('id'), "value": $(this).val() };
        searchParams.push(obj);
    });

    return searchParams;
}

function filterUrlParam(id) {
    var el = id ? $("#" + id) : $(".ibox-search");
    var searchParams = "";
    el.find("input").each(function () {
        if ($(this).attr("type") == "text" || $(this).attr("type") == "hidden") {
            if ($(this).attr('id') != undefined)
                searchParams += $(this).attr('id') + "=" + $(this).val() + "&";
        }
    });

    el.find("select").each(function () {

        if ($(this).attr('id') != undefined)
            searchParams += $(this).attr('id') + "=" + $(this).val() + "&";
    });

    return searchParams;
}