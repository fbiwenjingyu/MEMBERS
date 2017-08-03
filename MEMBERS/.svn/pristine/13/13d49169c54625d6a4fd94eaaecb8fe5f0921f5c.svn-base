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
<form id="rform" action="recharge" method="post">
<input type="hidden" name="way" value="0">
<div class="dialogPage">
	<div class="om-panel-header">货币充值</div>
	<div class="editDiv">
		<table class="editTable">
			<tr>
				<td><span class="required">*</span>用户编号：</td>
				<td><input type="text" id="memberNo" name="memberNo"></td>
				<td><span class="required">*</span>数量：</td>
				<td><input type="text" id="amount" name="amount"></td>
			</tr>
			<tr>
				<td>账户类型：</td>
				<td><input type="combo" id="accountType" name="accountType"></td>
				<td>货币方向：</td>
				<td><input type="combo" id="forward" name="forward"></td>
			</tr>
			<tr>
				<td><span class="required">*</span>备注：</td>
				<td colspan="3"><textarea name="memo" id="memo" cols="70" rows="3" maxlength="256" data-options="validType:'length[0,80]'" class="easyui-validatebox"></textarea></td>
			</tr>
		</table>
		<div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" onclick="recharge();">&nbsp;确认&nbsp;</button>
			<button type="button" onclick="javascript:art.dialog.close();" class="button">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#accountType').combobox({  
    	data:JSON.parse('${accountType}'),
    	editable:false,
    	panelHeight:'auto',
    	value:1
    }); 
	$('#forward').combobox({  
    	data:JSON.parse('${rechargeForward}'),
    	editable:false,
    	panelHeight:'auto',
    	value:0
    }); 
	
	$('#memberNo').validatebox({
		required:true,
		validType:['isExist["findMemByNo","memberNo","用户不存在"]']  
	}); 
	$('#amount').validatebox({
		required:true,
		validType:['number_','maxLength[7]']
	});
	$('#memo').validatebox({
		required:true,
	}); 
});

function recharge(){
	$("#memo").val($("#memo").val().trim());
	if(!$('#rform').form('validate')){
		return;
	}
	
// 	if($("[name=forward]").val()=="1" && $("#memo").val()==""){
// 		$.messager.alert('提示:',"备注不能为空",'warning'); 
// 		return;
// 	}
	
	var tip="确认要给"+$("#memberNo").val()+"账户"
	tip=tip+JSON.parse('${rechargeForwardMap}')[$("[name=forward]").val()];
	tip=tip+JSON.parse('${accountTypeMap}')[$("[name=accountType]").val()];
	$.messager.confirm('提示:',tip,function(e){ 
		if(e){
			$.ajax({
				type:"POST",
				url:'recharge',
				dataType:'json',
				data:$("#rform").serialize(),
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
	}); 
}
</script>
</html>