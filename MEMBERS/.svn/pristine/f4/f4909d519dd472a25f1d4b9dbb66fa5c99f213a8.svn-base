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
   		<jsp:param name="on" value="1"></jsp:param>
    </jsp:include>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">充值</h4>
            <form action="add" class="sui-form form-horizontal sui-validate" method="post">
              <div class="control-group">
                <label for="tit" class="control-label"><b>*</b>充值金额</label>
                <div class="controls">
                  <input type="text" id="amount" placeholder="100" data-rules="required|digits|gt=99|lt=10001" data-error-msg="请输入100到10000之间的正整数" name="amount">
                </div>
              </div>
              <div class="control-group">
                <label for="paytype" class="control-label"><b>*</b>账户类型</label>
                <div class="controls">
                  <select name="accountType" id="accountType">
                  	<c:forEach items="${accountType}" var="entry">
						<option value="${entry.key}">${entry.value}</option>
					</c:forEach>  
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label for="paytype" class="control-label"><b>*</b>支付方式</label>
                <div class="controls">
                  <select name="way" id="way">
                  	<c:forEach items="${way}" var="entry">
						<option value="${entry.key}">${entry.value}</option>
					</c:forEach>  
                  </select>
                  <div class="sui-msg msg-error help-inline">暂时仅支持支付宝</div>
                </div>
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
<script type="text/javascript">
$(function(){
	$("#amount").blur(function(){
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