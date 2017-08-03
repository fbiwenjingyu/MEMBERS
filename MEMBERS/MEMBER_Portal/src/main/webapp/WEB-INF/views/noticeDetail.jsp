<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <%@ include file="/WEB-INF/views/taglibs.jsp" %>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="memu.jsp">
  	<jsp:param name="on" value="1"></jsp:param>
  </jsp:include>
  <div class="container">
    <div class="wrap_tit">
      <a href="#" class="on">公告详情</a>
    </div>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">${notice["TITLE"] }<span><fmt:formatDate value="${notice.DEPLOY_TIME }" pattern="yyyy-MM-dd HH:mm:ss"/></span></h4>
            <div class="notice_cont">
           	${notice["CONTENT"] }
            </div>
        </div> 
    </div>
  </div>
</div>
</body>
</html>