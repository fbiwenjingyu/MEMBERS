<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="updateStatus" method="post">
<div class="dialogPage">
	<div class="om-panel-header">修改</div>
	<div class="editDiv">
		<input type="hidden" id="drawId" name="drawId" value="${param.drawId}">
		<table class="editTable">
		<tr>
			<td>状态：</td>
			<td><input type="combo" id="drawStatus" name="drawStatus"></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td colspan="3">
				<textarea name="drawMemo" id="drawMemo" cols="58" rows="3" maxlength="256"></textarea>
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
	$('#btnSubmit').click(function(){
		var reg=/^\s*$/;
		var drawStatus=$('#drawStatus').combobox('getValue');
		if((drawStatus=="1" || drawStatus=="4") && (reg.test($("#drawMemo").val()))){
			$.messager.alert('提示:','备注不能为空!'); 
			return;
		}
		$.ajax({
			url:'changeStatus',
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
			error:function(req,err,ecp){alert(ecp);
				$.messager.alert('提示:',err,'error'); 
			}
		});
	});
	
	$('#drawStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	editable:false,
    	panelHeight:'auto',
    	value:'${param.auditStatus}'
    }); 
	
	$('#drawMemo').validatebox({
		validType:['maxLength[80]']
	});
});
</script>
</html>