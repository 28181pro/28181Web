(function ($, toastr) {
	toastr.showWarning = function (title, msg) {
		var option = [];
		option.showType = "warning";
		option.title = title;
		option.msg = msg;
		toastr.showMessage(option);
	}

	toastr.showSuccess = function (title, msg) {
	    var option = [];
	    option.showType = "success";
	    option.title = title;
	    option.msg = msg;
	    toastr.showMessage(option);
	}

	toastr.showError = function (title, msg) {
	    var option = [];
	    option.showType = "error";
	    option.title = title;
	    option.msg = msg;
	    toastr.showMessage(option);
	}

	toastr.showInfo = function (title, msg) {
	    var option = [];
	    option.showType = "info";
	    option.title = title;
	    option.msg = msg;
	    toastr.showMessage(option);
	}

	toastr.showMessage = function (option) {
		var showType = option.showType ? option.showType : "warning";
		var title = option.title ? option.title : "";
		var msg = option.msg ? option.msg : "";
		var closeButton = option.closeButton == false ? false : true;//是否显示关闭按钮
		var progressBar = option.progressBar == false ? false : true;//是否显示进度条
		var positionClass = option.positionClass ? option.positionClass : "toast-bottom-right";//显示位置，默认右下
		var onclick = option.onclick ? option.onclick : null;//单击事件
		var showDuration = option.showDuration ? option.showDuration : "400";//显示持续时间
		var hideDuration = option.hideDuration ? option.hideDuration : "1000";//隐藏持续时间
		var timeOut = option.timeOut ? option.timeOut : "0";//超时
		var extendedTimeOut = option.extendedTimeOut ? option.extendedTimeOut : "1000";//延长时间
		var showEasing = option.showEasing ? option.showEasing : "swing";//显示动画
		var hideEasing = option.hideEasing ? option.hideEasing : "linear";//隐藏动画
		var showMethod = option.showMethod ? option.showMethod : "fadeIn";//显示方法
		var hideMethod = option.hideMethod ? option.hideMethod : "fadeOut";//隐藏方法

		toastr.options = {
			"closeButton": closeButton,
			"debug": false,
			"progressBar": progressBar,
			"positionClass": positionClass,
			"onclick": onclick,
			"showDuration": showDuration,
			"hideDuration": hideDuration,
			"timeOut": timeOut,
			"extendedTimeOut": extendedTimeOut,
			"showEasing": showEasing,
			"hideEasing": hideEasing,
			"showMethod": showMethod,
			"hideMethod": hideMethod
		};
		toastr[showType](msg, title);
	}

})(window.jQuery, toastr)
