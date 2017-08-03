<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="id" name="id" value="${grade.id}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>等级名称：</td>
			<td><input type="text" id="gradeName" name="gradeName" value="${grade.gradeName}"></td>
			<td>超出部分奖励比例 ：</td>
			<td><input type="text" id="scale" name="scale" value="${grade.scale}"></td>
		</tr>
		<tr>
			<td>节点总金额(元)：</td>
			<td><input type="text" id="gradeAmount" name="gradeAmount" value="${grade.gradeAmount}"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" >&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#scale').validatebox({validType:'grade'});
	$('#gradeAmount').validatebox({validType:'number'});
	
	/* $('#scale').validatebox({validType:'range:[1,9]'}); */
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>