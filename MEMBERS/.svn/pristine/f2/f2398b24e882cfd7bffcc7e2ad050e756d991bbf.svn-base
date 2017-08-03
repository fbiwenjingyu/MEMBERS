$(function(){
	//绑定查询用户的按钮
	$("#authBtn").on("click",function(){
		checkInput();
	});
	
})

//检测用户的输入
function checkInput(){
	
/*	var phone = $("#phone").val();
	var name = trim($("#name").val());
	var input = trim(phone);
    var flag = isEmpty(input);
    if(flag){
    	 $(".tip").html("<font color='red'>手机号不能为空</font>");
    	return false;
    }else{
    	var phoneReg = /^0?(13|15|18|14|17)[0-9]{9}$/;
    	if(!phoneReg.test(phone)){
    		 $(".tip").html("<font color='red'>请填写正确的手机号</font>");
    		return false;
    	}
    }
    if(isEmpty(name)==true){
    	$(".tip").html("<font color='red'>姓名不能为空</font>");
    	return false;
    }*/
    return true;
}
// 异步提交请求，授权用户可以购买
function authUser(){
	
	$.ajax({
		url: $("#projectURL").val() +'/user/userAuth',//提交的URL
		data:{"phone":trim($('#phone').val()),"name":trim($('#name').val())},
		dataType: 'json',
		type: 'POST',
		success: function(data){
			alert("success");
			if(data.success == false){
				alert(data.msg);
				return;
			}
			else{ 
				 //展示查询结果
				loadData(data);
			}
		},
		error: function(){
			alert('授权异常');
		}
    });
}

//取消授权
function cancleAuth(phone){
	
	var tip = '是否确认取消授权';
	$.messager.confirm('提示:',tip,function(e){ 
    if(e){
	$.ajax({
		url: 'cancelAuth',//提交的URL
		data:{"recommendPhone":trim(phone+'')},
		dataType: 'json',
		type: 'POST',
		success: function(data){
			if(data.success == false){
				$.messager.alert('提示:',data.msg);
				return;
			}
			else{ 
				var win=art.dialog.open.origin; 
				win.showTip("1");
				art.dialog.close();
			}
		},
		error: function(){
			$.messager.alert('提示:','取消授权异常');
		}
    });
   }
	});
}