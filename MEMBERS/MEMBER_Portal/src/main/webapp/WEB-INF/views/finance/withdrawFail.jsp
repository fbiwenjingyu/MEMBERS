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
        <div class="state_box fail">
            <img src="${img }/fail.png" alt="">
            <h2>抱歉，您的提现申请失败!</h2>
            <p>${msg } <br>请联系 <a href="tel:4009969595">400-996-9595</a> 申请修改</p>
            <div class="state_btn"><a href="toDraw">返回提现首页</a></div>
        </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		setTimeout(function(){
			if('${msg }'=="银行卡号为空"){
				window.location.href="${ctx}/user/person";
			}
		},5000);
	});
</script>
</body>
</html>