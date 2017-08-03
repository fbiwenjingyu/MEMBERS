<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
</head>
<body id="vip">
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
    <jsp:include page="../memu.jsp">
 		<jsp:param value="3" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="6" name="on"/>
 	</jsp:include>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">再次购买</h4>
            <form onsubmit="return false;"  class="sui-form form-horizontal sui-validate" id="tForm" method="POST">
              <input type="text" style="display: none;">
              <div class="control-group">
                <label for="num" class="control-label"><b>*</b>购买数量</label>
                <div class="controls">
                  <input type="text" id="num" placeholder="请输入购买数量" data-rules="required|digits|gt=0|lt=101" data-error-msg="请输入1到100之间的正整数"  autocomplete="off" name="num">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn">确定</button>
                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/memberManage/toBuyAgain' />'">返回</button>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>

<!-- 选择支付方式div -->
<div id="payWayModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>选择购买方式</h4>
    <form action="" method="post" class="sui-form sui-validate">
      <label id="radio1" class="radio-pretty checked">
        <input type="radio" name="radio" value="1"><span>现金币购买，<i class="tAmount1">${rAmount}</i>元</span>
      </label>
      <label id="radio2" class="radio-pretty">
        <input type="radio" name="radio" value="2">
        	<span>现金币+博豆购买，<i class="tAmount"><fmt:formatNumber value="${rAmount*cashScale/100}" type="currency" pattern="#"/></i>元+<i class="tAmount2"><fmt:formatNumber value="${rAmount-rAmount*cashScale/100}" type="currency" pattern="#"/></i>博豆</span>
      </label>
      <label id="radio3" class="radio-pretty">
        <input type="radio" name="radio" value="3">
        	<span>现金币+积分购买，<i class="tAmount"><fmt:formatNumber value="${rAmount*cashScale/100}" type="currency" pattern="#"/></i>元+<i class="tAmount2"><fmt:formatNumber value="${rAmount-rAmount*cashScale/100}" type="currency" pattern="#"/></i>积分</span>
      </label>
      <div class="control-group ">
        <div class="controls">
          <button type="button" data-ok="modal" class="btn btn-ok">确定</button>
          <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>

<!-- 输入支付密码div -->
<div id="payModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>请输入支付密码</h4>
    <form class="sui-form sui-validate" action="doBuyAgain" method="post" id="myDoBuyAgain">
      <div class="control-group ">
        <label for="" class="control-label">
        <i class="star">*</i>支付密码</label>
        <div class="controls">
          <input id="type" name="type" type="hidden">
          <input id="buyNum" name="buyNum" type="hidden">
          <input type="password" style="display:none">
          <input id="password" type="password" name="password" data-rules="required|minlength=6|maxlength=24">
         <span class="help-inline" data-rules="required|minlength=6|maxlength=24">仅支持6到24位字符</span>
           
        </div>
      </div>
      <div class="control-group ">
        <div class="controls">
          	<button type="submit"  class="sui-btn btn-primary" id="myDoBug">确定</button>
            <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
      <p class="error" id="error"></p>
    </form>
  </div>
</div>

<!-- 支付结果提示DIV -->
<div id="payNoModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>温馨提示</h4>
    <form action="" method="post" class="sui-form sui-validate">
      <label class="resultMesg">帐户余额不足，请您先充值</label>
      <div class="control-group ">
        <div class="controls">
          <button type="button" data-ok="modal" class="btn btn-ok">充值</button>
          <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
var isSuf = false;//余额是否足够
var resultCode="";//检查余额是否足够返回状态码
var resultMes="";//检查余额是否足够返回描述
var oldAmount1=oldAmount2=oldAmount=null;
$(function(){
	
	oldAmount=$("#payWayModal i.tAmount").html();
	oldAmount1=$("#payWayModal i.tAmount1").html();
	oldAmount2=$("#payWayModal i.tAmount2").html();
	
	//选择支付方式后检查余额
	$('#payWayModal').on('okHide', function(e){
		isSuf=checkAcount();
	    if(isSuf){
	      $('#payModal').modal('show');
	    }else{//校验不通过
	      $('#payNoModal .resultMesg').html(resultMes);	
	      $('#payNoModal').modal('show');
	    }
	});
	
	//还原购买金额初始值
	$('#payWayModal').on('hidden', function(e){
		$("#payWayModal i.tAmount").html(oldAmount);
		$("#payWayModal i.tAmount1").html(oldAmount1);
		$("#payWayModal i.tAmount2").html(oldAmount2);
		
	});
	
	$('#payNoModal').on('okHide', function(e){
		if(resultCode=='200'){
		    location.href = ctx+"recharge/toAdd";
		}else if(resultCode=='201'){
		    location.href = ctx+"recharge/toAdd";
		}else if(resultCode=='202'){
		    location.href = ctx+"assetes/gotoTransform";
		}
	});
});

//点击确认，弹出购买方式
$("#tForm").validate({
	success: function() {
		$("#payWayModal i.tAmount1").html(parseInt($("#payWayModal i.tAmount1").html())*parseInt($('#num').val()));
		$("#payWayModal i.tAmount2").html(parseInt($("#payWayModal i.tAmount2").html())*parseInt($('#num').val()));
		$("#payWayModal i.tAmount").html(parseInt($("#payWayModal i.tAmount").html())*parseInt($('#num').val()));
		
		$('#payWayModal').modal('toggle');
	}
}); 

//点击确认,提交表单
$("#myDoBuyAgain").validate({
	success: function() {
		$("#myDoBug").attr({'disabled':'disabled'});
		$("#myDoBuyAgain").submit();
	}
}); 


function checkAcount(){
	
	var type=flag=null;
	if($("#radio1").hasClass("checked")){
		type=1;
	}
	if($("#radio2").hasClass("checked")){
		type=2;
	}
	if($("#radio3").hasClass("checked")){
		type=3;
	}
	$("#type").val(type);//支付类型
	$("#buyNum").val($('#num').val());//购买数量
	$.ajax({
		url:'checkAccountEnough',
		dataType:'json',
		async: false,
		data:{'type': type, 'num':parseInt($('#num').val())},
		success:function(data){
			if(data){
				resultCode=data.code;
				resultMes=data.msg;
				if(data.code=='100'){
					flag = true;
				}else{
					flag = false;
				}
				
			}else{
				alert("服务器无返回结果！");
			}
		},
		error:function(req,err,ecp){
			alert(data.msg);
		}
	});
	return flag;
}

</script>
</body>
</html>