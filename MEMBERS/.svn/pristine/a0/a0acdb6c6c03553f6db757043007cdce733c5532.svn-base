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
        <div class="ibox white">
            <h4 class="ibox_tit">提现记录<a href="toAdd" class="tit_a">提现</a></h4>
            
            <c:if test="${fn:length(gdm.rows) == 0 }">
				<div class="notice_none">暂无数据!</div>
			</c:if>            
            <c:if test="${fn:length(gdm.rows) != 0 }">
            
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <th width="20%">时间</th>
                  <th width="15%">金额</th>
                  <th width="15%">到账金额</th>
                  <th width="10%">转化积分</th>
                  <th width="10%">手续费</th>
                  <th width="10%">提现状态</th>
                  <th>备注</th>
                </tr>  
                <c:forEach var="item" items="${gdm.rows}">
				<tr>
                  <td><fmt:formatDate value="${item.CREATE_TIME }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <td><fmt:formatNumber value="${item.AMOUNT/100}" pattern="#0.00"/>元</td>
                  <td><fmt:formatNumber value="${item.ARRIVAL_AMOUNT/100}" pattern="#0.00"/>元</td>
                  <td>${item.JF}积分</td>
                  <td><fmt:formatNumber value="${item.FEE/100}" pattern="#0.00"/>元</td>
                  <c:set var="varstatus">
                  	<c:out value="${item.STATUS}"/>
                  </c:set> 
                  <td>
                  	<span 
					<c:choose> 
						<c:when test="${varstatus=='2' || varstatus=='3' || varstatus=='5'}">   
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
                  <td>${item.MEMO }</td>
                </tr>
				</c:forEach>
            </table>
            <jsp:include page="../pageLinkAlone.jsp">
   				<jsp:param name="page" value="${po.currPage }"></jsp:param>
   				<jsp:param name="pageSize" value="${po.pageSize }"></jsp:param>
   				<jsp:param name="sum" value="${gdm.total }"></jsp:param>
   				<jsp:param name="url" value="${ctx }/draw/toDraw"></jsp:param>
    		</jsp:include>
            </c:if>
        </div>
    </div>
  </div>
</div>
</body>
</html>