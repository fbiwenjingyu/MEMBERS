<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<div class="wrap_tit">
	<a href="${ctx}/assetes/toAssetes?flag=1" class='<c:if test="${param.on==3}">on</c:if>'>资产流水</a>
	<c:if test="${user.type ==1 or user.type ==3}">
		<a href="${ctx}/recharge/toRecharge" class='<c:if test="${param.on==1}">on</c:if>'>充值</a>
		
		<a href="${ctx}/assetes/toAssetes?flag=2" class='<c:if test="${param.on==4}">on</c:if>'>货币转换</a>
		<a href="${ctx}/transfer/toTransfer" class='<c:if test="${param.on==5}">on</c:if>'>转账</a>
		<a href="${ctx}/assetes/toExchange" class='<c:if test="${param.on==6}">on</c:if>'>物品兑换</a>
		<a href="${ctx}/draw/toDraw" class='<c:if test="${param.on==2}">on</c:if>'>提现</a>
	</c:if>
</div>