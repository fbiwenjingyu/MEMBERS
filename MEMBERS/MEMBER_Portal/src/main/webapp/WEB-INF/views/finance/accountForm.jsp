<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>分销管理平台</title>
	<script type="text/javascript">
		$(function() {
			$("#sub").attr("disabled",false);
			var typeMap = ${transferType};
			for ( var type in typeMap) {
				if (typeMap[type].value == "1") {
					$("#type").append(
							"<option value='" + typeMap[type].value + "' selected='selected'> "
									+ typeMap[type].text + "</option>");
				} else {
					$("#type").append(
							"<option value='" + typeMap[type].value + "'> "
									+ typeMap[type].text + "</option>");
				}
			}
		});
	</script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
    <jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="5" name="on"/>
 	</jsp:include>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">转账</h4>
            <form onsubmit="return false;" id="form1" class="sui-form form-horizontal sui-validate">
            <input type="text" style="display:none">
            			<input type="password" style="display:none">
              <div class="control-group">
                <label for="accoutType" class="control-label"><b>*</b>账户类型</label>
                <div class="controls">
                  <select name="type" id="type" data-rules="required">
                  	
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label for="account" class="control-label"><b>*</b>转入账号</label>
                <div class="controls">
                  <input type="text" id="receivePhone" placeholder="请输入转入账号" data-rules="required|checkMember" name="receivePhone">
                </div>
              </div>
              <div class="control-group">
                <label for="money" class="control-label"><b>*</b>转账金额</label>
                <div class="controls">
                  <input type="text" id="transferAmount" placeholder="请输入转账金额" data-rules="required|digits|gt=99|lt=1000000000000000|checkAccount" name="transferAmount">
                </div>
              </div>
              <div class="control-group sui-inf">转账金额不小于100</div>
              <div class="control-group">
                <label for="password" class="control-label"><b>*</b>支付密码</label>
                <div class="controls">
                  <input type="password" id="password" placeholder="请输入支付密码" data-rules="required|minlength=6|maxlength=24" name="password" autocomplete="off">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button id="sub" type="submit" class="sui-btn btn-primary">确定</button>
                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/transfer/toTransfer' />'">返回</button>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>

<script type="text/javascript">
$("#form1").validate({
	success : function() {
		submitform();
		return false;
	}
});

function submitform(){
	$("#sub").attr("disabled","disabled");
		$.ajax({
			url:'add',
			dataType:'json',
			data:$("#form1").serialize(),
			success:function(data){
				if(data.success){
					window.location.href='success';
				}else{
					window.location.href='fail?msg='+data.msg;
				}
			},
			error:function(req,err,ecp){
				window.location.href='fail?msg='+"系统异常";
			}
		});
	}

var match = function(value, param){
		var v = $.trim(value);
	if(v != ''){
		var flag = false;
		$.ajax({
			url:'checkAccount',
			dataType:'json',
			async: false,
			data:$("#form1").serialize(),
			success:function(data){
				flag=data.success;
			}
		});
		return flag;
	}
  }
  
var match1 = function(value, param){
	var v = $.trim(value);
	if(v != ''){
		var flag = false;
		$.ajax({
			url:'checkMember',
			dataType:'json',
			async: false,
			data:$("#form1").serialize(),
			success:function(data){
				flag=data.success;
			}
		});
	return flag;
	}
}
  
$.validate.setRule("checkAccount", match, "余额不足");
$.validate.setRule("checkMember", match1, "该账户不存在");

</script>
</body>
</html>