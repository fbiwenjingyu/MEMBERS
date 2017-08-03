<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>  
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8" src="${js}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${js}/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${js}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<form id="form1" action="edit" method="post">
<input type="hidden" name="id" value="${notice.ID }">
<input type="text" style="display: none;">
<div class="dialogPage">
	<div class="om-panel-header">
		<c:choose> 
			<c:when test="${empty notice}">新增</c:when> 
			<c:otherwise>修改</c:otherwise> 
		</c:choose> 
	</div>
	<div class="editDiv">
		<table class="editTable">
			<tr>
				<td ><span class="required">*</span>标题：</td>
				<td><input type="text" id="title" name="title" value="${notice.TITLE }"></td>
			</tr>
		</table>
		
		<!-- 加载编辑器的容器 -->
        <script id="content" name="content" type="text/plain" style="width:950px;height:340px; margin-left:20px;">
		${notice.CONTENT }
		</script>
	   	
	   	<br/>
		<div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" onclick="edit();">&nbsp;保存&nbsp;</button>
			<button type="button" onclick="javascript:art.dialog.close();" class="button">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
var ue=null;
$(function(){
	//初始化文本编辑器
	ue = UE.getEditor('content');
	
	$('#title').validatebox({
		required:true,
		validType:['maxLength[150]']
	}); 
})

function edit(){
	if(/^\s*$/.test($("#title").val())){
		$("#title").val("");
	}
	$("#title").val($("#title").val().replace(/^\s+|\s+$/g,""));
	
    var content=$.trim(ue.getContent());
	//console.log("["+content+"]");
	if(content.length<=0){
		alert("公告内容不能为空");
		return;
	} 
	
	if(!$('#form1').form('validate')){
		return;
	}
	
	$.ajax({
		type:'POST',
		url:'edit',
		dataType:'json',
		data:$('#form1').serialize(),
		success:function(data){
			if(data.code==0){
				var win=art.dialog.open.origin; 
				win.showTip();
				art.dialog.close();
			}else{
				$.messager.alert('提示:',data.msg,'warning'); 
			}
		},
		error:function(req,err,ecp){
			$.messager.alert('提示:',err,'error'); 
		}
	});
}
</script>
</html>