$().ready(function() {
	validateRule();
    $("[data-toggle=popover]").popover();
});


function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 200) {
				parent.layer.msg(data.data);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		},
        submitHandler:function(){
            save();
        }
    })
}

var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}

function btnUserTypeChange(e) {
    var selval = e.options[e.options.selectedIndex].value;
    var seltxt = e.options[e.options.selectedIndex].text;
    var strurl = $("#bntCupboard").attr("url");
    if (selval == 1 && strurl.indexOf("userType=0") > -1) {
        strurl = strurl.replace("userType=0", "userType=1");
    }
    if (selval == 0 && strurl.indexOf("userType=1") > -1) {
        strurl = strurl.replace("userType=1", "userType=0");
    }
    $("#bntCupboard").attr("url", strurl);
}