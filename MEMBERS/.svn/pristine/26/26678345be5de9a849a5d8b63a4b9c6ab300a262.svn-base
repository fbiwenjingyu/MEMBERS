<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <style type="text/css">
  	.wrap_cont i{
  		font-family:"iconfont" !important;font-size:120px;font-style:normal;
  		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;color: orange;
		line-height: 52px;    margin-right: 10px;
		transition:all .3s ease;-webkit-transition:all .3s ease;
	}
  </style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
 	<jsp:include page="../memu.jsp">
 		<jsp:param value="3" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="6" name="on"/>
 	</jsp:include>
 	
    <div class="wrap_cont">
    	<c:choose>
    		<c:when test="${optionResult eq 1 }">
    			<div class="state_box succ">
		            <img src="${img }/succ.png" alt="">
		            <h2>再次购买成功!</h2>
	            	<div class="state_btn"><a href="<c:url value = '/memberManage/toBuyAgain' />">返回再次购买首页</a></div>
        		</div>
    		</c:when>
    		<c:when test="${optionResult eq 2 }">
    			 <div class="state_box fail">
		            <img src="${img }/fail.png" alt="">
		            <h2>${faildMessage }</h2>
		            <div class="state_btn"><a href="<c:url value = '/memberManage/toBuyForm' />">返回上一页面</a></div>
	       		 </div>
    		</c:when>
    		<c:otherwise>
	       		 <div class="state_box succ">
		            <i>&#xe602;</i>
		            <h2 style="padding-top: 36px;">请勿重复刷新页面!</h2>
	            	<div class="state_btn"><a href="<c:url value = '/memberManage/toBuyAgain' />">返回再次购买首页</a></div>
        		</div>
    		</c:otherwise>
    	</c:choose>
    
    
    </div>
  </div>
</div>
</body>
</html>