$(function(){
	//绑定查询用户的按钮
	$("#queryBtn").on("click",function(){
		checkInput();
	});
	$(".close").click(function(){
	    $(".hint").removeClass("zoomIn");
	    $(".infobox").hide();
	  });
	//取消解绑
	 $(".confirm").click(function(){
		    $(".hint").removeClass("zoomIn");
		    $(".infobox").hide();
		    doUnbind();
		  });
	 //确认解绑
	 
	 _.templateSettings = {
			    evaluate: /\{\{(.+?)\}\}/g,
			    interpolate: /\{\{=(.+?)\}\}/g,
			    escape: /\{\{-(.+?)\}\}/g
			  };
	 
     $('#queryBtn').bind('keypress',function(event){

         if(event.keyCode == "13")   

         {

             alert('你输入的内容为：');

         }

     });
})
//检查用户输入的手机号是否合法
function checkInput(){
	getUserInfo();
}
//检测用户的输入
function checkInput(){
	
	var phone = $("#phone").val();
	var input = trim(phone);
    var flag = isEmpty(input);
    if(flag){
    	alert("手机号不能为空");
    	return;
    }else{
    	var phoneReg = /^0?(13|15|18|14|17)[0-9]{9}$/;
    	if(phoneReg.test(phone)){
    		getUserInfo();
    	}else{
    		alert("请填写正确的手机号");
    		return;
    	}
    }
}
//异步查询用户的信息
function getUserInfo(){
	var data = {'phone': $('#phone').val()};
	$.ajax({
		url: $("#projectURL").val() +'/user/userInfo',//提交的URL
		data:data,
		dataType: 'json',
		type: 'POST',
		success: function(data){
			if(data.success == false){
				 //清除原有元素
				$(".result").remove();
				$(".noResult").remove();
				alert(data.msg);
				return;
			}
			else{ 
				 //清除原有元素
				$(".result").remove();
				$(".noResult").remove();
				 //展示查询结果
				loadData(data);
			}
		},
		error: function(){
			alert('订单提交异常');
		}
    });
}

//填充数据
function loadData(res){
	var detailT = $('#templateId').html();
	var htm = _.template(detailT)({userModel: res.userModel});
	$('#sysList').after(htm);
	//绑定查询用户的按钮
	$("#unbindCad").on("click",function(){
		
		confirmUnbind();
	});
}
//解绑银行卡
function doUnbind(){
	
var data = {'phone': $('#userPhone').html(),'cardNo':$('#cardNo').html()};
$.ajax({
	url: $("#projectURL").val() +'/user/unbind',//提交的URL
	data:data,
	dataType: 'json',
	type: 'POST',
	success: function(data){
		if(data.success == false){
			alert(data.msg);
			return;
		}
		else{ 
			 //展示查询结果
			$.messager.alert("提示","解绑成功！");
			//移除银行卡信息
			$(".cardInfo").remove();
			
		}
	},
	error: function(){
		alert('解绑失败');
	}
});
}
//确实是否解绑
function confirmUnbind(){
	$(".infobox").show();
}