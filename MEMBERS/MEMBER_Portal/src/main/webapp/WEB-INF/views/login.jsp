<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/font/iconfont.css">
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/login.css">
  <title>大博智家会员系统-登录</title>
</head>
<body>

<div class="login_box">
  <h1 class="log_img"></h1>
  <h4 class="log_title">大博智家会员系统</h4>
  <form class="log_form" id="loginForm" >
    <div class="white">
      <p><em>请输入用户名</em><input type="text" id="name" name="userName"><i>&#xe60c;</i></p>
      <p class="bboth"><em>请输入密码</em><input type="password" id="pwd" name="password" ><i>&#xe603;</i></p>
      <p class="ma"><em>请输入右侧验证码</em><input type="text" id="code" name="code">
      	<span>
      		<img id="ma" src="<%= request.getContextPath() %>/image.action?sid=daboo" onclick="this.src=this.src+'&'+Math.random();" />
     	 </span>
     </p>
    </div>
    <button type="button" id="loginBtn" class="log_btn">登录</button>
    <p class="error" id="error">${errorMsg}</p>
  </form>
</div>
<script src="<%= request.getContextPath() %>/static/js/jquery-1.11.1.min.js?jsVersion=${jsVersion}" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/static/js/login.js?jsVersion=${jsVersion}" type="text/javascript"></script>
</body>
</html>