<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <style type="text/css">
  	.wrap_cont i{
  		font-family:"iconfont" !important;font-size:30px;font-style:normal;
  		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;color: #02CDC1;
		line-height: 52px;    margin-right: 10px;
		transition:all .3s ease;-webkit-transition:all .3s ease;
	}
  </style>
  
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
            
       	<c:choose>
       		<c:when test="${result.type eq 0 }">
       			<div class="ibox white">
		            <h4 class="ibox_tit">申请成为服务中心</h4>
              		<form action="<c:url value = '/memberManage/applyToBC' />" class="sui-form form-horizontal sui-validate">
		              <div class="control-group sui-inf">确定申请成为服务中心？</div>
		              <div class="control-group">
		                <div class="controls">
		                  <button type="submit" class="sui-btn">确定</button>
		                </div>
		              </div>
		            </form>
            	</div>  
       		</c:when>
       		
       		<c:when test="${result.type eq 2 }">
       			<div class="ibox white">
		              <h4 class="ibox_tit">申请成为服务中心</h4>
		              <form action="<c:url value = '/memberManage/applyToBC' />" class="sui-form form-horizontal sui-validate">
			              <div class="control-group sui-inf"><i style="color: red;">&#xe600;</i>${result.resultMes }</div>
			              <div class="control-group">
			                <div class="controls">
			                  <button type="submit" class="sui-btn btn-primary">重新申请</button>
			                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/toIndex' />'">返回主页</button>
			                </div>
	              		  </div>
	              	  </form>	  
            	</div>  
       		</c:when>
       		
       		<c:otherwise>
       		
       			<div class="ibox white">
		              <h4 class="ibox_tit">申请成为服务中心</h4>
		              <form action="" class="sui-form form-horizontal sui-validate">
			              <div class="control-group sui-inf">
			              	<c:if test="${result.type eq -1 or result.type eq -2 or result.type eq 1 or result.type eq 4 }"><i style="color: orange;">&#xe602;</i></c:if>
							<c:if test="${result.type eq 3}"><i>&#xe601;</i></c:if>
							${result.resultMes }
						  </div>
			              <div class="control-group">
			                <div class="controls">
			                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/toIndex' />'">返回主页</button>
			                </div>
	              		  </div>
	              	   </form>
            	</div> 
            	
       		</c:otherwise>
       	</c:choose>
    </div>
  </div>
</div>
</body>
</html>