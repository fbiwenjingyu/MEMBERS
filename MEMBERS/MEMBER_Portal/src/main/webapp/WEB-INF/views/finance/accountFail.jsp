<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>分销管理平台</title>
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
        <div class="state_box fail">
            <img src="${img }/fail.png" alt="">
            <h2>${msg }</h2>
            <div class="state_btn"><a href="javascript:history.go(-1);">返回</a></div>
        </div>
    </div>
  </div>
</div>
</body>
</html>