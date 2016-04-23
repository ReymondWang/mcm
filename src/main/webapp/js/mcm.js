var parentFakeLoader = $(window.parent.document).find("#fakeLoader");
if (parentFakeLoader){
	parentFakeLoader.fadeOut();
}

function loadSubUrl(url){
	var loader = $("#fakeLoader");
	if (loader){
		loader.fadeIn();
		$("#fakeLoader").fakeLoader({
			timeToHide: 1000000,
			bgColor: "rgba(0, 0, 0, 0.4)",
			spinner: "spinner2"
        });
	}
	$(window.location).attr("href", url);
}

function loadModeView(url){
	var loader = $("#fakeLoader");
	if (loader){
		loader.fadeIn();
		
		$.ajax({
			url: url,
			method: "post",
			async: true,
			beforeSend: function(){
				$("#fakeLoader").fakeLoader({
					timeToHide: "1000000",
					bgColor: "rgba(0, 0, 0, 0.4)",
					spinner: "spinner2"
	            });
			},
			success: function(data){
				$("#fakeLoader").html(data);
				centerLoadInModeView($("#fakeLoader"));
			},
			error: function(err){
				alert(err);
			}
		});
	}
}

function centerLoadInModeView(obj){
	var winW = $(obj).width();
	var winH = $(obj).height();
	
	var content = $(obj).find("#modeView");
	if (content){
		var spinnerW = content.outerWidth();
        var spinnerH = content.outerHeight();

        content.css({
            'position':'absolute',
            'left':(winW/2)-(spinnerW/2),
            'top':((winH/2)-(spinnerH/2)) * 0.6
        });
	}
}

function closeModeView(){
	$("#fakeLoader").html("");
	$("#fakeLoader").fadeOut();
}

//弹出消息
function alertMcmMsg(obj, msg){
	var winW = $(window).width();
	var winH = $(window).height();
	
    var spinnerW = $(obj).outerWidth();
    var spinnerH = $(obj).outerHeight();

    $(obj).css({
        'position':'absolute',
        'left':(winW/2)-(spinnerW/2),
        'top':((winH/2)-(spinnerH/2)) * 0.6
    });
    
    $(obj).find("#MsgContent").html(msg);
    $(obj).fadeIn();
    setTimeout(function(){
		$(obj).fadeOut();
	}, 3000);
}

function alertMcmConfirmMsg(obj, msg){
	var winW = $(window).width();
	var winH = $(window).height();
	
    var spinnerW = $(obj).outerWidth();
    var spinnerH = $(obj).outerHeight();

    $(obj).css({
        'position':'absolute',
        'left':(winW/2)-(spinnerW/2),
        'top':((winH/2)-(spinnerH/2)) * 0.6
    });
    
    $(obj).find("#MsgContent").html(msg);
    $(obj).fadeIn();
}

function dismissMsg(obj){
	$(obj).fadeOut();
}