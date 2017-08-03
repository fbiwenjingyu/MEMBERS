<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
</head>
<body id="service">
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
 	<jsp:include page="../memu.jsp">
 		<jsp:param value="3" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="5" name="on"/>
 	</jsp:include>
    <div class="wrap_cont">
    
        <c:if test="${optionResult eq 1 }">
  			 <div class="state_box succ">
	            <img src="${img }/succ.png" alt="">
	            <h2>申请成功!</h2>
            	<div class="state_btn"><a href="<c:url value = '/toIndex' />">返回首页</a></div>
        	</div>
  		</c:if>  
  		<c:if test="${optionResult eq 2 }">
	        <div class="state_box fail">
	            <img src="${img }/fail.png" alt="">
	            <h2>${faildMessage }</h2>
	            <div class="state_btn"><a href="<c:url value = '/toIndex' />">返回首页</a></div>
	        </div>
       </c:if>
        
    </div>
  </div>
</div>
</body>
</html>