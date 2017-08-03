<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<c:url  value='/static/js/validate.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/opinion.js' />" ></script>
<style>
body{color:#333;}
.editDiv{display: block;padding:10px 20px;}
.replyDiv{height:30px;background-color:#01CCC0; font-size: 15px; line-height: 30px;margin:20px 0 10px;color: #fff;padding-left: 10px;}
.fontDiv{ border-bottom: 1px solid #ddd;line-height:26px;font-size: 12px;margin:0 10px 15px;padding-bottom: 15px;}
.whoDiv{text-align:right;font-size: 12px;margin:10px;}
.addDiv{margin:0 10px;}
.addDiv textarea{ width:100%; height: 80px;resize:none;border:1px solid #ddd;margin:10px 0 0;}
#noReply{margin-left:40px;}
#sureSubmit{
border:1px solid #01ccc0;color:#fff;line-height:26px; float:right;cursor: pointer;
width:80px;background: #01ccc0;text-align: center; margin-right:10px;margin-top:5px;
}
#alertInfo{
   margin-left:10px;margin-top:5px;
}
.delete{color:#999}
#addId{margin: 0 10px;}
</style>
</head>
<body>
<div class="dialogPage">
	<div class="om-panel-header">意见反馈详情</div>
	<div class="editDiv">	
	   <div class="tip">
	        ${model.comtent }
	   </div>
	   <div class="replyDiv">回复</div>
	   <c:choose>
	     <c:when test="${not empty model.replys }">
	      <div class="mainComtent">
	      <c:forEach items="${model.replys}" var="item">
                <div class="forDiv">
	              <div class="fontDiv">${item.replyComtent }</div>
	              <div class="whoDiv">
	             
	              <font class="delete">由</font> <font color="#01CCC0">&nbsp;|&nbsp;${item.replyName }&nbsp;|&nbsp;<fmt:formatDate value="${item.replyTime}" pattern="yyyy-MM-dd"/></font>
	              <a href="#" class="delete" id="id${item.id}" onclick="deleteReply(${item.id},${item.responseId })">删除</a>
	              </div>
	            </div>
          </c:forEach>
	   </div>
	     </c:when>
	     <c:otherwise>
	        <div id="noReply">暂时没有回复</div>
	     </c:otherwise>
	   </c:choose>
	  <div id="addId">添加回复</div>
	  <form action="${ctx}/opinion/addReply" method="POST" onSubmit="return checkInput()">
	  <div class="addDiv">
	    <input type="hidden" name="responseId" value="${model.id}">
	    <textarea rows="7" cols="120" name="replyComtent" id="replyComtent"></textarea>
	    
	  </div>
	  <div id="sureSubmit">保存</div><div id="alertInfo"><font color="red"></font></div>
	    </form>
	</div>
	

</div>
</body>
<script type="text/javascript">
function deleteReply(id , rid){
	
	$.messager.confirm('提示:','你确认要删除吗?',function(e){ 
		if(e){ 
  		   $.post('${ctx}/opinion/deleteReply',{"id":id,"responseId":rid}, function(data) {
  			   if(data.success){
  		          $("#id"+id).parent().parent().remove();
  				} else {
  					if(data.msg){
  						$.messager.alert('提示:',data.msg,'warning');
  					}
  				}
  			}, 'json');
		}
	}); 

}
window.onload = function(){
	var noreply = $("#noReply");
	console.log(noreply.length);
	if(noreply.length> 0){
	    $("#addId").css("margin-top","50px");
	}
}
</script>
</html>