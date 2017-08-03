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
<form id="form1" action="addMember">
<div class="dialogPage">
	<div class="om-panel-header">用户注册</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录账号：</td>
			<td><input type="text" id="accountNo" name="accountNo"  class="easyui-validatebox"  data-options="validType:['phone','checkMember'],required:true"/></td>
			<td><span class="required">*</span>注册单数：</td>
			<td><input type="text" id="regNum" name="regNum"  class="easyui-validatebox"  value="1" data-options="validType:['xnumber'],required:true"/></td>
			
		</tr>
		<tr>
			<td><span class="required" >*</span>登录密码：</td>
			<td><input type="text" name="password" onfocus="this.type='password'" autocomplete="off"  id="password" class="easyui-validatebox"  data-options="validType:'length[6,24]',required:true"/></td>
			<td><span class="required" >*</span>确认登录密码：</td>
			<td><input id="againPassword" type="text"  onfocus="this.type='password'" autocomplete="off"  class="easyui-validatebox"  data-options="validType:['confirmPwd[\'password\']'],required:true"/></td>
		</tr>
		<tr>
			<td><span class="required" >*</span>支付密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off" id="payPassword" name="payPassword"  class="easyui-validatebox"  data-options="validType:'length[6,24]',required:true"/></td>
			<td><span class="required" >*</span>确认支付密码：</td>
			<td><input  type="text"  onfocus="this.type='password'" autocomplete="off" id="againPayPassword"   class="easyui-validatebox"  data-options="validType:['confirmPwd[\'payPassword\']'],required:true"/></td>
		</tr>
		<tr>
			<td><span class="required">*</span>邀请人编号：</td>
			<td><input type="text" id="recommendCode"  class="easyui-validatebox" data-options="validType:'checkRecommend',required:true" value="4009969595" /></td>
			<td><span class="required" >*</span>服务中心：</td>
			<td><input type="text" id="parentCode"  class="easyui-validatebox" data-options="validType:'checkParent',required:true" value="4009969595"/></td>
		</tr>
		<tr>
			<td>开户银行：</td>
			<td><select name="bankName" id="bankName" ></select></td>
			<td>开户支行：</td>
			<td><input type="text" id="subbranchBank" name="subbranchBank"  class="easyui-validatebox" data-options="validType:'length[4,40]'"/></td>
		</tr>
		<tr>
			<td>持卡人姓名：</td>
			<td><input type="text" id="cardName" name="cardName" class="easyui-validatebox" data-options="validType:'nameFormat'"/></td>
			<td>银行卡号：</td>
			<td><input type="text" id="cardNo" name="cardNo" class="easyui-validatebox" data-options="validType:'cardNoFormat'"/></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="realName" name="realName" class="easyui-validatebox" data-options="validType:'nameFormat'"/></td>
			<td>联系电话：</td>
			<td><input type="text" id="phone" name="phone" class="easyui-validatebox"  data-options="validType:'phone'"/></td>
		</tr>
		<tr>
			<td>地址：</td>
			<td colspan="3">
				<select id="selectProvince" ></select>
				<select id="selectCity" ></select>
				<select id="selectArea"  style="display: none;"></select><br>
			</td>
		</tr>
		<tr>
			<td>详细地址：</td>
			<td colspan="3">
				<textarea id="fullAddress" name="fullAddress" class="easyui-validatebox" data-options="validType:'length[4,40]',tipPosition:'right'" ></textarea>
			</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button type="submit" id="submit" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
<input type="hidden" id="agentId" name ="agentId" value="-1" /> 
<input type="hidden" id="recommendId" name="recommendId" value="-1" /> 
<input type="hidden" id="province" name="province" /> 
<input type="hidden" id="city" name="city" /> 
<input type="hidden" id="area" name="area" /> 
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
		  bankObject.append("<option value='" + name + "' > " + name + "</option>");
	  }
		
	
	//初始化区域
	var data = {
			aId:['selectProvince','selectCity','selectArea']/* ,
			provice:110000 ,//默认为北京
			city:542300,
			area:542337  */
		};
	$.areaSelect(data);
	
	var errorMsg = $.trim($('#error').val());
	if(errorMsg != ''){
		alert(errorMsg);
	}
	
    $.extend($.fn.validatebox.defaults.rules, {
    	xnumber:{
    		validator: function(value, param){
    			var reg = /(^\d{1,2}$)/;
   	            if(!reg.test(value) || (value < 1 || value > 19)){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '请输入1-19的数字'
    	},
        phone: {
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
    		message: '银行卡号格式不正确'
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
        },
        checkRecommend:{
        	validator: function(value, param){
        		var v = $.trim(value);
        		if(v != ''){
        			var flag = false;
	        		$.ajax({
	    				url:'checkRecommend',
	    				async: false,
	    				data:{"code" : v, "type": 1},
	    				success:function(data){
	    					if(data == '-2'){
	    						flag = false;
	    					}else{
	    						flag = true;
	    						$("#recommendId").val(data);
	    					}
	    				}
	    			});
	        		return flag;
        		}else{
        			return false;
        		}
    		},
    		message: '用户不存在或已被冻结'
        },
        checkParent:{
        	validator: function(value, param){
        		var v = $.trim(value);
        		if(v != ''){
        			var flag = false;
	        		$.ajax({
	    				url:'checkRecommend',
	    				async: false,
	    				data:{"code" : v, "type" : 2},
	    				success:function(data){
	    					if(data == '-2'){
	    						flag = false;
	    					}else{
	    						flag = true;
	    						$("#agentId").val(data);
	    					}
	    				}
	    			});
	        		return flag;
        		}else{
        			return false;
        		}
    		},
    		message: '用户不是服务中心或已被冻结'
        }
    });
    
    $('#accountNo').on('change', function(){
    	var phone = $("#phone"),
    		vphone = $.trim(phone.val());
    	if(vphone == ''){
    		phone.val($('#accountNo').val());
    	}
    });
    
   	
	$("#submit").click(function(){
		if(tagFlag){
			return false;
		}
		
		if(!$("#form1").form('validate')){
			return false;
		}else{
			tagFlag = true;
			//区域存储中文
			if($("#selectProvince").val() != 0){
				$("#province").val($("#selectProvince  option:selected").text());
			}
			if($("#selectCity").val() != 0){
				$("#city").val($("#selectCity  option:selected").text());
			}
			if($("#selectArea").is(":visible")){
				$("#area").val($("#selectArea  option:selected").text());
			}
		}
	});
});

var tagFlag = false;

</script>
</body>
</html>