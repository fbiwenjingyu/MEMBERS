<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<style>
   .r_class{ width: 80px;height: 30px;line-height: 30px;background-color: #02CDC1;color: #fff;text-align: center;font-size: 15px;border-radius: 5px;cursor: pointer;margin-left: 20px;}
   #check{margin:20px 0 0 5px;}
</style>
<script type="text/javascript" src="${js}/validate.js"></script>
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
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
            <div class="notice_cont">
              <h4>${model.title}<span><fmt:formatDate value="${model.time}" pattern="yyyy-MM-dd HH:mm:ss"/></span></h4>
              <div class="notice_txt">${model.comtent}</div>
               <div class="notice_feed_box">
               <c:forEach items="${model.replys}" var="item">
	                <div class="notice_feed_list">
	                  <p>
	                    <b>${item.replyName }：</b>
	                    <span>${item.replyComtent }</span>
	                    <i><fmt:formatDate value="${item.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></i>
	                  </p>
	                </div>
	            </c:forEach>
	            </div>
	            <div class="r_class" id="feedBtn">
	                                                 回复
	            </div>
	            <form class="feed_form" action="${ctx}/message/addReply" method="POST" onsubmit="return checkInput()">
	                    <textarea  id="replyComtent" cols="30" rows="10" name="replyComtent"></textarea>
	                    <input type="hidden" value="${model.id}" name="responseId">
	                    <button type="submit">确定</button>
	                    <div id="check"><font color="red">${errorMsg}</font></div>
	            </form>
            </div>
            <form action="" class="sui-form form-horizontal sui-validate" id="feedback" style="display:none;">
              <div class="control-group">
                <label for="tit" class="control-label">标题</label>
                <div class="controls">
                  <input type="text" id="tit" placeholder="请输入标题" data-rules="required|maxlength=16" name="tit">
                </div>
              </div>
              <div class="control-group">
                <label for="question" class="control-label">问题描述</label>
                <div class="controls">
                  <textarea id="question" placeholder="请输入问题描述"></textarea>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn">确定</button>
                  <button type="reset" class="sui-btn">返回</button>
                </div>
              </div>
            </form>
        </div> 
    </div>
  </div>
</div>
<script>
  $(function(){
    $("#feedBtn").click(function(){
      $(".feed_form").show();
    })
    
    //如果有异常显示，则dom加载完后显示输入框
    if($("#check font").html() != ''){
    	$(".feed_form").show();
    }
  })
 
</script>
</body>
</html>