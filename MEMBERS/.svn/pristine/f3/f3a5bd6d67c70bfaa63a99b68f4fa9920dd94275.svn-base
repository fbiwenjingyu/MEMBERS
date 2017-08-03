<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<style>
.editTable{margin:10px auto;}
.editTable select{height:30px;border:1px solid #ddd;margin:0 10px 10px 0; width:200px;}
.editTable select.long{width:250px;}
.editTable textarea{ width:350px;height:80px;padding:5px;font-size:14px;border:1px solid #ddd;resize:none;} 
.editTable *{font-family: "Microsoft YaHei" ! important;}
.editTable .vipbtn button{width:100px; height:36px;line-height:36px;text-align:center;background:#01CBBF;color:#fff;margin-right:15px;}
</style>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td>登录账号：</td>
			<td colspan="3">${model.accountNo}</td>
		</tr>
		<tr>
			<td>新登录密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="password" name="password"  class="easyui-validatebox"  data-options="validType:['length[6,24]']"/></td>
			<td>确认登录密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="againPassword"   class="easyui-validatebox"  data-options="validType:['confirmPwd[\'password\']']"/></td>
		</tr>
		<tr>
			<td>新支付密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="payPassword" name="payPassword"  class="easyui-validatebox"  data-options="validType:['length[6,24]']"/></td>
			<td>确认支付密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="againPayPassword"   class="easyui-validatebox"  data-options="validType:['confirmPwd[\'payPassword\']']"/></td>
		</tr>
		<tr>
			<td>开户银行：</td>
			<td><select name="bankName" id="bankName" ></select></td>
			<td>开户支行：</td>
			<td><input type="text" id="subbranchBank" name="subbranchBank"  class="easyui-validatebox" data-options="validType:'length[4,40]'" value="${model.subbranchBank}"/></td>
		</tr>
		<tr>
			<td>持卡人姓名：</td>
			<td><input type="text" id="cardName" name="cardName" class="easyui-validatebox" data-options="validType:'nameFormat'" value="${model.cardName}"/></td>
			<td>银行卡号：</td>
			<td><input type="text" id="cardNo" name="cardNo" class="easyui-validatebox" data-options="validType:'cardNoFormat'" value="${model.cardNo}"/></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="realName" name="realName" class="easyui-validatebox" data-options="validType:'nameFormat'" value="${model.realName}"/></td>
			<td>联系电话：</td>
			<td><input type="text" id="phone" name="phone" class="easyui-validatebox"  data-options="validType:'phonex'" value="${model.phone}"/></td>
		</tr>
		<tr>
			<td>地址：</td>
			<td colspan="3">
				<select id="selectProvince" class="easyui-validatebox"   ></select>
				<select id="selectCity" class="easyui-validatebox"   ></select>
				<select id="selectArea"  style="display: none;" class="easyui-validatebox"  ></select><br>
			</td>
		</tr>
		<tr>
			<td>详细地址：</td>
			<td colspan="3">
				<textarea id="fullAddress" name="fullAddress" class="easyui-validatebox" data-options="validType:'length[4,40]'" >${model.fullAddress}</textarea>
			</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button type="submit" id="submit" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
<input type="hidden" id="province" name="province" value="${model.province}"/> 
<input type="hidden" id="city" name="city" value="${model.city}"/> 
<input type="hidden" id="area" name="area" value="${model.area}"/> 
<input type="hidden" id="id" name="id" value="${model.id}"/> 
<input id="error" type="hidden" value="${errorMsg}">
</form>
<script type="text/javascript" src="<c:url  value='/static/js/areaData.js' />" ></script>
<script type="text/javascript">
$(function(){
	//初始化银行
	 var bankNameList = ["中国工商银行","中国农业银行","中国银行","中国建设银行"];
	  
	  var bankObject = $("#bankName");
	  
	  for(var i in bankNameList){
		  var name = bankNameList[i];
		  bankObject.append("<option value='" + name + "'> " + name + "</option>");
	  }
		
	
	//初始化区域
	var data = {
			aId:['selectProvince','selectCity','selectArea']
		};
	$.areaSelect(data);
	 var province = '${model.province}';
	 if(province != ''){
		 $("#selectProvince option:contains('${model.province}')").attr("selected", true);
		 $("#selectProvince").change(); 
		 $("#selectCity option:contains('${model.city}')").attr("selected", true);
		 $("#selectCity").change();
		 $("#selectArea option:contains('${model.area}')").attr("selected", true);
	 }
	//修改选中项
    //银行
    $("#bankName").val("${model.bankName}");
	
	var errorMsg = $.trim($('#error').val());
	if(errorMsg != ''){
		alert(errorMsg);
	}
	
    $.extend($.fn.validatebox.defaults.rules, {
        phonex: {
    		validator: function(value, param){
    			var reg= /^(133|153|177|180|181|189|134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|155|156|176|185|186|145|147|170)\d{8}$/;
   	            if(!reg.test(value)){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '格式不正确,输入正确的手机号'
        },
        cardNoFormat: {
    		validator: function(value, param){
    			var reg = /(^\d{16,20}$)/; 
   	            if(!reg.test(value)){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '银行卡号必须输入16-20位'
        },
        nameFormat:{
        	validator: function(value, param){
    			var reg = /^([a-z]|[A-Z]|[\u4e00-\u9fa5]){2,12}$/;
   	            if(!reg.test(value)){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '姓名必须2至12个中文或英文字符'
        },
        payPwd:{
        	validator: function(value, param){
        		var pwd = $("#"+ param[0]).val();
   	            if(value == pwd){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '支付密码和登录密码不能相同'
        },
        confirmPwd:{
        	validator: function(value, param){
        		var oldPwd = $("#" + param[0]).val();
   	            if(value != oldPwd){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '前后密码不一致'
        },
        lengthx:{
        	validator: function(value, param){
        		var oldPwd = $("#" + param[0]).val();
   	            if(value != oldPwd){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '前后密码不一致'
        },
        selectValue:{
        	validator: function(value, param){
   	            if($(this).is(":visible") && value == 0){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '请选择一项'
        },
        checkMember:{
        	validator: function(value, param){
        		var v = $.trim(value);
        		if(v != ''){
        			var flag = false;
	        		$.ajax({
	    				url:'checkMember',
	    				async: false,
	    				data:{"accountNo" : v},
	    				success:function(data){
	    					if(data){
	    						flag = false;
	    					}else{
	    						flag = true;
	    					}
	    				}
	    			});
	        		return flag;
        		}else{
        			return false;
        		}
    		},
    		message: '账号已经存在'
        }
    });
    
    $('#accountNo').on('change', function(){
    	var phone = $("#phone"),
    		vphone = $.trim(phone.val());
    	if(vphone == ''){
    		phone.val($('#accountNo').val());
    	}
    });
    
    //修改选中项
    //$("#selectProvince").find("option[text='pxx']").attr("selected",true);
    
	$("#submit").click(function(){
		if(!$("#form1").form('validate')){
			return false;
		}else{
			//区域存储中文
			if($("#selectProvince").val() != 0){
				$("#province").val($("#selectProvince  option:selected").text());
			}
			if($("#selectCity").val() != 0){
				$("#city").val($("#selectCity  option:selected").text());
			}
			if($("#selectArea").is(":visible")){
				$("#area").val($("#selectArea  option:selected").text());
			}else{
				$("#area").val('');
			}
		}
	});
});

</script>
</body>
</html>