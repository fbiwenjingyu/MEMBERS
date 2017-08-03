<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博新会员管理平台</title>
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
        <div class="state_box succ">
            <img src="${img }/succ.png" alt="">
            <h2>充值成功!</h2>
            <div class="state_btn"><a href="${ctx}/recharge/toRecharge">返回充值首页</a></div>
        </div>
    </div>
  </div>
</div>
</body>
</html>