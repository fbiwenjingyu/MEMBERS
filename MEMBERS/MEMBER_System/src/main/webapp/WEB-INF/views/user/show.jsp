<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<style>
.viptable{margin:10px auto;}
.viptable th,.viptable td{padding:10px;font-size:15px;color:#333;text-align:left;vertical-align: top;color: #999;}
.viptable th{border-bottom: 1px solid #ddd;}
.viptable th:nth-of-type(1),.viptable td:nth-of-type(1){color: #333;}
.viptable input[type="text"],.viptable input[type="password"]{padding:0 5px;width:240px;height:30px;line-height:30px;border:1px solid #ddd;}
.viptable select{height:30px;border:1px solid #ddd;margin:0 10px 10px 0; width:100px;}
.viptable select.long{width:250px;}
.viptable textarea{ width:350px;height:80px;padding:5px;font-size:14px;border:1px solid #ddd;resize:none;} 
.viptable *{font-family: "Microsoft YaHei" ! important;}
.viptable .vipbtn button{width:100px; height:36px;line-height:36px;text-align:center;background:#01CBBF;color:#fff;margin-right:15px;margin-left: 0;}
</style>
<form id="form1" action="addAccountMoney">
<div class="dialogPage">
	<div class="editDiv">
		<table class="viptable" width="95%">
		<tr>
			<th colspan="2"><h4>银行卡信息</h4></th>
		</tr>
		<tr>
			<td width="100">开户人</td>
			<td>${(model.cardName == "" || model.cardName == null)? "暂未添加" : model.cardName}</td>
		</tr>
		<tr>
			<td>开户行</td>
			<td>${(model.bankName == "" || model.bankName == null)? "暂未添加" : model.bankName}</td>
		</tr>
		<tr>
			<td>开户支行</td>
			<td>${(model.subbranchBank == "" || model.subbranchBank == null)? "暂未添加" : model.subbranchBank}</td>
		</tr>
		<tr>
			<td>开户银行卡号</td>
			<td>${(model.cardNo == "" || model.cardNo == null)? "暂未添加" : model.cardNo} </td>
		</tr>
		<tr>
			<th colspan="2"><h4>基本资料信息</h4></th>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${(model.realName == "" || model.realName == null)? "暂未添加" : model.realName}</td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td>${(model.phone == "" || model.phone == null)? "暂未添加" : model.phone}</td>
		</tr>
		<tr>
			<td>地址</td>
			<td><span id="dz">${model.province}${model.city}${model.area}${model.fullAddress}</span></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div class="vipbtn">
				<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
			</div>
			</td>
		</tr>
	   </table>
	   
	</div>
</div>
</form>
<script type="text/javascript">
$(function(){
	if($("#dz").text() == ''){
		$("#dz").text("暂未添加")
	}
});
</script>

</body>
</html>