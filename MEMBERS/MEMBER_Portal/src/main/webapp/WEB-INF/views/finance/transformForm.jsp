<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
 	<jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
    
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">转换</h4>
            <form action="<c:url value = '/assetes/doTransform' />" class="sui-form form-horizontal sui-validate" id="tForm" method="POST">
              <input type="text" style="display:none">
              <input type="password" style="display:none">
              <div class="control-group">
                <label for="" class="control-label"><b>*</b>转换类型</label>
                <div class="controls">
                	 <select name="convertType" id="convertType">
                      <option value="1">博豆转积分</option>
                      <option value="2">现金币转博豆</option>
                    </select>
                </div>
              </div>
              <div class="control-group">
                <label for="tit" class="control-label" id="titMes"><b>*</b>需转博豆</label>
                <div class="controls">
                  <input type="text" id="tit" placeholder="请输入正整数" data-rules="required|digits|gt=0|lt=7001" data-error-msg="请输入1到7000之间的正整数" name="tit" autocomplete="off">
                </div>
              </div>
              <div class="control-group">
                <label for="password" class="control-label"><b>*</b>支付密码</label>
                <div class="controls">
                  <input type="password" id="password" placeholder="请输入支付密码" data-rules="required|minlength=6|maxlength=24" name="password" autocomplete="off">
                </div>
              </div>
              <div class="control-group sui-inf" id="toolTil">转换类型为博豆转积分，且不可逆，1博豆=1积分</div>
              <div class="control-group sui-inf">
              	可用现金币<b style="color:red;" id="balance"><fmt:formatNumber value="${user.balance/100}" type="currency" pattern="0.00"/>元</b>&nbsp;&nbsp;&nbsp;
              	可用博豆<b style="color:red;" id="bodou">${user.shoppingScore }个</b>
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn btn-primary">确定</button>
                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/assetes/toAssetes?flag=2' />'" >返回</button>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>
<div class="sui-msg msg-large msg-error" id="result" style="display:none;z-index:100;position:absolute;left:50%;top:50%;width:auto;height:46px;margin:-23px 0 0 -65px;">
  	<div class="msg-con" style="padding-left: 50px;  background-color: #EEE;">博豆数不足，请重新输入</div>
 	 <s class="msg-icon"></s>
 </div>
<script type="text/javascript">
	
$("#tForm").validate({
	success: function() {
     	var bd=parseInt($("#bodou").html());
     	var xj=parseFloat($("#balance").html());
     	var val=parseInt($("#tit").val());
     	var t=$("#convertType").val();
     	if(t==1){
     		if(val>bd){
    			$("#result .msg-con").html("博豆数不足，请重新输入");
    			$("#result").fadeIn();
    			setTimeout(function(){$("#result").fadeOut();},2000);
    			
    			$("#tit").val("").focus();
    		    return false;
         	}
     	}else if(t==2){
     		if(val>xj){
    			$("#result .msg-con").html("现金币不足，请重新输入");
    			$("#result").fadeIn();
    			setTimeout(function(){$("#result").fadeOut();},2000);
    			
    			$("#tit").val("").focus();
    		    return false;
         	}
     	}
     	
     	$("#tForm").submit();
	}
  });
  
$(function(){
	
	$("#convertType").change(function(){
		var val=$(this).val();
		if(val == 1){
			$("#titMes").html("<b>*</b>需转博豆");
			$("#toolTil").html("转换类型为博豆转积分，且不可逆，1博豆=1积分");
		}else if(val == 2){
			$("#titMes").html("<b>*</b>需转现金");
			$("#toolTil").html("转换类型为现金币转博豆，且不可逆，1现金币=1博豆");
		}
		
	});
	
	$("#tit").blur(function(){
		var val=$.trim($(this).val());
		if(val && (val.indexOf("0") == 0)){
			$(this).val(parseInt(val));
			return ;	
		}
		
	});
	
});

</script>
</body>
</html>