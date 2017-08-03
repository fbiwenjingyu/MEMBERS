<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <script type="text/javascript" src="${js}/exchange.js"></script>
  <style type="text/css">
  .yundie_list tr{cursor: pointer;}
  .yundie_list tr td:last-child{padding:10px;}
  .yundie_list p{text-align: left;padding-left: 20px;}
  .yundie_list p span{display: inline-block;margin-right: 30px;}
  .pdetail{display: block;padding:4px 10px;text-align: left;line-height:20px;font-size:14px;}
  .tr_detail{display: none;}
  .tr_detail td{background-color: #eee;}
  </style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
<div class="container">
   <jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="6" name="on"/>
 	</jsp:include>
    
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">兑换列表 <a href="toExchange" class="tit_a" style="background-color: #F1F5F5;color: #999;">返回</a></h4>
        	<div class="notice_none" style="display: none;">暂无数据!</div>
            <table class="table_ui yundie_list" border="0" cellpadding="0" cellspacing="1"></table>
            <%@ include file="/WEB-INF/views/pageLink.jsp" %>
        </div>
    </div>
  </div>
</div>
</body>
</html>