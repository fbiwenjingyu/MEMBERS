<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.infoTable{margin: 10px auto;text-align: center;border-spacing: 0; background-color: #eee;width: 98%;}
.infoTable tr th{background-color: #E1E7E7;height: 30px;font-size: 16px;color: #A4A6A6;border-bottom: 1px solid #ddd;}
.infoTable tr td{height: 35px; background-color: #fff;border-bottom: 1px solid #F2F5F5;font-size: 14px;border-right: 1px solid #F2F5F5;transition:all .3s ease;-webkit-transition:all .3s ease;}
.infoTable tr:hover td{background-color: #f7f7f7;}
.notice_none {display: block;  text-align: center; padding: 60px 0; color: #999;font-size: 16px;}
</style>
</head>
<body>
<div class="dialogPage">
	<div class="om-panel-header">服务中心用户列表</div>
	<div class="editDiv" style="height: 85%;overflow-y: scroll;">
		
		 <c:if test="${fn:length(users) == 0 }">
				<div class="notice_none">暂无数据!</div>
		</c:if>            
        <c:if test="${fn:length(users) != 0 }">
			<table class="infoTable">
				<tr>
					<th width="10%">ID</th>
					<th width="13%">用户账号</th>
					<th width="13%">联系电话</th>
					<th width="10%">姓名</th>
					<!-- <th width="10%">类型</th> -->
					<th width="10%">等级</th>
					<th width="13%">注册人编号</th>
					<th width="13%">邀请人编号</th>
					<th>开通时间</th>
				</tr>
				
				<c:forEach var="u" items="${users }">
					<tr>
						<td>${u.id }</td>
						<td>${u.accountNo }</td>
						<td>${u.phone }</td>
						<td>${u.realName }</td>
					<%-- 	<td>${u.type }</td> --%>
						<td>${u.levelName }</td>
						<td>${u.agentNo }</td>
						<td>${u.recommendNo }</td>
						<td><fmt:formatDate value="${u.activateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
		   </table>
        </c:if>
		
			
	</div>
</div>
</body>
</html>