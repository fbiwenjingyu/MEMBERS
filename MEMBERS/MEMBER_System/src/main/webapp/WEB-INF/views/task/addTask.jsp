<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="addNewTask" method="post">
<input type="hidden" name="taskName" id="taskName" value="赠送博豆任务"/>
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>统计日期：</td>
			<td><input type="datetime" class="easyui-datebox" id="collectDate" name="collectDate" style="width:155px;" required="true"></td>
		</tr>
		<tr>
			<td><span class="required">*</span>任务类型：</td>
			<td>
				<select id="taskType" name="taskType" style="height:24px;">
					<option value="1">赠送博豆任务</option>
					<option value="2">更用户等级</option>
					<option value="3">计算管理奖励</option>
					<option value="4">计算辅导奖励</option>
				</select>
			</td>
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
	$("#taskType").change( function() {
		$("#taskName").val($("#taskType option:selected").text());
	});
	
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	});
});
</script>
</html>