<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <%@ include file="/WEB-INF/views/taglibs.jsp" %>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="../memu.jsp">
  	<jsp:param name="on" value="4"></jsp:param>
  </jsp:include>
  <div class="container">
    <jsp:include page="tab.jsp">
   		<jsp:param name="on" value="2"></jsp:param>
    </jsp:include>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">提现</h4>
            <form action="add" class="sui-form form-horizontal sui-validate" method="post">
            <input type="text" style="display:none">
            <input type="password" style="display:none">
              <input type="hidden" name="cardNo" value="${cardNo }">
              <div class="control-group">
                <label for="tit" class="control-label"><b>*</b>提现金额</label>
                <div class="controls">
                  <input type="text" id="amount" placeholder="100" data-rules="required|digits|gt=99|lt=7001" data-error-msg="请输入100到7000之间的正整数" name="amount" onblur="calFee();">
                </div>
              </div>
              <div class="control-group">
                <label for="password" class="control-label"><b>*</b>支付密码</label>
                <div class="controls">
                  <input type="password" id="password" placeholder="请输入支付密码" data-rules="required|minlength=6|maxlength=24" name="password" autocomplete="off">
                </div>
              </div>
              <div class="control-group con_spe" id="ariDiv" style="display: none;">
                <label class="control-label">实到金额</label>
                <div class="controls">
                  <span class="fee" id="ari"></span>
                </div>
              </div>
              <div class="control-group con_spe" id="fenDiv" style="display: none;">
                <label class="control-label">转化积分</label>
                <div class="controls">
                  <span class="fee" id="fen"></span>
                </div>
              </div>
              <div class="control-group con_spe" id="feeDiv" style="display: none;">
                <label class="control-label">手续费</label>
                <div class="controls">
                  <span class="fee" id="fee"></span>
                </div>
              </div>
              <div class="control-group sui-inf">
              	温馨提示：<br/>
              	1.提现金额需大于100元<br/>
              	2.仅支持周一提现（节假日顺延）<br/>
              	3.提现金额将转入您的个人信息的银行卡中，预计1-2个工作日内到账
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn">确定</button>
                  <button type="reset" class="sui-btn" onclick="window.history.go(-1);">返回</button>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
	function calFee(){
		var amount=$.trim($("#amount").val());
		if(amount && (amount.indexOf("0") == 0)){
			$("#amount").val(parseInt(amount));
		}
		if(!isPositiveNum(amount)){
			$("#ari,#fee,#fen").text("");
			$("#ariDiv,#fenDiv,#feeDiv").slideUp();
			return;
		}
		if(amount<99 || amount>7000){
			$("#ari,#fee,#fen").text("");
			$("#ariDiv,#fenDiv,#feeDiv").slideUp();
			return;
		}
		
		if(amount!=""){
			$.ajax({
				url:'calFee',
				dataType:'json',
				data:{amount:amount},
				success:function(data){
					if(data.code==0){
						$("#ari").text((data.ari/100).toFixed(2)+"元");
						$("#fee").text((data.fee/100).toFixed(2)+"元");
						$("#fen").text((data.fen)+"积分");
						$("#ariDiv,#fenDiv,#feeDiv").slideDown();
					}else{
						alert('提示:',data.msg,'warning');
					}
				},
				error:function(req,err,ecp){
					alert('提示:',err,'error'); 
				}
			});
		}else{
			$("#ari,#fee,#fen").text("");
			$("#ariDiv,#fenDiv,#feeDiv").slideUp();
		}
	}
	
	function isPositiveNum(s){//是否为正整数 
		var re = /^[0-9]*[1-9][0-9]*$/ ; 
		return re.test(s);
	} 
</script>
</html>