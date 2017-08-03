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
            <h4 class="ibox_tit">充值记录<a href="toAdd" class="tit_a">充值</a></h4>
            
			<c:if test="${fn:length(gdm.rows) == 0 }">
				<div class="notice_none">暂无数据!</div>
			</c:if>            
            <c:if test="${fn:length(gdm.rows) != 0 }">
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <th width="30%">时间</th>
                  <th width="25%">充值资产</th>
                  <th width="25%">充值方式</th>
                  <th>状态</th>
                </tr>  
				<c:forEach var="item" items="${gdm.rows}" >
				<tr>
                  <td><fmt:formatDate value="${item.CREATE_TIME }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <td>
                  	<c:choose> 
						<c:when test="${item.ACCOUNT_TYPE==1}">
						    <c:if test="${item.FORWARD==1 }"><b style="color:#ff5722;">-<fmt:formatNumber value="${item.AMOUNT/100}" pattern="#0.00"/>元</b></c:if>
							<c:if test="${item.FORWARD==0 }"><b style="color:#02CDC1;">+<fmt:formatNumber value="${item.AMOUNT/100}" pattern="#0.00"/>元</b></c:if>
						</c:when> 
						<c:when test="${item.ACCOUNT_TYPE==2}">
						    <c:if test="${item.FORWARD==1 }"><b style="color:#ff5722;">-${item.AMOUNT }博豆</b></c:if>
							<c:if test="${item.FORWARD==0 }"><b style="color:#02CDC1;">+${item.AMOUNT }博豆</b></c:if>
						</c:when> 
						<c:when test="${item.ACCOUNT_TYPE==3}">
						    <c:if test="${item.FORWARD==1 }"><b style="color:#ff5722;">-${item.AMOUNT }积分</b></c:if>
							<c:if test="${item.FORWARD==0 }"><b style="color:#02CDC1;">+${item.AMOUNT }积分</b></c:if>
						</c:when> 
						<c:otherwise>   
						${item.AMOUNT }
						</c:otherwise> 
					</c:choose> 
                  </td>
                  <c:set var="varway">
                  	<c:out value="${item.WAY}"/>
                  </c:set> 
                  <td>${way[varway] }</td>
                  <c:set var="varstatus">
                  	<c:out value="${item.STATUS}"/>
                  </c:set> 
                  <td>
                  	<span 
					<c:choose> 
						<c:when test="${varstatus=='2'}">   
						class="color2"
						</c:when> 
						<c:otherwise>   
						class="color1" 
						</c:otherwise> 
					</c:choose> 
                  	>
                  		${status[varstatus] }
                  	</span>
                  </td>
                </tr>
				</c:forEach>
            </table>
            <jsp:include page="../pageLinkAlone.jsp">
   				<jsp:param name="page" value="${po.currPage }"></jsp:param>
   				<jsp:param name="pageSize" value="${po.pageSize }"></jsp:param>
   				<jsp:param name="sum" value="${gdm.total }"></jsp:param>
   				<jsp:param name="url" value="${ctx }/recharge/toRecharge"></jsp:param>
    		</jsp:include>
          </c:if>  
            
        </div>
    </div>
  </div>
</div>
</body>
</html>