<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<div class="sidebar">
    <a href="${ctx}/toIndex" class='subnav <c:if test="${param.on==1}">on</c:if>'><em>&#xe60a;</em>首页</a>
    <a href="${ctx}/memberManage/toMemberAdd" class='subnav <c:if test="${param.on==3}">on</c:if>'><em>&#xe608;</em>用户中心</a>
    <a href="${ctx}/assetes/toAssetes?flag=1" class='subnav <c:if test="${param.on==4}">on</c:if>'><em>&#xe606;</em>财务管理</a>
	 <a href="${ctx}/message/goMessage" class='subnav <c:if test="${param.on==2}">on</c:if>'><em>&#xe609;</em>信息中心</a>
</div>