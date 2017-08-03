//var isSuf = false;//余额是否足够
//var resultCode="";//检查余额是否足够返回状态码
//var resultMes="";//检查余额是否足够返回描述
//$(function(){
//
//	//选择支付方式后检查余额
//	$('#payWayModal').on('okHide', function(e){
//		isSuf=checkAcount();
//	    if(isSuf){
//	      $('#payModal').modal('show');
//	    }else{//校验不通过
//	      $('#payNoModal .resultMesg').html(resultMes);	
//	      $('#payNoModal').modal('show');
//	    }
//	});
//	$('#payNoModal').on('okHide', function(e){
//		if(resultCode=='200'){
//		    location.href = ctx+"recharge/toAdd";
//		}else if(resultCode=='201'){
//		    location.href = ctx+"recharge/toAdd";
//		}else if(resultCode=='202'){
//		    location.href = ctx+"assetes/gotoTransform";
//		}
//	});
//});
//
////点击确认，弹出购买方式
//$("#tForm").validate({
//	success: function() {
//		$('#payWayModal').modal('toggle');
//	}
//}); 
//
//function checkAcount(){
//	
//	var type=flag=null;
//	if($("#radio1").hasClass("checked")){
//		type=1;
//	}
//	if($("#radio2").hasClass("checked")){
//		type=2;
//	}
//	if($("#radio3").hasClass("checked")){
//		type=3;
//	}
//	$("#type").val(type);//支付类型
//	$("#buyNum").val($('#num').val());//购买数量
//	$.ajax({
//		url:'checkAccountEnough',
//		dataType:'json',
//		async: false,
//		data:{'type': type, 'num':parseInt($('#num').val())},
//		success:function(data){
//			if(data){
//				resultCode=data.code;
//				resultMes=data.msg;
//				if(data.code=='100'){
//					flag = true;
//				}else{
//					flag = false;
//				}
//				
//			}else{
//				alert("服务器无返回结果！");
//			}
//		},
//		error:function(req,err,ecp){
//			alert(data.msg);
//		}
//	});
//	return flag;
//	
//}