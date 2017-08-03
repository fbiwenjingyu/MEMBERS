<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
   <title>大博智家会员系统</title>
    <script type="text/javascript" src="${js}/buy.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
    <jsp:include page="../memu.jsp">
 		<jsp:param value="3" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="6" name="on"/>
 	</jsp:include>
    
    <div class="wrap_cont">
        <div class="ibox white">
             <h4 class="ibox_tit">购买记录<a href="toBuyForm" class="tit_a">再次购买</a></h4>
            <form class="sui-form sui-validate ass_form ">
              <div class="ass_fl s1">
                <div data-toggle="datepicker" class="control-group input-daterange">
                  <label class="control-label">日期</label>
                  <div class="controls">
                    <input type="text" class="input-medium input-date" id="startDate"><span>至</span>
                    <input type="text" class="input-medium input-date" id="endDate">
                  </div>
                </div>
              </div>
              <div class="ass_fl s3">
                <div class="control-group">
                  <button type="button" class="sui-btn sui-search" onclick="doQuery(1);">搜索</button>
                </div>
              </div>
            </form>
        </div>
        
        <div class="ibox white">
            <div class="notice_none" style="display: none;">暂无数据!</div>
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1"></table>
            <%@ include file="/WEB-INF/views/pageLink.jsp" %>
        </div>
    </div>
  </div>
</div>
</body>
</html>