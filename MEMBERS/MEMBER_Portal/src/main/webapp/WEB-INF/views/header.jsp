<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
	<a href="javascript:;" class="logo">大博智家会员系统-欢迎您！</a>
	<a href="<%= request.getContextPath() %>/security/logout" class="logout fr"><em>&#xe60b;</em>退出</a>
	<a href="#" class="user fr"><em>&#xe607;</em>${user.accountNo}</a>
</div>