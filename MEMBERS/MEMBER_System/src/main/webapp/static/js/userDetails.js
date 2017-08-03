$(function(){
	//绑定查询用户的按钮
	$("#queryBtn").on("click",function(){
		checkInput();
	});
	//模板
    _.templateSettings = {  
            evaluate : /\{%([\s\S]+?)\%\}/g,  
            interpolate : /\{%=([\s\S]+?)\%\}/g,  
            escape : /\{%-([\s\S]+?)%\}/g  
        }
	 //初始化
	 var userId = $('#userId').val();
	 getUserInfo(userId);
})



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
function getUserInfo(userId){
	var data = {'phone': $('#phone').val(), 'userId' : userId};
	$.ajax({
		url: $("#projectURL").val() +'/user/userInfo',//提交的URL
		data:data,
		dataType: 'json',
		type: 'POST',
		success: function(data){
			//展示查询结果
			loadData(data);
		},
		error: function(){
			alert('异步查询用户详情异常');
		}
    });
}

//填充数据
function loadData(model){
	var detailT = $('#templateId').html();
	var htm = _.template(detailT)({userModel: model});
	$('#toolbar').after(htm);
	
}
