<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	.errMes{color: red;position: absolute; display: none;}
</style>
</head>
<body>
<form id="form1" action="<c:url value="opera" />">
<input type="hidden"  name="Bid" id="Bid" value="${ model.id}"/>
<input type="hidden"  name="version" id="version" value="${ model.version}"/>
<input type="hidden"  name="flag" id="flag" />
<div class="dialogPage">
	<div class="om-panel-header">申请服务中心操作</div>
	<div class="editDiv">
		<table class="editTable">
			<tr>
				<td>用户账号：</td>
				<td>${model.accountNo }</td>
				<td>用户名称：</td>
				<td>${model.realName }</td>
			</tr>
			<tr>
				<td>电话：</td>
				<td>${model.phone }</td>
				<td>申请时间：</td>
				<td><fmt:formatDate value="${model.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td colspan="3">
					<textarea name="remark" id="remark" cols="65" rows="3" maxlength="256"></textarea>
					<p class="errMes">请填写拒绝理由！</p>
				</td>
			</tr>
	   </table>
	   <div class="editBtn">
			<button type="button" class="button" onclick="opera(1);">&nbsp;开通&nbsp;</button>
			<button type="button" class="button" onclick="opera(2);">&nbsp;拒绝&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">

function opera(f){
	
    if(f ==2 ){//拒绝
		var re=$.trim($("#remark").val());
		if(re.length<=0){
			$(".errMes").show();
			return false;
		}
	}
	
    $("#flag").val(f);
	$('#form1').submit();
}

</script>
</html>