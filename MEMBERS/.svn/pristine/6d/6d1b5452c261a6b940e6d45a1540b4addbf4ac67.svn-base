<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <script type="text/javascript" src="${js}/validate.js"></script>
  <style type="text/css">
   .c_descriptive{font-size: 12px;float:left;
    line-height: 18px;}
  </style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="../memu.jsp">
  	<jsp:param name="on" value="2"></jsp:param>
  </jsp:include>
  <div class="container">
    <div class="wrap_tit">
  	  <a href="${ctx}/message/goMessage" class="on">消息中心</a>
      <%-- <a href="${ctx}/message/goHelp">帮助中心</a> --%>
    </div>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">留言</h4>
            <form action="addOpinion" class="sui-form form-horizontal sui-validate" method="POST">
              <div class="control-group">
                <label for="tit" class="control-label">标题</label>
                <div class="controls">
                  <input type="text" id="tit" placeholder="请输入标题" data-rules="required|maxlength=30" name="title" value="${title}">
                </div>
                <div class="c_title"><font color="red">${errorMsg}</font></div>
              </div>
              <div class="control-group">
                <label for="question" class="control-label">问题描述</label>
                <div class="controls">
                  <textarea id="question" placeholder="请输入问题描述" name="comtent" data-rules="required|maxlength=300">${comtent}</textarea>
                </div>
                <div class="c_descriptive"><font color="red">${errorMsg}</font></div>
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn">确定</button>
                  <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/message/goMessage' />'">返回</button>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
 function checkInput(){
	 
	 var tit = trim($("#tit").val());
	 var ques = trim($("#question").val());
	 var pass = true;
	 if(isEmpty(tit)){
		 pass = false;
		 console.log($("#c_descriptive font"));
		 $(".c_descriptive font").html("问题描述不能为空");
	 }
	 return pass;
 }
</script>
</html>