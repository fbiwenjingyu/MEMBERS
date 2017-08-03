<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="nodeUpdate">
<div class="dialogPage">
	<div class="om-panel-header">节点调整</div>
	<div class="editDiv">
		<input type="hidden" id="userId" name="userId" value="${model.id}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>新邀请人账号：</td>
			<td><input type="text" id="accountNo" name="accountNo"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" >&nbsp;确定&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
		<input id="error" type="hidden" value="${errorMsg}">
	</div>
</div>
</form>
</body>  
<script type="text/javascript">
$(function(){
	
	var errorMsg = $.trim($('#error').val());
	if(errorMsg != ''){
		alert(errorMsg);
	}
	/* $('#scale').validatebox({validType:'range:[1,9]'}); */
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>