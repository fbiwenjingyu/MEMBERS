<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <script type="text/javascript" src="${js}/transform.js"></script>
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
    
        	<!-- 我的资产信息 -->
        <div class="ibox white" id="my_count" style="display: block;">
            <h4 class="ibox_tit">我的资产<a href="<c:url value = '/assetes/gotoTransform' />"  class="tit_a">转换</a></h4>
            <div class="my_asset">
                 <span>现金：<b id="balance">${user.balance}</b></span>
                <span>积分：<b id="generalizeScore">${user.generalizeScore }</b></span>
                <span>博豆：<b id="shoppingScore">${user.shoppingScore }</b></span>
            </div>
        </div>
        
        <div class="ibox white">
            <h4 class="ibox_tit">转换记录</h4>
            <div class="notice_none" style="display: none;">暂无数据!</div>
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1"></table>
            
            <%@ include file="/WEB-INF/views/pageLink.jsp" %>
        </div>
    </div>
  </div>
</div>
</body>
</html>