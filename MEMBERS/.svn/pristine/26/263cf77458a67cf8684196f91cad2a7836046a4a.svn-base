<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<div class="wrap_tit">
	<a href="${ctx}/memberManage/toMemberAdd" class='<c:if test="${param.on==1}">on</c:if>'>用户信息查询</a>
	<c:if test="${user.type ==1 or user.type ==3}">
		<a href="${ctx}/memberManage/toFriendAdd" class='<c:if test="${param.on==4}">on</c:if>'>添加好友</a>
		<a href="${ctx}/memberManage/toBuyAgain" class='<c:if test="${param.on==6}">on</c:if>'>再次购买</a>
	</c:if>
		<a href="${ctx}/memberManage/toMemberInfo" class='<c:if test="${param.on==2}">on</c:if>'>邀请信息列表</a>
	<a href="${ctx}/user/person" class='<c:if test="${param.on==3}">on</c:if>'>个人信息</a>
	<a href="${ctx}/memberManage/toApplyBC" class='<c:if test="${param.on==5}">on</c:if>'>申请成为服务中心</a>
</div>